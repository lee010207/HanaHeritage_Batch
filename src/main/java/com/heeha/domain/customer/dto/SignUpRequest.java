package com.heeha.domain.customer.dto;

import com.heeha.domain.customer.entity.Customer;
import com.heeha.domain.customer.entity.RoleType;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class SignUpRequest {
    @NotEmpty
    private Long id;
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
                .id(id)
                .name(name)
                .password(password)
                .phoneNumber(phoneNumber)
                .identificationNumber(identificationNumber)
                .role(RoleType.USER).build();
    }
}
