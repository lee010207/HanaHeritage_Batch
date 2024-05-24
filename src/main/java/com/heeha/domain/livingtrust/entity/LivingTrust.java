package com.heeha.domain.livingtrust.entity;

import com.heeha.domain.base.entity.BaseEntity;
import com.heeha.domain.customer.entity.Customer;
import com.heeha.domain.property.entity.Property;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "living_trust")
@Table(name = "living_trust")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
public class LivingTrust extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "trust_contract_start_date")
    private LocalDateTime trustContractStartDate;

    @Column(name = "trust_contract_end_date")
    private LocalDateTime trustContractEndDate;

    @Column(name = "settlor", length = 50)
    private String settlor;

    @Column(name = "trustee", length = 50)
    private String trustee;

    @Column(name = "contract_number", length = 50)
    private String contractNumber;

    @OneToMany(mappedBy = "livingTrust", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Property> properties;
}

