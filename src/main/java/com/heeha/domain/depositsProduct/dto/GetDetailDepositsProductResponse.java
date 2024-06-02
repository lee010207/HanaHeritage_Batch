package com.heeha.domain.depositsProduct.dto;

import com.heeha.domain.depositsProduct.entity.DepositsProduct;
import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor()
@ToString
@Builder
public class GetDetailDepositsProductResponse {
    private Long id;
    private String type;
    private String finPrdtNm; // 금융상품명
    private String mtrtInt; // 만기 후 이자율
    private String spclCnd; // 우대조건
    private String joinDeny; // 가입제한
    private String joinMember; // 가입대상
    private String etcNote; // 기타 유의사항
    private String maxLimit; // 최고한도
    private String promotionalText; // 홍보문구
    private String explanatoryText; // 설명문구

    public GetDetailDepositsProductResponse(DepositsProduct depositsProduct) {
        this.id = depositsProduct.getId();
        this.type = depositsProduct.getType().getTitle();
        this.finPrdtNm = depositsProduct.getFinPrdtNm();
        this.mtrtInt = depositsProduct.getMtrtInt();
        this.spclCnd = depositsProduct.getSpclCnd();
        this.joinDeny = depositsProduct.getJoinDeny();
        this.joinMember = depositsProduct.getJoinMember();
        this.etcNote = depositsProduct.getEtcNote();
        this.maxLimit = depositsProduct.getMaxLimit();
        this.promotionalText = depositsProduct.getPromotionalText();
        this.explanatoryText = depositsProduct.getExplanatoryText();
    }
}
