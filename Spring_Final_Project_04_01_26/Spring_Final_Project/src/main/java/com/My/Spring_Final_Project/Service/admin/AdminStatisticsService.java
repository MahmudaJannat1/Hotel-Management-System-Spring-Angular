package com.My.Spring_Final_Project.Service.admin;

import com.My.Spring_Final_Project.DTO.admin.AdminStatisticsDTO;
import com.My.Spring_Final_Project.DTO.admin.AdminUserStatisticsDTO;

public interface AdminStatisticsService {

    AdminUserStatisticsDTO getUserStatistics();

    AdminStatisticsDTO getSystemStatistics();
}
