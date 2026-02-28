package com.My.Spring_Final_Project.Controller.employee;

import com.My.Spring_Final_Project.DTO.employee.AttendanceDTO;
import com.My.Spring_Final_Project.Service.employee.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;


    // ✅ GET ALL ATTENDANCE
    @GetMapping
    public ResponseEntity<List<AttendanceDTO>> getAllAttendance() {
        return ResponseEntity.ok(attendanceService.getAllAttendance());
    }

    @PostMapping
    public ResponseEntity<AttendanceDTO> markAttendance(@RequestBody AttendanceDTO dto) {
        return ResponseEntity.ok(attendanceService.markAttendance(dto));
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<AttendanceDTO> updateAttendance(
            @PathVariable Long id,
            @RequestBody AttendanceDTO dto
    ) {
        return ResponseEntity.ok(attendanceService.updateAttendance(id, dto));
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
        return ResponseEntity.ok("Attendance deleted successfully");
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<AttendanceDTO>> getAttendanceByEmployee(
            @PathVariable Long employeeId
    ) {
        return ResponseEntity.ok(attendanceService.getAttendanceByEmployee(employeeId));
    }

    @GetMapping("/employee/{employeeId}/date/{date}")
    public ResponseEntity<List<AttendanceDTO>> getAttendanceByEmployeeAndDate(
            @PathVariable Long employeeId,
            @PathVariable String date
    ) {
        LocalDate localDate = LocalDate.parse(date);
        return ResponseEntity.ok(
                attendanceService.getAttendanceByEmployeeAndDate(employeeId, localDate)
        );
    }
}
