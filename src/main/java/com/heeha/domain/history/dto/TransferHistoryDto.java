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
@Setter
public class TransferHistoryDto {

    @NotEmpty
    private String dealClassification;

    private Long amount;

    private String recipient;
    @NotEmpty
    private String recipientBank;

    private Long recipientNumber;

    private Long senderNumber;

    private String sender;
    @NotEmpty
    private String recipientRemarks;
    @NotEmpty
    private String senderRemarks;
    @NotEmpty
    private Account account;

    public History toEntity() {
        return History.builder()
                .dealClassification(dealClassification)
                .amount(amount)
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
