package com.My.Spring_Final_Project.Controller.employee;

import com.My.Spring_Final_Project.DTO.employee.AdminEmployeeDashboardDTO;
import com.My.Spring_Final_Project.Service.employee.AdminEmployeeDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/api/admin/employee-dashboard")
    @RequiredArgsConstructor
    public class AdminEmployeeDashboardController {

        private final AdminEmployeeDashboardService dashboardService;

        @GetMapping
        public ResponseEntity<AdminEmployeeDashboardDTO> getDashboard() {
            return ResponseEntity.ok(dashboardService.getDashboardStats());
        }
    }


