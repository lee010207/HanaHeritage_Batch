package com.heeha.domain.autoTransfer.jihu.service;

import com.heeha.domain.account.entity.Account;
import com.heeha.domain.account.jihu.repository.AccountRepository;
import com.heeha.domain.account.jihu.service.TransferService;
import com.heeha.domain.autoTransfer.entity.AutoTransfer;
import com.heeha.domain.autoTransfer.jihu.dto.CreateAutoTransferDto;
import com.heeha.domain.autoTransfer.jihu.repository.AutoTransferRepository;
import com.heeha.domain.history.dto.TransferHistoryDto;
import com.heeha.domain.history.service.HistoryService;
import com.heeha.global.config.BaseException;
import com.heeha.global.config.BaseResponse;
import com.heeha.global.config.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AutoTransferService {
    private final AutoTransferRepository autoTransferRepository;
    private final AccountRepository accountRepository;
    private final HistoryService historyService;
    private final TransferService transferService;

    // 자동이체 등록
    @Transactional
    public Long createAutoTransfer(CreateAutoTransferDto createAutoTransferDto) {
        Account fromAccount = accountRepository.findById(createAutoTransferDto.getAccountId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_FROM_ACCOUNT));
        if (transferService.CheckAccountPassword(fromAccount.getPassword(), createAutoTransferDto.getPassword())) {
            AutoTransfer saved = autoTransferRepository.save(createAutoTransferDto.toEntity(fromAccount));
            return saved.getId();
        } else {
            throw new BaseException(BaseResponseStatus.FAIL_REGISTER_AUTOTRANSFER);
        }
    }

    // 자동이체 실행
    @Transactional
    public void executeAutoTransfers() {
        LocalDate today = LocalDate.now();
        int day = today.getDayOfMonth();

        // 오늘날짜기준으로 이체해야될 자동이체내역 가져오기
        List<AutoTransfer> transfers = autoTransferRepository.findByAutoTransferDay(day);
        List<CreateAutoTransferDto> autoTransferDtoList = transfers.stream().map(CreateAutoTransferDto::new).toList();

        // 계좌이체 로직 실행
        // transfer(CreateHistoryDto createHistoryDto)

        for (CreateAutoTransferDto autoTransferDto : autoTransferDtoList) {
            if(today.isAfter(autoTransferDto.getStartDate().toLocalDate()) &&  today.isBefore(autoTransferDto.getEndDate().toLocalDate())){

                TransferHistoryDto autoTransfer = TransferHistoryDto.builder()
                    .dealClassification("자동이체")
                    .amount(autoTransferDto.getAmount())
                    .recipientBank(autoTransferDto.getRecipientBank()) // 수신 은행 명
                    .recipientNumber(autoTransferDto.getToAccountNumber())
                    .sender(autoTransferDto.getSender())
                    .recipientRemarks(autoTransferDto.getSender())
                    .senderRemarks(autoTransferDto.getRecipient())
                    .accountId(autoTransferDto.getAccountId())
                    .password(autoTransferDto.getPassword())
                    .build();

            transferService.transfer(autoTransfer);

            }
        }
    }



}
