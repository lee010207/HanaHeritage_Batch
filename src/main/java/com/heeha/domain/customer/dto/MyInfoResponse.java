package com.heeha.domain.customer.dto;

import com.heeha.domain.customer.entity.Customer;
import com.heeha.domain.customer.entity.RoleType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MyInfoResponse {
    private Long id;

    private RoleType role = RoleType.USER;

    private String name;

    public MyInfoResponse(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
    }
}
