package com.heeha.global.scheduler;

import com.heeha.domain.customer.service.CustomerService;
import com.heeha.domain.sms.dto.SmsPhoneNumberDto;
import com.heeha.domain.sms.entity.SmsReservation;
import com.heeha.domain.sms.repository.SmsReservationTargetRepository;
import com.heeha.domain.sms.service.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class SmsScheduler {

    private final SmsService smsService;

    @Scheduled(cron = "0 0 14 * * *") // 매일 14:00:00 에 실행
    public void run() {
        LocalDate today = LocalDate.now();
        List<SmsReservation> smsReservationList = smsService.getAllAtDate(today);
        for (SmsReservation smsReservation : smsReservationList) {
            // 문자 발송
            List<String> phoneNumberList = smsService.getPhoneNumberBySmsReservationId(smsReservation.getId());
            for (String phoneNumber : phoneNumberList) {
                smsService.sendMessage(phoneNumber, smsReservation.getContent());
            }

            // 문자 발송 상태 변경
            smsReservation.setSentTrue();
            smsService.insert(smsReservation);
        }
        log.info("=== 문자 예약 발송 ===");
    }
}