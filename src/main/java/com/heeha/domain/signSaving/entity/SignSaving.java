package com.heeha.domain.signSaving.entity;


import com.heeha.domain.account.entity.Account;
import com.heeha.domain.base.entity.BaseEntity;
import com.heeha.domain.customer.entity.Customer;
import com.heeha.domain.savingProduct.entity.SavingProduct;
import jakarta.persistence.*;

import java.time.LocalDateTime;

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

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "savingproduct_id")
    private SavingProduct savingProduct;

    private String accountName;
    private String accountNumber;
    private String password;

    @OneToOne
    @JoinColumn(name = "withdrawal_account_id")
    private Account withdrawalAccount;

    private Integer firstDeposit;
    private LocalDateTime maturityDate;
    private Boolean autoRedeposit;
    private Boolean maturityNotice;
    private Double interestRate;
}

