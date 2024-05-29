package com.heeha.domain.signDeposit.entity;

import com.heeha.domain.account.entity.Account;
import com.heeha.domain.base.entity.BaseEntity;
import com.heeha.domain.customer.entity.Customer;
import com.heeha.domain.depositProduct.entity.DepositProduct;
import com.heeha.domain.depositsProduct.entity.DepositsProduct;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "deposit_prdt_id")
    private DepositsProduct deposit;

    private String password;
    private String accountNumber;
    private Integer firstDeposit;
    private String installmentMethod;

    private Integer contractPeriod;
    private String snsNotice;

    private Double interestRate;

}
