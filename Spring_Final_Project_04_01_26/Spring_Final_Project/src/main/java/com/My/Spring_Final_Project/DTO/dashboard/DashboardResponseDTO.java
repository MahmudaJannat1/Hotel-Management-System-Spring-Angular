package com.My.Spring_Final_Project.DTO.dashboard;

import lombok.*;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DashboardResponseDTO {
    private String role;
    private LocalDateTime generatedAt;
    private Map<String, Object> sections;
}
