package com.heeha.domain.history.dto;

import com.heeha.domain.account.entity.Account;
import com.heeha.domain.history.entity.History;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Builder
public class CreateHistoryDto {

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
    @NotEmpty
    private String password;
    @NotEmpty
    private Long accountId;

    public History toEntity(Account account) {
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