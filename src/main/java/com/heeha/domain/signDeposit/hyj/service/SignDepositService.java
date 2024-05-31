package com.heeha.domain.signDeposit.hyj.service;

import com.heeha.domain.account.JCS.dto.SavingAccountCreateDto;
import com.heeha.domain.account.JCS.service.AccountService;
import com.heeha.domain.account.entity.Account;
import com.heeha.domain.signDeposit.hyj.dto.SavingJoinRequestDto;
import com.heeha.domain.signDeposit.hyj.dto.SignDepositRequest;
import com.heeha.domain.signDeposit.hyj.repository.SignDepositRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignDepositService {
    private final SignDepositRepository signDepositRepository;
    private final AccountService accountService;

    /**
     * 예적금 가입 처리
     *
     *
     * 예금
     * DTO 입력
     *
     * 가입 금액, 출금계좌Id, 출금계좌 비밀번호, 가입기간, 적립방법, 만기해지구분, 자동해지시점, SNS 통보
     *
     * 예금 가입 프로세스
     * 1. 출금계좌랑 출금계좌 비밀번호 확인 (Validation) : AccountService 담당
     * 2. 잔액확인
     * 3. 출금 계좌에서 가입금액 차감
     * 4. 가입금액,고객아이디 예금계좌생성
     * 5. 끝
     */

    public Account joinSavingAccount(Long customerId, SavingJoinRequestDto savingJoinRequestDto){



    }

    public Account createSavingAccount(Long customerId, SavingAccountCreateDto savingAccountCreateDto){
       return accountService.createAccount(customerId, savingAccountCreateDto);
    }

    private
}
