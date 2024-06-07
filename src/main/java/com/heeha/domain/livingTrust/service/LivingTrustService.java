package com.heeha.domain.livingTrust.service;

import com.heeha.domain.consulting.entity.Consulting;
import com.heeha.domain.livingTrust.dto.GetLivingTrustSummaryDto;
import com.heeha.domain.livingTrust.entity.LivingTrust;
import com.heeha.domain.livingTrust.repository.LivingTrustRepository;
import com.heeha.global.config.BaseException;
import com.heeha.global.config.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LivingTrustService {
    private final LivingTrustRepository livingTrustRepository;

    public List<GetLivingTrustSummaryDto> getAllSummary() {
        return livingTrustRepository.findAllSummary();
    }

    public List<LivingTrust> getAll() {
        return livingTrustRepository.findAll();
    }

    public boolean setComplete(Long id){
        Optional<LivingTrust> response = livingTrustRepository.findById(id);
        if(response.isEmpty()){
            throw new BaseException(BaseResponseStatus.NOT_FOUND_LIVING_TRUST_ID);
        }

        LivingTrust livingTrust = response.get();
        livingTrust.setApproveTrue();
        livingTrustRepository.save(livingTrust);
        return true;
    }
}
