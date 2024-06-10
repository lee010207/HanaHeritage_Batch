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

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Slf4j
public class GetAutoTransferTasklet implements Tasklet, StepExecutionListener {
    private final AutoTransferService autoTransferService;
    private LocalDate today;

    private List<CreateAutoTransferDto> autoTransferDtoList;

    public GetAutoTransferTasklet(AutoTransferService autoTransferService, LocalDate today) {
        this.autoTransferService = autoTransferService;
        this.today = today;
    }
    @Override
    public void beforeStep(StepExecution stepExecution) {
        int day = LocalDate.now().getDayOfMonth();
        autoTransferDtoList = autoTransferService.getAutoTransferByDay(day);
        log.info("자동이체 내역 조회: {}", autoTransferDtoList);
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        StepExecution stepExecution = contribution.getStepExecution();
        if(autoTransferDtoList.isEmpty()){
            contribution.setExitStatus(ExitStatus.FAILED);
        }
        //stepExecution.getExecutionContext().put("autoTransferDtoList", (Serializable) autoTransferDtoList);
        //stepExecution.getExecutionContext().put("autoTransferDtoList", autoTransferDtoList);
        chunkContext.getStepContext().getStepExecution().getExecutionContext().put("autoTransferDtoList", autoTransferDtoList);
        log.info("Serializable :{}", (List<CreateAutoTransferDto>) stepExecution.getExecutionContext().get("autoTransferDtoList"));
        return RepeatStatus.FINISHED;
    }
}
