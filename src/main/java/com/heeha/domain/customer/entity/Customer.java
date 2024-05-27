package com.heeha.domain.customer.entity;


import com.heeha.domain.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "customer")
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@ToString
@Getter
public class Customer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String phoneNumber;
    @Column(unique = true, nullable = false)
    private String identificationNumber;
}
