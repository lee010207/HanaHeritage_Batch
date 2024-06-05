package com.heeha.domain.signSaving.service;

import com.heeha.domain.account.dto.MakeTransactionDto;
import com.heeha.domain.account.dto.SavingAccountCreateDto;
import com.heeha.domain.account.entity.Account;
import com.heeha.domain.account.service.AccountService;
import com.heeha.domain.depositsProduct.entity.DepositsProduct;
import com.heeha.domain.depositsProduct.service.DepositsProductService;
import com.heeha.domain.signSaving.dto.AccountInfoResponse;
import com.heeha.domain.signSaving.dto.SavingJoinRequestDto;
import com.heeha.domain.signSaving.dto.SavingJoinResponseDto;
import com.heeha.domain.signSaving.entity.SignSaving;
import com.heeha.domain.signSaving.repository.SignSavingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class SignSavingService {
    private final DepositsProductService depositsProductService;
    private final AccountService accountService;
    private final SignSavingRepository signSavingRepository;

    @Transactional
    public SavingJoinResponseDto joinSavingAccount(Long customerId, SavingJoinRequestDto savingJoinRequestDto) {

        // 가입할 상품 가져오기
        DepositsProduct product = depositsProductService.getDetail(savingJoinRequestDto.getSavingProductId());

        SavingAccountCreateDto savingAccountCreateDto = new SavingAccountCreateDto(product.getFinPrdtNm(),
                savingJoinRequestDto.getAccountPassword(), 0L);

        Account savingAccount = accountService.createAccount(customerId, savingAccountCreateDto);
        Account withdrawAccount = accountService.getAccount(savingJoinRequestDto.getWithdrawAccountId());

        /* TO-DO
         * 가입 요청 Dto에서 예치금액만큼 출금계좌에서 차감하기 (이체 로직 사용)
         */
        accountService.makeTransaction(MakeTransactionDto.builder()
                .accountId(withdrawAccount.getId())
                .password(withdrawAccount.getPassword())
                .amount(savingJoinRequestDto.getDepositAmount())
                .recipientAccountNumber(savingAccount.getAccountNumber())
                .recipientBank("하나")
                .senderRemarks(savingAccount.getName())
                .recipientRemarks(withdrawAccount.getName()).build());

        SignSaving signSaving = SignSaving.builder()
                .account(savingAccount)
                .depositsProduct(product)
                .contractYears(savingJoinRequestDto.getContractYears())
                .snsNotice(savingJoinRequestDto.getSnsNotice())
                .interestRate(savingJoinRequestDto.getInterestRate())
                .build();

        return new SavingJoinResponseDto(signSavingRepository.save(signSaving));
    }

    public AccountInfoResponse getAccountInfo(Long accountId) {
        SignSaving signSaving = signSavingRepository.findByAccountId(accountId);

        return AccountInfoResponse.todto(signSaving);
    }

}
