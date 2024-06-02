package com.heeha.domain.signSaving.service;

import com.heeha.domain.account.dto.SavingAccountCreateDto;
import com.heeha.domain.account.entity.Account;
import com.heeha.domain.account.service.AccountService;
import com.heeha.domain.depositsProduct.entity.DepositsProduct;
import com.heeha.domain.depositsProduct.service.DepositsProductService;
import com.heeha.domain.signSaving.dto.SavingJoinRequestDto;
import com.heeha.domain.signSaving.dto.SavingJoinResponseDto;
import com.heeha.domain.signSaving.entity.SignSaving;
import com.heeha.domain.signSaving.repository.SignSavingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SignSavingService {
    private final DepositsProductService depositsProductService;
    private final AccountService accountService;
    private final SignSavingRepository signSavingRepository;

    /**
     * 예적금 가입 처리
     * <p>
     * <p>
     * 예금 DTO 입력
     * <p>
     * 가입 금액, 출금계좌Id, 출금계좌 비밀번호, 가입기간, 적립방법, 만기해지구분, 자동해지시점, SNS 통보
     * <p>
     * 예금 가입 프로세스 1. 출금계좌랑 출금계좌 비밀번호 확인 (Validation) : AccountService 담당 2. 잔액확인 3. 출금 계좌에서 가입금액 차감 4. 가입금액,고객아이디 예금계좌생성
     * 5. 끝
     */

    @Transactional
    public SavingJoinResponseDto joinSavingAccount(Long customerId, SavingJoinRequestDto savingJoinRequestDto) {

        // 가입할 상품 가져오기
        DepositsProduct product = depositsProductService.getDetail(savingJoinRequestDto.getSavingProductId());


        SavingAccountCreateDto savingAccountCreateDto = new SavingAccountCreateDto(product.getFinPrdtNm(),
                savingJoinRequestDto.getAccountPassword(), savingJoinRequestDto.getDepositAmount());

        Account savingAccount = accountService.createAccount(customerId, savingAccountCreateDto);
        Account withdrawAccount = accountService.getAccount(savingJoinRequestDto.getWithdrawAccountId());

        /* TO-DO
         * 가입 요청 Dto에서 예치금액만큼 출금계좌에서 차감하기 (이체 로직 사용)
         */

        SignSaving signSaving = SignSaving.builder()
                .account(savingAccount)
                .depositsProduct(product)
                .contractYears(savingJoinRequestDto.getContractYears())
                .snsNotice(savingJoinRequestDto.getSnsNotice())
                .interestRate(savingJoinRequestDto.getInterestRate())
                .build();

        return new SavingJoinResponseDto(signSavingRepository.save(signSaving));
    }

}
