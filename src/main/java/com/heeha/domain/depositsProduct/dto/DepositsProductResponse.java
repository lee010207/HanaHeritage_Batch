package com.heeha.domain.depositsProduct.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.heeha.domain.depositsProduct.entity.DepositsProduct;
import com.heeha.domain.depositsProduct.entity.DepositsType;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepositsProductResponse {

    @JsonProperty("join_member")
    private String joinMember;

    @JsonProperty("max_limit")
    private String maxLimit;

    @JsonProperty("join_way")
    private String joinWay;

    @JsonProperty("dcls_month")
    private String dclsMonth;

    @JsonProperty("join_deny")
    private String joinDeny;

    @JsonProperty("kor_co_nm")
    private String korCoNm;

    @JsonProperty("dcls_strt_day")
    private String dclsStrtDay;

    @JsonProperty("dcls_end_day")
    private String dclsEndDay;

    @JsonProperty("spcl_cnd")
    private String spclCnd;

    @JsonProperty("fin_co_no")
    private String finCoNo;

    @JsonProperty("mtrt_int")
    private String mtrtInt;

    @JsonProperty("fin_prdt_cd")
    private String finPrdtCd;

    @JsonProperty("fin_prdt_nm")
    private String finPrdtNm;

    @JsonProperty("etc_note")
    private String etcNote;

    @JsonProperty("fin_co_subm_day")
    private String finCoSubmDay;
}
