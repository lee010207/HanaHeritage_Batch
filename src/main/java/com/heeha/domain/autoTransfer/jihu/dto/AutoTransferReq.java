package com.heeha.domain.autoTransfer.jihu.dto;

import com.heeha.domain.account.entity.Account;
import com.heeha.domain.autoTransfer.entity.AutoTransfer;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class AutoTransferReq {

    private String sender;

    private String recipient;

    private String toAccountNumber;

    private int amount;

    private int autoTransferDay;

    private LocalDateTime endDate;

    private Account account;

    public AutoTransferReq(AutoTransfer autoTransfer) {
        this.sender = autoTransfer.getSender();
        this.recipient = autoTransfer.getRecipient();
        this.toAccountNumber = autoTransfer.getToAccountNumber();
        this.amount = autoTransfer.getAmount();
        this.autoTransferDay = autoTransfer.getAutoTransferDay();
        this.endDate = autoTransfer.getEndDate();
        this.account = autoTransfer.getAccount();
    }
}