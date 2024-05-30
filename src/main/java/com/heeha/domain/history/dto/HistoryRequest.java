package com.heeha.domain.history.dto;

import com.heeha.domain.account.entity.Account;
import com.heeha.domain.customer.entity.Customer;
import com.heeha.domain.history.entity.History;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class HistoryRequest {
    @NotEmpty
    private String dealClassification;
    @NotEmpty
    private int deposit;
    @NotEmpty
    private int withdraw;
    @NotEmpty
    private String recipient;
    @NotEmpty
    private String recipientBank;
    @NotEmpty
    private Long recipientNumber;
    @NotEmpty
    private String sender;
    @NotEmpty
    private String recipientRemarks;
    @NotEmpty
    private String senderRemarks;

    private Account account;

    public History toEntity() {
        return History.builder()
                .dealClassification(dealClassification)
                .deposit(deposit)
                .withdraw(withdraw)
                .recipient(recipient)
                .recipientBank(recipientBank)
                .recipientNumber(recipientNumber)
                .sender(sender)
                .recipientRemarks(recipientRemarks)
                .senderRemarks(senderRemarks)
                .account(account)
                .build();
    }

}
