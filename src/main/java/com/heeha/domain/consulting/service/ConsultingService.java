package com.heeha.domain.consulting.service;

import com.heeha.domain.consulting.dto.ConsultingReservationRequest;
import com.heeha.domain.consulting.entity.Consulting;
import com.heeha.domain.consulting.repository.ConsultingRepository;
import com.heeha.global.config.BaseException;
import com.heeha.global.config.BaseResponseStatus;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConsultingService {
    private final ConsultingRepository consultingRepository;

    @Transactional
    public Long save(ConsultingReservationRequest request) {
        try {
            Consulting saved = consultingRepository.save(request.toEntity());
            return saved.getId();
        } catch (NullPointerException e) {
            throw new BaseException(BaseResponseStatus.INVALID_WORK_TYPE);
        } catch (DataIntegrityViolationException e) {
            throw new BaseException(BaseResponseStatus.USERS_EMPTY_USER_ID);
        }
    }
}
