package com.My.Spring_Final_Project.Entity.pricing;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import com.My.Spring_Final_Project.Enum.Pricing.PromotionType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "promotions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Promotion extends SoftDeleteEntity {

    @Column(unique = true, nullable = false)
    private String promoCode;

    @Enumerated(EnumType.STRING)
    private PromotionType promotionType;
    // PERCENTAGE, FLAT

    @Column(nullable = false)
    private Double discountValue;

    private LocalDate validFrom;
    private LocalDate validTo;

    private Boolean active = true;
}
