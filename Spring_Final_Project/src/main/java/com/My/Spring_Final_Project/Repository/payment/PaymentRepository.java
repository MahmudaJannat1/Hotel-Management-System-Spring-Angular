package com.My.Spring_Final_Project.Repository.payment;


import com.My.Spring_Final_Project.DTO.payment.PaymentStatsDTO;
import com.My.Spring_Final_Project.Entity.auth.User;
import com.My.Spring_Final_Project.Entity.payment.Payment;
import com.My.Spring_Final_Project.Enum.payment.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Admin / general
    List<Payment> findByIsActiveTrue();


    Long countByStatus(PaymentStatus status);

    @Query("""
SELECT COALESCE(SUM(p.amount), 0)
FROM Payment p
WHERE p.status = com.My.Spring_Final_Project.Enum.payment.PaymentStatus.COMPLETED
""")
    BigDecimal sumTotalRevenue();



    // ===== USER PAYMENT HISTORY =====
    List<Payment> findByUsers_UserNameAndIsActiveTrue(String userName);


    long countByUsers_UserName(String userName);

    long countByUsers_UserNameAndStatus(String userName, PaymentStatus status);
    List<Payment> findByUsers(User users);


    Optional<Payment> findFirstByBookingIdOrderByPaymentDateDesc(Long bookingId);
}

