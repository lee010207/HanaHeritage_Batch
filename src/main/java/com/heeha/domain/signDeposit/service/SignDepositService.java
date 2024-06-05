package com.heeha.domain.signDeposit.service;

import com.heeha.domain.account.dto.AccountCreateDto;
import com.heeha.domain.account.dto.DepositAccountCreateDto;
import com.heeha.domain.account.dto.MakeTransactionDto;
import com.heeha.domain.account.entity.Account;
import com.heeha.domain.account.service.AccountService;
import com.heeha.domain.autoTransfer.dto.CreateAutoTransferDto;
import com.heeha.domain.autoTransfer.entity.AutoTransfer;
import com.heeha.domain.autoTransfer.service.AutoTransferService;
import com.heeha.domain.depositsProduct.entity.DepositsProduct;
import com.heeha.domain.depositsProduct.service.DepositsProductService;
import com.heeha.domain.signDeposit.dto.SignDepositRequest;
import com.heeha.domain.signDeposit.entity.SignDeposit;
import com.heeha.domain.signDeposit.repository.SignDepositRepository;
import com.heeha.domain.signDeposit.dto.AccountInfoResponse;
import com.heeha.domain.signDeposit.dto.SignDepositResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignDepositService {
    private final DepositsProductService depositsProductService;
    private final AccountService accountService;
    private final SignDepositRepository signDepositRepository;
    private final AutoTransferService autoTransferService;

    /**
     * 예적금 가입 처리
     * <p>
     * <p>
     * 적금 DTO 입력
     * <p>
     * 가입 금액, 출금계좌Id, 출금계좌 비밀번호, 가입기간, 적립방법, 자동이체 계좌번호, 자동이체 비번, 자동이체 금액, 자동이체 시작일, 만기해지구분, 자동해지시점, SNS 통보
     * <p>
     * 적금 가입 프로세스 1. 출금계좌랑 출금계좌 비밀번호 확인 (Validation) : AccountService 담당 2. 잔액확인 3. 출금 계좌에서 가입금액 차감 4. 자동이체 출금계좌가 다를시 계좌 비번 확인 5. 가입금액,고객아이디 적금계좌생성
     * 6. 끝
     */

    @Transactional
    public SignDepositResponse joinDepositAccount(Long customerId, SignDepositRequest signDepositRequest) {
        // 가입할 상품
        DepositsProduct product = depositsProductService.getDetail(signDepositRequest.getDepositProductId());
        log.info("가입할 상품 조회 : {}", product.getId());
        // 출금 계좌
        Account withdrawAccount = accountService.getAccount(signDepositRequest.getWithdrawAccountId());
        log.info("출금 계좌 번호 : {}", withdrawAccount.getAccountNumber());

        // 예적금 신규 계좌 생성 및 초기 금액 셋팅
        Account depositAccount = initiateDepositAccount(customerId, product.getFinPrdtNm(), signDepositRequest.getAccountPassword(), withdrawAccount, signDepositRequest.getDepositAmount());

        Long autoTransferId = 0L;
        if (signDepositRequest.getAutoTransfer() != null) {
            Account autoTransferAccount=accountService.getAccount(signDepositRequest.getAutoTransfer().getId());

            log.info("Auto Transfer create START");
            CreateAutoTransferDto createAutoTransferDto = CreateAutoTransferDto.builder()
                    .autoTransferDay(signDepositRequest.getAutoTransfer().getStartDate().getDayOfMonth())
                    .accountId(autoTransferAccount.getId())
                    .startDate(signDepositRequest.getAutoTransfer().getStartDate())
                    .endDate(signDepositRequest.getAutoTransfer().getStartDate().plusYears(signDepositRequest.getContractYears()))
                    .recipientRemarks(depositAccount.getName())
                    .senderRemarks(withdrawAccount.getName())
                    .toAccountNumber(autoTransferAccount.getAccountNumber())
                    .recipientBank("하나")
                    .password(signDepositRequest.getAutoTransfer().getPassword())
                    .amount(signDepositRequest.getAutoTransfer().getAmount())
                    .build();


            autoTransferId = autoTransferService.createAutoTransfer(createAutoTransferDto);
        }

        SignDeposit signDeposit = SignDeposit.builder()
                .autoTransfer(AutoTransfer.builder().id(autoTransferId).build())
                .account(depositAccount)
                .depositsProduct(product)
                .contractYears(signDepositRequest.getContractYears())
                .snsNotice(signDepositRequest.getSnsNotice())
                .interestRate(signDepositRequest.getInterestRate())
                .build();

        return new SignDepositResponse(signDepositRepository.save(signDeposit));
    }

    private Account initiateDepositAccount(
            Long customerId,
            String productName,
            String accountPassword,
            Account withdrawAccount,
            Long depositAmount
    ) {
        // 예적금 신규 계좌 생성
        DepositAccountCreateDto depositAccountCreateDto = new DepositAccountCreateDto(
                productName,
                accountPassword,
                0L
        );
        Account depositAccount = accountService.createAccount(customerId, depositAccountCreateDto);

        // 출금계좌 -> 예적금 신규 계좌 이체
        MakeTransactionDto makeTransactionDto = MakeTransactionDto.builder()
                .accountId(withdrawAccount.getId())
                .amount(depositAmount)
                .password(withdrawAccount.getPassword())
                .recipientBank("하나")
                .recipientAccountNumber(depositAccount.getAccountNumber())
                .recipientRemarks(withdrawAccount.getName())
                .senderRemarks(depositAccount.getName())
                .build();

        accountService.makeTransaction(makeTransactionDto);
        log.info("계좌 생성 및 적금 계좌에 입금 완료");
        return depositAccount;
    }

    public AccountInfoResponse getAccountInfo(Long accountId) {
        SignDeposit signDeposit = signDepositRepository.findByAccountId(accountId);

        return AccountInfoResponse.todto(signDeposit);
    }
}
