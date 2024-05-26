package com.heeha.domain.customer.dto;

import com.heeha.domain.customer.entity.Customer;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class SignUpRequest {
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
    @NotEmpty
    private String phoneNumber;
    @NotEmpty
    private String identificationNumber;

    public Customer toEntity() {
        return Customer.builder()
                .name(name)
                .password(password)
                .phoneNumber(phoneNumber)
                .identificationNumber(identificationNumber).build();
    }

}
