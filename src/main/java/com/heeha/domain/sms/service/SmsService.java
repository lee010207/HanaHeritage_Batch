package com.heeha.domain.sms.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.heeha.domain.customer.dto.CustomerContactDto;
import com.heeha.domain.customer.entity.Customer;
import com.heeha.domain.postBeneficiary.entity.PostBeneficiary;
import com.heeha.domain.sms.dto.ReserveSmsDto;
import com.heeha.domain.sms.dto.SmsPhoneNumberDto;
import com.heeha.domain.sms.entity.SmsReservation;
import com.heeha.domain.sms.entity.SmsReservationTarget;
import com.heeha.domain.sms.repository.SmsReservationRepository;
import com.heeha.domain.sms.repository.SmsReservationTargetRepository;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoEmptyResponseException;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.exception.NurigoUnknownException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SmsService {

    private final DefaultMessageService defaultMessageService;
    private final RedisTemplate<String, Object> redisTemplate;
    private final String CERTIFICATION = "[본인 인증 번호] : ";

    private final SmsReservationRepository smsReservationRepository;
    private final SmsReservationTargetRepository smsReservationTargetRepository;

    public SmsService(@Value("${coolsms.apikey}") String apiKey, @Value("${coolsms.apiPrivate}") String apiSecret,
                      RedisTemplate<String, Object> redisTemplate, SmsReservationRepository smsReservationRepository, SmsReservationTargetRepository smsReservationTargetRepository) {
        this.redisTemplate = redisTemplate;
        this.smsReservationRepository = smsReservationRepository;
        this.smsReservationTargetRepository = smsReservationTargetRepository;
        this.defaultMessageService = NurigoApp.INSTANCE.initialize(apiKey,
                apiSecret, "https://api.coolsms.co.kr");
    }

    public Boolean sendCertificationMessage(String phoneNumber) {
        String certification = createCertificationNumber();
        String text = CERTIFICATION + certification + " 입니다.";
        sendMessage(phoneNumber, text);
        redisTemplate.opsForValue().set(phoneNumber, certification, 5, TimeUnit.MINUTES);
        return Boolean.TRUE;
    }

    public void sendMessage(String phoneNumber, String text) {
        Message message = new Message();
        message.setFrom("01090410672");
        message.setTo(phoneNumber);
        message.setText(text);

        try {
            defaultMessageService.send(message);
        } catch (NurigoMessageNotReceivedException | NurigoEmptyResponseException | NurigoUnknownException e) {
            throw new RuntimeException(e);
        }
    }


    private String createCertificationNumber() {
        Random random = new Random();
        return String.valueOf(random.nextInt(9000) + 1000);
    }

    @Transactional
    public Boolean reserve(ReserveSmsDto reserveDto) {
        // SmsReservation 생성
        SmsReservation smsReservation = smsReservationRepository.save(
                SmsReservation.builder()
                        .content(reserveDto.getContent())
                        .sendingDate(reserveDto.getSendingDate())
                        .build()
        );

        // SmsReservationTarget 생성
        for (Long customerId : reserveDto.getCustomerIdList()) {
            smsReservationTargetRepository.save(
                    SmsReservationTarget.builder()
                            .smsReservation(smsReservation)
                            .customer(Customer.builder().id(customerId).build())
                            .build()
            );
        }

        return Boolean.TRUE;
    }

    public List<SmsReservation> getAllAtDate(LocalDate date) {
        return smsReservationRepository.findAllBySendingDateBetween(date, date.plusDays(1));
    }

    public List<String> getPhoneNumberBySmsReservationId(Long smsReservationId) {
        return smsReservationTargetRepository.findPhoneNumberBySmsReservationId(smsReservationId);
    }

    public SmsReservation insert(SmsReservation smsReservation) {
        return smsReservationRepository.save(smsReservation);
    }

    public List<SmsReservation> getAll() {
        return smsReservationRepository.findAll();
    }
}
