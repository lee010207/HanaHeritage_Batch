package com.heeha.domain.autoTransfer.jihu.dto;

import com.heeha.domain.account.entity.Account;
import com.heeha.domain.autoTransfer.entity.AutoTransfer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class CreateAutoTransferDto {

    private String sender;

    private String recipient;

    private String recipientBank;

    private Long toAccountNumber;

    private int amount;

    private int autoTransferDay;

    private Date endDate;

    private Date startDate;

    private Long accountId;

    private String password;


    public CreateAutoTransferDto(AutoTransfer autoTransfer) {
        this.sender = autoTransfer.getSender();
        this.autoTransferDay = autoTransfer.getAutoTransferDay();
        this.accountId = autoTransfer.getAccount().getId();
        this.startDate = autoTransfer.getStartDate();
        this.endDate = autoTransfer.getEndDate();
        this.toAccountNumber = autoTransfer.getToAccountNumber();
        this.recipient = autoTransfer.getRecipient();
        this.recipientBank = autoTransfer.getRecipientBank();
        this.password = autoTransfer.getAccount().getPassword();
        this.amount = autoTransfer.getAmount();
    }

    public AutoTransfer toEntity(Account account) {
        return AutoTransfer.builder()
                .sender(sender)
                .account(account)
                .autoTransferDay(autoTransferDay)
                .amount(amount)
                .recipient(recipient)
                .recipientBank(recipientBank)
                .startDate(startDate)
                .endDate(endDate)
                .toAccountNumber(toAccountNumber)
                .build();

    }

}