package com.heeha.domain.signDeposit.service;

import com.heeha.domain.account.dto.DepositAccountCreateDto;
import com.heeha.domain.account.dto.SavingAccountCreateDto;
import com.heeha.domain.account.entity.Account;
import com.heeha.domain.account.service.AccountService;
import com.heeha.domain.account.yma.dto.AccountInfoResponse;
import com.heeha.domain.depositsProduct.entity.DepositsProduct;
import com.heeha.domain.depositsProduct.service.DepositsProductService;
import com.heeha.domain.signDeposit.dto.SignDepositRequest;
import com.heeha.domain.signDeposit.dto.SignDepositResponse;
import com.heeha.domain.signDeposit.entity.SignDeposit;
import com.heeha.domain.signDeposit.repository.SignDepositRepository;
import com.heeha.domain.signSaving.dto.SavingJoinRequestDto;
import com.heeha.domain.signSaving.entity.SignSaving;
import com.heeha.domain.signSaving.repository.SignSavingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignDepositService {
    private final DepositsProductService depositsProductService;
    private final AccountService accountService;
    private final SignDepositRepository signDepositRepository;

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

        // 가입할 상품 가져오기
        DepositsProduct product = depositsProductService.getDetail(signDepositRequest.getDepositProductId());

        DepositAccountCreateDto depositAccountCreateDto = new DepositAccountCreateDto(product.getFinPrdtNm(),
                signDepositRequest.getAccountPassword(), signDepositRequest.getDepositAmount());

        Account depositAccount = accountService.createAccount(customerId, depositAccountCreateDto);
        Account withdrawAccount = accountService.getAccount(signDepositRequest.getWithdrawAccountId());


        MakeTransactionDto makeTransactionDto = MakeTransactionDto.builder()
                .accountId(withdrawAccount.getId())
                .amount(signDepositRequest.getDepositAmount())
                .password(withdrawAccount.getPassword())
                .recipientBank("하나")
                .recipientAccountNumber(depositAccount.getAccountNumber())
                .recipientRemarks(withdrawAccount.getName())
                .senderRemarks(depositAccount.getName())
                .memo("적금 납입")
                .build();

        accountService.makeTransaction(makeTransactionDto);

        SignDeposit signDeposit = SignDeposit.builder()
                .account(depositAccount)
                .depositsProduct(product)
                .contractYears(signDepositRequest.getContractYears())
                .snsNotice(signDepositRequest.getSnsNotice())
                .interestRate(signDepositRequest.getInterestRate())
                .build();

        return new SignDepositResponse(signDepositRepository.save(signDeposit));
    }

    public AccountInfoResponse getAccountInfo(Long accountId) {
        SignDeposit signDeposit = signDepositRepository.findByAccountId(accountId);

        return AccountInfoResponse.todto(signDeposit);
    }
}
