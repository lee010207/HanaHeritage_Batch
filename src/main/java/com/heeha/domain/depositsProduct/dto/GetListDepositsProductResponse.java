package com.heeha.domain.depositsProduct.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.heeha.domain.depositsProduct.entity.DepositsProduct;
import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Builder
public class GetListDepositsProductResponse {
    @JsonProperty("id")
    private long id;

    @JsonProperty("fin_prdt_nm")
    private String finPrdtNm;

    @JsonProperty("spcl_cnd")
    private String spclCnd;

    @JsonProperty("join_member")
    private String joinMember;

    @JsonProperty("max_limit")
    private String maxLimit;


    public static GetListDepositsProductResponse fromEntity(DepositsProduct depositsProduct) {
        return GetListDepositsProductResponse.builder()
                .id(depositsProduct.getId())
                .finPrdtNm(depositsProduct.getFinPrdtNm())
                .spclCnd(depositsProduct.getSpclCnd())
                .joinMember(depositsProduct.getJoinMember())
                .maxLimit(depositsProduct.getMaxLimit())
                .build();
    }
}
