package com.My.Spring_Final_Project.DTO.employee;

import com.My.Spring_Final_Project.Enum.employee.Shift;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AttendanceDTO {
    private Long id;
    private Long employeeId;
    private LocalDate date;
    private LocalTime checkIn;
    private LocalTime checkOut;
    private Shift shift;
    private Boolean present;

}
