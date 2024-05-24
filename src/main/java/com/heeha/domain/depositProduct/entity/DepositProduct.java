package com.heeha.domain.depositProduct.entity;


import com.heeha.domain.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity(name = "deposit_product")
@Table(name = "deposit_product")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class DepositProduct extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deposit_product_id")
    private Long id;

    @Column(name = "fin_prdt_cd", length = 20)
    private String finPrdtCd;

    @Column(name = "dcls_month", length = 10, nullable = false)
    private String dclsMonth;

    @Column(name = "fin_co_no", length = 20, nullable = false)
    private String finCoNo;

    @Column(name = "kor_co_nm", length = 20, nullable = false)
    private String korCoNm;

    @Column(name = "fin_prdt_nm", length = 20, nullable = false)
    private String finPrdtNm;

    @Column(name = "join_way", length = 20, nullable = false)
    private String joinWay;

    @Column(name = "mtrt_int", length = 20, nullable = false)
    private String mtrtInt;

    @Column(name = "spcl_cnd", length = 20, nullable = false)
    private String spclCnd;

    @Column(name = "join_deny", length = 20, nullable = false)
    private String joinDeny;

    @Column(name = "join_member", length = 20, nullable = false)
    private String joinMember;

    @Column(name = "etc_note", length = 20, nullable = false)
    private String etcNote;
    @Column(name = "max_limit", length = 20, nullable = false)
    private String maxLimit;
    @Column(name = "dcls_strt_day", length = 20, nullable = false)
    private String dclsStrtDay;
    @Column(name = "dcls_end_day", length = 20, nullable = false)
    private String dclsEndDay;
    @Column(name = "fin_co_subm_day", length = 20, nullable = false)
    private String finCoSubmDay;

}
