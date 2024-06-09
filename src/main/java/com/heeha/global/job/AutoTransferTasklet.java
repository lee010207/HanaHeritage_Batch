package com.heeha.global.job;

import com.heeha.domain.account.dto.MakeTransactionDto;
import com.heeha.domain.account.service.AccountService;
import com.heeha.domain.autoTransfer.dto.CreateAutoTransferDto;
import com.heeha.domain.autoTransfer.service.AutoTransferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.time.LocalDate;
import java.util.List;

@Slf4j
public class AutoTransferTasklet implements Tasklet, StepExecutionListener {
    private final AutoTransferService autoTransferService;
    private final AccountService transferService;
    private LocalDate today;

    public AutoTransferTasklet(AutoTransferService autoTransferService, AccountService transferService) {
        this.autoTransferService = autoTransferService;
        this.transferService = transferService;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.today = LocalDate.now();
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        int day = today.getDayOfMonth();


        List<CreateAutoTransferDto> autoTransferDtoList = autoTransferService.getAutoTransferByDay(day);

        for (CreateAutoTransferDto autoTransferDto : autoTransferDtoList) {
            if (today.isAfter(autoTransferDto.getStartDate()) && today.isBefore(autoTransferDto.getEndDate())) {
                MakeTransactionDto autoTransfer = MakeTransactionDto.builder()
                        .amount(autoTransferDto.getAmount())
                        .recipientBank(autoTransferDto.getRecipientBank())
                        .recipientAccountNumber(autoTransferDto.getToAccountNumber())
                        .recipientRemarks(autoTransferDto.getRecipientRemarks())
                        .senderRemarks(autoTransferDto.getSenderRemarks())
                        .accountId(autoTransferDto.getAccountId())
                        .password(autoTransferDto.getPassword())
                        .build();

                transferService.makeTransaction(autoTransfer);
            }
        }

        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return ExitStatus.COMPLETED;
    }
}
