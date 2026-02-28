package com.My.Spring_Final_Project.Service.employee;

import com.My.Spring_Final_Project.DTO.employee.AttendanceDTO;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {

    AttendanceDTO markAttendance(AttendanceDTO dto);          // CREATE

    AttendanceDTO updateAttendance(Long id, AttendanceDTO dto); // UPDATE

    void deleteAttendance(Long id);                           // DELETE

    List<AttendanceDTO> getAttendanceByEmployee(Long employeeId);

    List<AttendanceDTO> getAttendanceByEmployeeAndDate(Long employeeId, LocalDate date);
    List<AttendanceDTO> getAllAttendance();

}

