package com.My.Spring_Final_Project.Entity.pricing;

import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import com.My.Spring_Final_Project.Enum.Pricing.SeasonType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "seasonal_rates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeasonalRate extends SoftDeleteEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeasonType seasonType;   // PEAK, OFF_PEAK, NORMAL

    @Column(nullable = false)
    private Double priceMultiplier;  // 1.2, 0.8 etc

    private LocalDate startDate;
    private LocalDate endDate;
}
