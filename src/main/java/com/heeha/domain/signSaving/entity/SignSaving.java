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

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "first_deposit")
    private Integer firstDeposit;

    @Column(name = "installment_method")
    @Enumerated(EnumType.STRING)
    private InstallmentMethod installmentMethod;

    @Column(name = "contract_period")
    @Enumerated(EnumType.STRING)
    private ContractYears contractYears;

    @Column(name = "maturity_class")
    @Enumerated(EnumType.STRING)
    private MaturityClass maturityClass;

    @Column(name = "auto_cancel")
    @Enumerated(EnumType.STRING)
    private AutoCancel autoCancel;

    @Column(name = "sns_notice")
    private String snsNotice;

    @Column(name = "auto_account")
    private String autoAccount;

    @Column(name = "auto_start_date")
    private LocalDateTime autoStartDate;

    @Column(name = "auto_transfer_day")
    private String autoTransferDay;

    @Column(name = "auto_period")
    private String autoPeriod;

    @Column(name = "interest_rate")
    private Double interestRate;

    public enum InstallmentMethod {
        정액적립식, 자유적립식
    }
    public enum MaturityClass {
        self, auto
    }
    public enum ContractYears {
        one, two, three
    }

    public enum AutoCancel {
        만기일, 이연만기일
    }

}

