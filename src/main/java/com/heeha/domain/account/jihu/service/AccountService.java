package com.heeha.domain.account.jihu.service;

import com.heeha.domain.account.entity.Account;
import com.heeha.domain.account.jihu.dto.AccountCheckResponse;
import com.heeha.domain.account.jihu.repository.AccountRepository;
import com.heeha.domain.history.dto.HistoryRequest;
import com.heeha.domain.history.entity.History;
import com.heeha.domain.history.repository.HistoryRepository;
import com.heeha.global.config.BaseException;
import com.heeha.global.config.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private static final Logger log = LoggerFactory.getLogger(AccountService.class);
    private final AccountRepository accountRepository;
    private final HistoryRepository historyRepository;

    @Transactional
    public void transfer(HistoryRequest historyRequest) {
        Long fromAccountId = historyRequest.getAccount().getId();
        Long toAccountNumber = historyRequest.getRecipientNumber();
        long amount = historyRequest.getWithdraw();
        String receipientBank = historyRequest.getRecipientBank();

        log.info(historyRequest.toString());
        log.info("--------------------"+fromAccountId+" "+toAccountNumber+" "+amount+"--------------------");
        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_FROM_ACCOUNT));


        // 비밀번호 확인은 INSERT 전에 유효성 체크
//        if (!fromAccount.getPassword().equals(password)) {
//            throw new Exception("Invalid password");
//        }

        // 잔액이 부족한 경우
        if (fromAccount.getBalance() < amount) {
            throw new BaseException(BaseResponseStatus.INVALID_BALANCE);
        }

        History fromHistory = null;

        // 하나 -> 하나 이체
        if (receipientBank.equals("하나")) {
            Account toAccount = accountRepository.findByAccountNumber(toAccountNumber);
            if (toAccount == null) {
                throw new BaseException(BaseResponseStatus.NO_TO_ACCOUNT);
            }

            toAccount.setBalance(toAccount.getBalance() + amount);

            accountRepository.save(toAccount);

            History toHistory = History.builder()
                    .account(toAccount)
                    .dealClassification(historyRequest.getDealClassification())
                    .deposit((int) amount)
                    .recipient(toAccount.getCustomer().getName())
                    .recipientBank("하나")
                    .recipientNumber(toAccount.getAccountNumber())
                    .sender(fromAccount.getCustomer().getName())
                    .recipientRemarks(historyRequest.getSenderRemarks())
                    .senderRemarks(historyRequest.getRecipientRemarks())
                    .build();

            try {
                historyRepository.save(historyRequest.toEntity());
                historyRepository.save(toHistory);
            } catch (DataIntegrityViolationException e) {
                throw new BaseException(BaseResponseStatus.FAIL_TRANSFER);
            }
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);

        try{
            accountRepository.save(fromAccount);
        }catch (DataIntegrityViolationException e){
            throw new BaseException(BaseResponseStatus.FAIL_TRANSFER);
        }

        historyRepository.save(historyRequest.toEntity());

    }



}
