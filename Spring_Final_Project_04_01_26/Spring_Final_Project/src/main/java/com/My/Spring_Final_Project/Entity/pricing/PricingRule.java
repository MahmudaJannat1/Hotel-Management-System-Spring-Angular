package com.My.Spring_Final_Project.Entity.pricing;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import com.My.Spring_Final_Project.Enum.Pricing.PricingRuleType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pricing_rules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PricingRule extends SoftDeleteEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PricingRuleType ruleType;
    // LONG_STAY, WEEKEND, CORPORATE

    @Column(nullable = false)
    private Double discountPercentage;

    private Integer minNights; // long stay rule এর জন্য

    private Boolean active = true;
}
