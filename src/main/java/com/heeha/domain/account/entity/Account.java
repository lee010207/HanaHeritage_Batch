package com.heeha.domain.account.entity;

import com.heeha.domain.base.entity.BaseEntity;
import com.heeha.domain.customer.entity.Customer;
import jakarta.persistence.*;
import lombok.*;

@Entity(name="account")
@Table(name="account")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@ToString
@Getter
public class Account extends BaseEntity {

    // 계좌 아이디
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 고객 아이디
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // 계좌번호
    private Long account_number;

    // 계좌 비밀번호
    private String password;

    // 계좌명
    private String name;

    // 잔액
    private long balance;

}
