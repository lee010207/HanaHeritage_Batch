package com.heeha.domain.consulting.service;

import com.heeha.domain.consulting.dto.GetConsultingDto;
import com.heeha.domain.consulting.dto.ReserveConsultingDto;
import com.heeha.domain.consulting.entity.Consulting;
import com.heeha.domain.consulting.repository.ConsultingRepository;
import com.heeha.global.config.BaseException;
import com.heeha.global.config.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsultingService {
    private final ConsultingRepository consultingRepository;

    @Transactional
    public Long save(Long customerId, ReserveConsultingDto request) {
        try {
            Consulting saved = consultingRepository.save(request.toEntity(customerId));
            return saved.getId();
        } catch (NullPointerException e) {
            throw new BaseException(BaseResponseStatus.INVALID_WORK_TYPE);
        } catch (DataIntegrityViolationException e) {
            throw new BaseException(BaseResponseStatus.USERS_EMPTY_USER_ID);
        }
    }

    public List<GetConsultingDto> getAllByReservationDate(LocalDate reservationDate) {
        return consultingRepository.findAllByReservationDate(reservationDate);
    }

    @Transactional
    public boolean setComplete(Long id){
        Optional<Consulting> response = consultingRepository.findById(id);
        if(!response.isPresent()){
            throw new BaseException(BaseResponseStatus.NOT_FOUND_CONSULTING_ID);
        }

        Consulting consulting = response.get();
        consulting.setCompleteTrue();
        return true;
    }
}
