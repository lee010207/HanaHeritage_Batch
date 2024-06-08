package com.heeha.domain.signDeposit.dto;
import com.heeha.domain.account.entity.Account;
import com.heeha.domain.autoTransfer.entity.AutoTransfer;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class MakeAutoTransfer {

    private Long toAccountNumber;
    private Long id;

    private Long amount;

    private String password;
    private LocalDate startDate;

}
