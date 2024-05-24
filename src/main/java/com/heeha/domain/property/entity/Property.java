package com.heeha.domain.property.entity;

import com.heeha.domain.base.entity.BaseEntity;
import com.heeha.domain.livingTrust.entity.LivingTrust;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "property")
@Table(name = "property")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@ToString
@Getter
public class Property extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "living_trust_id", nullable = false)
    private LivingTrust livingTrust;

    @Column(name = "type", length = 12)
    private String type;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "location", length = 30)
    private String location;

    @Column(name = "quantity")
    private Integer quantity;
}
