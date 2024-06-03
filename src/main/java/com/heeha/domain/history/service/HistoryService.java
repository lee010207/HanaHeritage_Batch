package com.heeha.domain.history.service;

import com.heeha.domain.history.dto.TransferHistoryDto;
import com.heeha.domain.history.repository.HistoryRepository;
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
public class HistoryService {
    private static final Logger log = LoggerFactory.getLogger(HistoryService.class);
    private final HistoryRepository historyRepository;

    @Transactional
    public void historySave(TransferHistoryDto history){
        try {
            historyRepository.save(history.toEntity());
        } catch (DataIntegrityViolationException e) {
            throw new BaseException(BaseResponseStatus.FAIL_TRANSFER);
        }

    }

}
