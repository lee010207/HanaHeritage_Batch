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
public class ExecutionAutoTransferTasklet implements Tasklet, StepExecutionListener {
    private final AccountService transferService;
    private LocalDate today;
    //private List<CreateAutoTransferDto> autoTransferDtoList;

    public ExecutionAutoTransferTasklet(AccountService transferService, LocalDate today) {
        this.transferService = transferService;
        this.today = today;
    }


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        StepExecution stepExecution = contribution.getStepExecution();
        List<CreateAutoTransferDto> autoTransferDtoList = (List<CreateAutoTransferDto>) stepExecution.getExecutionContext().get("autoTransferDtoList");

        for (CreateAutoTransferDto autoTransferDto : autoTransferDtoList) {
            if (today.isAfter(autoTransferDto.getStartDate()) && today.isBefore(autoTransferDto.getEndDate())) {
                try {
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
                } catch (Exception e) {
                    log.error("자동이체 실패: " + e.getMessage());
                }
            }
        }
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return ExitStatus.COMPLETED;
    }
}
