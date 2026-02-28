package com.My.Spring_Final_Project.Controller.dashboard;

import com.My.Spring_Final_Project.DTO.dashboard.DashboardResponseDTO;
import com.My.Spring_Final_Project.Service.dashboard.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_MODERATOR','ROLE_USER')")
    public ResponseEntity<DashboardResponseDTO> getDashboard() {
        return ResponseEntity.ok(dashboardService.getDashboard());
    }
}
