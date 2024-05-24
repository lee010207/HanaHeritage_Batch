package com.heeha.domain.history.entity;

import com.heeha.domain.account.entity.Account;
import com.heeha.domain.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity(name = "history")
@Table(name = "history")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@ToString
@Getter
public class History extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private Date dealdate;

    private String dealClassification;

    private int deposit;

    private int withdraw;
    private String recipient;
    private String sender;
    private String remarks;
}