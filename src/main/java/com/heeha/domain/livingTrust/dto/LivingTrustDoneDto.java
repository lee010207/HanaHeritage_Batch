package com.heeha.domain.livingTrust.dto;

import com.heeha.domain.deathNotifier.dto.DeathNotifierRegisterDto;
import com.heeha.domain.postBeneficiary.dto.PostBeneficiaryRegisterDto;
import com.heeha.domain.property.dto.PropertyRegisterDto;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LivingTrustDoneDto {
    private String contractNumber;
    private String settlor;
    private String trustee;
    private LocalDateTime trustContractStartDate;
    private LocalDateTime trustContractEndDate;

    private List<PostBeneficiaryRegisterDto> postBeneficiary;
    private List<PropertyRegisterDto> properties;
    private List<DeathNotifierRegisterDto> deathNotifiers;
}
