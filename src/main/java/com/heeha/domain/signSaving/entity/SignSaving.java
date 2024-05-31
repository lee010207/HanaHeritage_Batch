package com.heeha.domain.signSaving.entity;


import com.heeha.domain.account.entity.Account;
import com.heeha.domain.base.entity.BaseEntity;
import com.heeha.domain.depositsProduct.entity.DepositsProduct;
import jakarta.persistence.*;

import lombok.*;

@Entity(name = "sign_saving")
@Table(name = "sign_saving")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class SignSaving extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "deposits_product_id")
    private DepositsProduct depositsProduct;

    private Integer contractYears; // 계약 햇수
    private Boolean snsNotice; // SNS 만기 알림
    private Double interestRate; // 금리
}

