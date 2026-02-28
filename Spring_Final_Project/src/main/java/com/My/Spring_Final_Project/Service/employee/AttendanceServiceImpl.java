package com.My.Spring_Final_Project.Service.employee;

import com.My.Spring_Final_Project.DTO.employee.AttendanceDTO;
import com.My.Spring_Final_Project.Entity.employee.Attendance;
import com.My.Spring_Final_Project.Entity.employee.Employee;
import com.My.Spring_Final_Project.Repository.employee.AttendanceRepository;
import com.My.Spring_Final_Project.Repository.employee.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;
    @Override
    public List<AttendanceDTO> getAllAttendance() {
        return attendanceRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }


    @Override
    public AttendanceDTO markAttendance(AttendanceDTO dto) {
        Employee emp = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Attendance attendance = new Attendance();
        attendance.setEmployee(emp);
        attendance.setDate(dto.getDate());
        attendance.setCheckIn(dto.getCheckIn());
        attendance.setCheckOut(dto.getCheckOut());
        attendance.setShift(dto.getShift());
        attendance.setPresent(dto.getCheckIn() != null);
        attendance.setPresent(true);


        attendanceRepository.save(attendance);

        dto.setId(attendance.getId());
        return dto;
    }

    // ✅ UPDATE
    @Override
    public AttendanceDTO updateAttendance(Long id, AttendanceDTO dto) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        attendance.setDate(dto.getDate());
        attendance.setCheckIn(dto.getCheckIn());
        attendance.setCheckOut(dto.getCheckOut());
        attendance.setShift(dto.getShift());

        attendanceRepository.save(attendance);
        return mapToDTO(attendance);
    }

    // ✅ DELETE
    @Override
    public void deleteAttendance(Long id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        attendanceRepository.delete(attendance);
    }

    @Override
    public List<AttendanceDTO> getAttendanceByEmployee(Long employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceDTO> getAttendanceByEmployeeAndDate(Long employeeId, LocalDate date) {
        return attendanceRepository.findByEmployeeIdAndDate(employeeId, date)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private AttendanceDTO mapToDTO(Attendance attendance) {
        AttendanceDTO dto = new AttendanceDTO();
        dto.setId(attendance.getId());
        dto.setEmployeeId(attendance.getEmployee().getId());
        dto.setDate(attendance.getDate());
        dto.setCheckIn(attendance.getCheckIn());
        dto.setCheckOut(attendance.getCheckOut());
        dto.setShift(attendance.getShift());
        return dto;
    }
}
