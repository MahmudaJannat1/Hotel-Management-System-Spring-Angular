package com.My.Spring_Final_Project.Repository.employee;

import com.My.Spring_Final_Project.Entity.employee.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByEmployeeIdAndDate(Long employeeId, LocalDate date);
    List<Attendance> findByEmployeeId(Long employeeId);

    long countByDateAndPresent(LocalDate date, boolean present);

}
