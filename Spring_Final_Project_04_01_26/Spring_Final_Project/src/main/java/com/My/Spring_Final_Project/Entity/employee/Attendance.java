package com.My.Spring_Final_Project.Entity.employee;

import com.My.Spring_Final_Project.Entity.common.BaseEntity;
import com.My.Spring_Final_Project.Enum.employee.Shift;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class Attendance extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private LocalDate date;

    private LocalTime checkIn;
    private LocalTime checkOut;


    @Enumerated(EnumType.STRING)
    private Shift shift;
    private boolean present;   // ✅ ADD THIS


}
