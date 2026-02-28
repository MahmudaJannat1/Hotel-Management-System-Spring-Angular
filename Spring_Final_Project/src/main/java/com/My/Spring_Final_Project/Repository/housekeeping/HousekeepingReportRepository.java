package com.My.Spring_Final_Project.Repository.housekeeping;

import com.My.Spring_Final_Project.Entity.housekeeping.HousekeepingReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface HousekeepingReportRepository
        extends JpaRepository<HousekeepingReport, Long> {

    Optional<HousekeepingReport> findByReportDate(LocalDate reportDate);
}
