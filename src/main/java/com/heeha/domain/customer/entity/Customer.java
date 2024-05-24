package com.heeha.domain.customer.entity;



import com.heeha.domain.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity(name="customer")
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

    private String name;
    private String password;
    private String phoneNumber;
    private String identificationNumber;
}
