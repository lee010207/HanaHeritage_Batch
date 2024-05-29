package com.heeha.domain.signSaving.entity;


import com.heeha.domain.account.entity.Account;
import com.heeha.domain.base.entity.BaseEntity;
import com.heeha.domain.customer.entity.Customer;
import com.heeha.domain.depositsProduct.entity.DepositsProduct;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

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
    @JoinColumn(name = "withdrawal_account")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "saving_prdt_id")
    private DepositsProduct saving;

    private String password;
    private String accountNumber;
    private Integer firstDeposit;
    private String installmentMethod;

    private Integer contractPeriod;
    private String maturityClass;
    private String autoCancel;
    private String snsNotice;

    private String autoTransfer;
    private Integer autoAmount;
    private Integer autoAccountPwd;
    private String autoAccount;
    private Date autoStartDay;
    private String autoTransferDay;
    private String autoPeriod;
    private Double interestRate;

}

