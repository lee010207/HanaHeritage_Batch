package com.heeha.domain.depositsProduct.entity;


import com.heeha.domain.base.entity.BaseEntity;
import com.heeha.domain.customer.entity.RoleType;
import com.heeha.domain.depositsProduct.entity.DepositsType;
import jakarta.persistence.*;
import lombok.*;


@Entity(name = "deposits_product")
@Table(name = "deposits_product")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class DepositsProduct extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DepositsType type;

    @Column(name = "fin_prdt_cd")
    private String finPrdtCd;

    @Column(name = "dcls_month")
    private String dclsMonth;

    @Column(name = "fin_co_no")
    private String finCoNo;

    @Column(name = "kor_co_nm")
    private String korCoNm;

    @Column(name = "fin_prdt_nm")
    private String finPrdtNm;

    @Column(name = "join_way")
    private String joinWay;

    @Column(name = "mtrt_int")
    private String mtrtInt;

    @Column(name = "spcl_cnd")
    private String spclCnd;

    @Column(name = "join_deny")
    private String joinDeny;

    @Column(name = "join_member")
    private String joinMember;

    @Column(name = "etc_note")
    private String etcNote;

    @Column(name = "max_limit")
    private String maxLimit;

    @Column(name = "dcls_strt_day")
    private String dclsStrtDay;

    @Column(name = "dcls_end_day")
    private String dclsEndDay;

    @Column(name = "fin_co_subm_day")
    private String finCoSubmDay;

}
