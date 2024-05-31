package com.heeha.domain.signDeposit.hyj.service;

import com.heeha.domain.signDeposit.hyj.repository.SignDepositRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignDepositService {
    private final SignDepositRepository signDepositRepository;

//    @Transactional


}
