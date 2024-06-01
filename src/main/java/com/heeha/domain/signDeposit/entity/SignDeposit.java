package com.heeha.domain.signDeposit.entity;

import com.heeha.domain.account.entity.Account;
import com.heeha.domain.autoTransfer.entity.AutoTransfer;
import com.heeha.domain.base.entity.BaseEntity;
import com.heeha.domain.depositsProduct.entity.DepositsProduct;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "sign_deposit")
@Table(name = "sign_deposit")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@ToString
@Getter
public class SignDeposit extends BaseEntity {
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

    @OneToOne
    @JoinColumn(name = "auto_transfer_id", nullable = true)
    private AutoTransfer autoTransfer;

    private InstallmentMethodType installmentMethod; // 적립 방법
    private MaturityClassType maturityClass; // 만기해지구분
    private AutoCancelType autoCancel; // 자동 해지 시점
    private Integer monthlyAmount; // 월 납입 금액

    private Integer contractYears; // 계약 햇수 setSubscriptionPeriod
    private Boolean snsNotice; // SNS 만기 알림 setSmsNotification
    private Double interestRate; // 금리
}
