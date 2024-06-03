package com.heeha.domain.sms.service;

import java.util.Random;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoEmptyResponseException;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.exception.NurigoUnknownException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
    private final DefaultMessageService defaultMessageService = NurigoApp.INSTANCE.initialize("",
            "", "https://api.coolsms.co.kr");

    public Boolean sendMessage(String phoneNumber) {
        Message message = new Message();
        message.setFrom("01090410672");
        message.setTo(phoneNumber);
        message.setText("문자 테스트다. 이 자식아");
        try {
            defaultMessageService.send(message);
        } catch (NurigoMessageNotReceivedException | NurigoEmptyResponseException | NurigoUnknownException e) {
            throw new RuntimeException(e);
        }
        return Boolean.TRUE;
    }


    private String makeMessage() {

    }


    private String createCertificationNumber() {
        Random random = new Random();
        return String.valueOf(random.nextInt(9000) + 1000);
    }
}
