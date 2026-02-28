package com.My.Spring_Final_Project.Service.payment;

import com.My.Spring_Final_Project.DTO.payment.PaymentRequestDTO;
import com.My.Spring_Final_Project.DTO.payment.PaymentResponseDTO;
import com.My.Spring_Final_Project.DTO.payment.PaymentStatsDTO;

import java.util.List;

public interface PaymentService {
    PaymentResponseDTO createPayment(PaymentRequestDTO request);
    PaymentResponseDTO getPayment(Long id);
    List<PaymentResponseDTO> getAllPayments();
    PaymentResponseDTO updatePayment(Long id, PaymentRequestDTO request);
    void cancelPayment(Long id);// soft delete

    PaymentStatsDTO getPaymentStats();
    PaymentStatsDTO getMyInvoiceStats();
    List<PaymentResponseDTO> getMyPayments();


}
