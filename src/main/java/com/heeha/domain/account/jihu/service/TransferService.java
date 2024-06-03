package com.heeha.domain.account.jihu.service;

import com.heeha.domain.account.entity.Account;
import com.heeha.domain.account.jihu.dto.AccountResponse;
import com.heeha.domain.account.jihu.repository.AccountRepository;
import com.heeha.domain.history.dto.TransferHistoryDto;
import com.heeha.domain.history.entity.History;
import com.heeha.domain.history.service.HistoryService;
import com.heeha.global.config.BaseException;
import com.heeha.global.config.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransferService {
    private static final Logger log = LoggerFactory.getLogger(TransferService.class);
    private final AccountRepository accountRepository;
    private final HistoryService historyService;

    @Transactional
    public void transfer(TransferHistoryDto createHistoryDto) {
        Long fromAccountId = createHistoryDto.getAccountId();
        Long toAccountNumber = createHistoryDto.getRecipientNumber();
        long amount = createHistoryDto.getAmount();
        String receipientBank = createHistoryDto.getRecipientBank();
        String password = createHistoryDto.getPassword();

        // 계좌 아이디 기준으로 출금 계좌 정보 조회
        Account account = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_FROM_ACCOUNT));
        AccountResponse fromAccount = AccountResponse.builder().accountNumber(account.getAccountNumber()).
                balance(account.getBalance()).password(account.getPassword()).customer(account.getCustomer()).build();

        // 잔액 조회 - 이체 가능 여부 확인
        checkBalance(fromAccount.getBalance(), amount);

        // 비밀번호 일치 여부 확인
        if(CheckAccountPassword(fromAccount.getPassword(), password)) {
            History fromHistory = null;

            // 하나 -> 하나 이체
            if (receipientBank.equals("하나")) {
                Account toAccount = accountRepository.findByAccountNumber(toAccountNumber);
                if (toAccount == null) {
                    throw new BaseException(BaseResponseStatus.NO_TO_ACCOUNT);
                }
                createHistoryDto.setRecipient(toAccount.getCustomer().getName());
                toAccount.setBalance(toAccount.getBalance() + amount);

                History toHistory =
                        History.builder()
                                .account(toAccount)
                                .dealClassification(createHistoryDto.getDealClassification())
                                .deposit((int) amount)
                                .senderNumber(fromAccount.getAccountNumber())
//                                .recipient(toAccount.getCustomer().getName())
                                .recipientBank("하나")
                                .recipientNumber(toAccount.getAccountNumber())
                                .sender(fromAccount.getCustomer().getName())
                                .recipientRemarks(createHistoryDto.getSenderRemarks())
                                .senderRemarks(createHistoryDto.getRecipientRemarks())
                                .build();
                log.info(toHistory.toString());

                try {
                    // 거래내역 INSERT
                    historyService.historySave(toHistory);
                } catch (DataIntegrityViolationException e) {
                    throw new BaseException(BaseResponseStatus.FAIL_TRANSFER);
                }
            }
            // 타행이체
            account.setBalance(fromAccount.getBalance() - amount);
            historyService.historySave(createHistoryDto.toEntity(account));
        }

    }


    public void checkBalance(Long balance, long amount){
        // 잔액이 부족한 경우 -> 메서드 분리
        if (balance < amount) {
            throw new BaseException(BaseResponseStatus.INVALID_BALANCE);
        }
    }


    public boolean CheckAccountPassword(String accPw, String inputPw){
            if (!accPw.equals(inputPw)) {
                throw new BaseException(BaseResponseStatus.WRONG_PASSWORD);
            }
            return true;
    }
}
