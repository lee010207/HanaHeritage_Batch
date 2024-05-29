package com.heeha.domain.depositsProduct.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * finPrdtCd : 금융상품 코드
 * dclsMonth : 공시제출월
 * finCoNo : 금융회사코드
 * korCoNm : 금융회사명
 * finPrdtNm : 금융상품명
 * joinWay : 가입방법
 * mtrtInt : 만기 후 이자율(설명)
 * spclCnd : 우대조건
 * joinDeny : 가입제한
 * joinMember : 가입대상
 * etcNote : 기타 유의사항
 * maxLimit : 최고한도
 * dclsStartDay : 공시시작일
 * dclsEndDay : 공시종료일
 * finCoSubmDay : 금융회사 제출일
 */
@Entity(name = "saving_product")
@Table(name = "saving_product")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class DepositsProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "saving_product_id")
    private Long id;

    private String type;
    private String finPrdtCd;
    private String dclsMonth;
    private String finCoNo;
    private String korCoNm;
    private String finPrdtNm;
    private String joinWay;
    private String mtrtInt;
    private String spclCnd;
    private String joinDeny;
    private String joinMember;
    private String etcNote;
    private String maxLimit;
    private String dclsStartDay;
    private String dclsEndDay;
    private String finCoSubmDay;
}

