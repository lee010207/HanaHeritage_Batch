package com.heeha.domain.signDeposit.entity;

import com.heeha.domain.account.entity.Account;
import com.heeha.domain.base.entity.BaseEntity;
import com.heeha.domain.customer.entity.Customer;
import com.heeha.domain.depositProduct.entity.DepositProduct;
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
    // 적금가입아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "withdrawal_account")
    private Account account;

    @JoinColumn(name = "customer_id")
    @OneToOne
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "fin_prdt_cd")
    private DepositProduct deposit;

    private int first_deposit;

    private int installment_method;

    private int contract_period;

    private int auto_transfer_apply;

    private int auto_redeopsit;

    private String maturity_classification;

    private int auto_cancellation_point;

    private String maturity_notice;

}
