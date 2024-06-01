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
    @JsonProperty("fin_prdt_nm")
    private String finPrdtNm;

    @JsonProperty("spcl_cnd")
    private String spclCnd;

    @JsonProperty("promotional_text")
    private String promotionalText;

    public static GetListDepositsProductResponse fromEntity(DepositsProduct depositsProduct) {
        return GetListDepositsProductResponse.builder()
                .finPrdtNm(depositsProduct.getFinPrdtNm())
                .spclCnd(depositsProduct.getSpclCnd())
                .promotionalText(depositsProduct.getPromotionalText())
                .build();
    }
}
