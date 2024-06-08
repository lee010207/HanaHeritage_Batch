package com.heeha.global.aop;

import com.heeha.domain.depositsProduct.entity.DepositsProduct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class PreferenceAop {

    private final RedisTemplate<String, Object> redisTemplate;

    @Pointcut("@annotation(com.heeha.global.aop.Preference)")
    private void cut() {
    }


    @Around("cut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Long productId = (Long) joinPoint.getArgs()[0];
        log.info("예적금 상품 아이디 : {} 가 조회되었습니다.", productId);
        DepositsProduct selectedProduct = (DepositsProduct) joinPoint.proceed();
        log.info("조회된 상품명 : {}", selectedProduct.getFinPrdtNm());
        updatePreference(selectedProduct.getFinPrdtNm());
        return selectedProduct;
    }

    private void updatePreference(String productName) {
        if (!redisTemplate.opsForValue().setIfAbsent(productName, "1")) {
            int preference = Integer.parseInt((String) redisTemplate.opsForValue().get(productName));

            redisTemplate.opsForValue().set(productName, String.valueOf(preference + 1));
        }
    }
}
