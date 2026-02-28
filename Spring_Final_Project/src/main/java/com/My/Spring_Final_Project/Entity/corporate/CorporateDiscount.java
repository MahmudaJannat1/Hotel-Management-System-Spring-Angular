package com.My.Spring_Final_Project.Entity.corporate;
import com.My.Spring_Final_Project.Entity.common.SoftDeleteEntity;
import com.My.Spring_Final_Project.Enum.Corporate.DiscountType;
import jakarta.persistence.*;


@Entity
@Table(name = "corporate_discounts")
public class CorporateDiscount extends SoftDeleteEntity {
    @ManyToOne
    @JoinColumn(name = "contract_id")
    private CorporateContract contract;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;  // FLAT or PERCENTAGE
}
