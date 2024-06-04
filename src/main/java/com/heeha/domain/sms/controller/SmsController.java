package com.heeha.domain.sms.controller;

import com.heeha.domain.sms.service.SmsService;
import com.heeha.global.config.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sms")
@RequiredArgsConstructor
public class SmsController {
    private final SmsService smsService;

    @GetMapping("/send")
    public BaseResponse.SuccessResult<Boolean> sendSms(String phoneNumber) {
        return BaseResponse.success(smsService.sendMessage(phoneNumber));
    }

}
