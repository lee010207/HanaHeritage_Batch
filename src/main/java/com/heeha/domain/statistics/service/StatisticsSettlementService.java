package com.heeha.domain.statistics.service;

import com.heeha.domain.account.entity.Account;
import com.heeha.domain.history.dto.DailySettlementDto;
import com.heeha.domain.statistics.entity.StatisticsSettlement;
import com.heeha.domain.statistics.repository.StatisticsSettlementRepository;
import com.heeha.global.config.BaseException;
import com.heeha.global.config.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsSettlementService {
    private final StatisticsSettlementRepository repository;
    private final StatisticsSettlementRepository statisticsSettlementRepository;

    @Transactional
    public void save(LocalDate date, List<DailySettlementDto> dailySettlementDtoList) {
        StatisticsSettlement statisticsSettlement = new StatisticsSettlement();
        statisticsSettlement.setDate(date);

        for (DailySettlementDto dailySettlementDto : dailySettlementDtoList) {
            if (dailySettlementDto.getDealClassification().equals("입금")) {
                statisticsSettlement.setDepositCount(dailySettlementDto.getCount());
                statisticsSettlement.setDepositAmount(dailySettlementDto.getSumAmount());
            } else {
                statisticsSettlement.setWithdrawalCount(dailySettlementDto.getCount());
                statisticsSettlement.setWithdrawalAmount(dailySettlementDto.getSumAmount());
            }
        }

        repository.save(statisticsSettlement);
    }

    public boolean existStatisticsSettlement(LocalDate dealDate) {
        return !statisticsSettlementRepository.findByDate(dealDate).isEmpty();
    }
}
