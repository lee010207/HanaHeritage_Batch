package com.heeha;

import com.heeha.domain.history.dto.DailySettlementDto;
import com.heeha.domain.history.repository.HistoryRepository;
import com.heeha.domain.history.service.HistoryService;
import com.heeha.domain.statistics.repository.StatisticsSettlementRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BatchStatisticsTests {

    @Autowired
    HistoryService historyService;

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    StatisticsSettlementRepository statisticsSettlementRepository;

    @Test
    void 정산_데이터_조회() {
        LocalDateTime startDate = LocalDate.of(2024, 6, 4).atStartOfDay();
        LocalDateTime endDate = LocalDate.of(2024, 6, 4).atTime(LocalTime.MAX);
        List<DailySettlementDto> list = historyRepository.getStatistics(startDate, endDate);
        System.out.println(list);
    }

    @Test
    void 이미_존재하는_정산_데이터가_있는가(){
        LocalDate dealDate = LocalDate.of(2024, 6, 7);
        boolean isExist = !statisticsSettlementRepository.findByDate(dealDate).isEmpty();
        System.out.println(isExist);
    }

}