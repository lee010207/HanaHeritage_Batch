package com.heeha.global.job;

import com.heeha.domain.history.dto.DailySettlementDto;
import com.heeha.domain.history.service.HistoryService;
import com.heeha.domain.statistics.entity.StatisticsSettlement;
import com.heeha.domain.statistics.service.StatisticsSettlementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

@Slf4j
public class SettlementValidTasklet implements Tasklet {

    private final LocalDate dealDate;
    private final StatisticsSettlementService statisticsSettlementService;

    @Autowired
    public SettlementValidTasklet(LocalDate dealDate, StatisticsSettlementService statisticsSettlementService) {
        this.dealDate = dealDate;
        this.statisticsSettlementService = statisticsSettlementService;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        // 어제 이체 통계가 이미 처리되었다면
        if (statisticsSettlementService.existStatisticsSettlement(dealDate)) {
            contribution.setExitStatus(ExitStatus.FAILED);
        }
        return RepeatStatus.FINISHED;
    }
}
