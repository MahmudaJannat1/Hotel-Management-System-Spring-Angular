package com.My.Spring_Final_Project.Entity.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@FilterDef(
        name = "activeFilter",
        parameters = @ParamDef(name = "isActive", type = Boolean.class)
)
@Filter(
        name = "activeFilter",
        condition = "is_active = :isActive"
)
public abstract class SoftDeleteEntity extends BaseEntity {

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
