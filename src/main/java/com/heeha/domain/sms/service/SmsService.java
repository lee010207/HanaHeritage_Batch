package com.heeha.domain.sms.service;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoEmptyResponseException;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.exception.NurigoUnknownException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    private final DefaultMessageService defaultMessageService;
    private final RedisTemplate<String, Object> redisTemplate;
    private final String CERTIFICATION = "[본인 인증 번호] : ";

    public SmsService(@Value("${coolsms.apikey}") String apiKey, @Value("${coolsms.apiPrivate}") String apiSecret,
                      RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.defaultMessageService = NurigoApp.INSTANCE.initialize(apiKey,
                apiSecret, "https://api.coolsms.co.kr");
    }

    public Boolean sendMessage(String phoneNumber) {
        Message message = new Message();
        message.setFrom("01090410672");
        message.setTo(phoneNumber);
        String certification = createCertificationNumber();
        message.setText(CERTIFICATION + certification + " 입니다.");

        try {
            defaultMessageService.send(message);
            redisTemplate.opsForValue().set(phoneNumber, certification, 5, TimeUnit.MINUTES);
        } catch (NurigoMessageNotReceivedException | NurigoEmptyResponseException | NurigoUnknownException e) {
            throw new RuntimeException(e);
        }
        return Boolean.TRUE;
    }


    private String createCertificationNumber() {
        Random random = new Random();
        return String.valueOf(random.nextInt(9000) + 1000);
    }
}
