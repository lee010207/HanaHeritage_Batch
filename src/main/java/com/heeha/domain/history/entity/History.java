package com.heeha.domain.history.entity;

import com.heeha.domain.account.entity.Account;
import com.heeha.domain.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;

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

    // 출금계좌번호(id기준 조회)
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @CreatedDate
    private Timestamp dealdate;

    //@ColumnDefault("이체")
    private String dealClassification;

    // 입금액 : 받는 사람 계좌 +
    private int deposit;

    // 출금액 : 보내는 사람 계좌 -
    private int withdraw;

    // 받는 사람 이름
    private String recipient;
    // 받는 사람 은행
    private String recipientBank;
    // 받는 사람 계좌 번호
    private Long recipientNumber;

    // 보내는 사람
    private String sender;

    // 받는 분 표기
    private String recipientRemarks;
    // 보내는 분 표기
    private String senderRemarks;
}
