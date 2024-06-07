package com.heeha.domain.postBeneficiary.service;

import com.heeha.domain.livingTrust.entity.LivingTrust;
import com.heeha.domain.postBeneficiary.dto.PostBeneficiaryRegisterDto;
import com.heeha.domain.postBeneficiary.entity.PostBeneficiary;
import com.heeha.domain.postBeneficiary.repository.PostBeneficiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostBeneficiaryService{
    private final PostBeneficiaryRepository postBeneficiaryRepository;

    public Long save(PostBeneficiaryRegisterDto postBeneficiaryRegisterDto, LivingTrust livingTrust) {
        PostBeneficiary postBeneficiary = PostBeneficiary.builder()
                .name(postBeneficiaryRegisterDto.getName())
                .phoneNumber(postBeneficiaryRegisterDto.getPhoneNumber())
                .address(postBeneficiaryRegisterDto.getAddress())
                .birthdate(postBeneficiaryRegisterDto.getBirthdate())
                .relation(postBeneficiaryRegisterDto.getRelation())
                .ratio(postBeneficiaryRegisterDto.getRatio())
                .livingTrust(livingTrust)
                .build();
        return postBeneficiaryRepository.save(postBeneficiary).getId();
    }

}
