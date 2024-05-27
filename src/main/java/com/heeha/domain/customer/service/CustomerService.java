package com.heeha.domain.customer.service;

import com.heeha.domain.customer.dto.SignUpRequest;
import com.heeha.domain.customer.entity.Customer;
import com.heeha.domain.customer.repository.CustomerRepository;
import com.heeha.global.config.BaseException;
import com.heeha.global.config.BaseResponseStatus;
import java.sql.SQLIntegrityConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
    private final CustomerRepository customerRepository;

    @Transactional
    public Long save(SignUpRequest signUpRequest) {
        try {
            Customer saved = customerRepository.save(signUpRequest.toEntity());
            return saved.getId();
        } catch (DataIntegrityViolationException e) {
            throw new BaseException(BaseResponseStatus.DUPLICATE_CUSTOMER);
        }
    }
}
