package com.My.Spring_Final_Project.Controller.admin;

import com.My.Spring_Final_Project.DTO.admin.AdminStatisticsDTO;
import com.My.Spring_Final_Project.DTO.admin.AdminUserStatisticsDTO;
import com.My.Spring_Final_Project.Service.admin.AdminStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/statistics")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminStatisticsController {

    private final AdminStatisticsService adminStatisticsService;

    // ================= USER STATISTICS =================
    @GetMapping("/users")
    public ResponseEntity<AdminUserStatisticsDTO> getUserStatistics() {
        return ResponseEntity.ok(
                adminStatisticsService.getUserStatistics()
        );
    }

    // ================= SYSTEM STATISTICS =================
    @GetMapping("/system")
    public ResponseEntity<AdminStatisticsDTO> getSystemStatistics() {
        return ResponseEntity.ok(
                adminStatisticsService.getSystemStatistics()
        );
    }
}
