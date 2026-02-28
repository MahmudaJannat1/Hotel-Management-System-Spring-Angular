package com.My.Spring_Final_Project.Service.payment;

import com.My.Spring_Final_Project.DTO.payment.InvoiceResponseDTO;
import com.My.Spring_Final_Project.DTO.payment.InvoiceStatsDTO;
import com.My.Spring_Final_Project.Entity.payment.Payment;

public interface InvoiceService {

    // Payment থেকে invoice generate
    InvoiceResponseDTO generateInvoice(Payment payment);

    // Booking ID থেকে invoice generate (optional, legacy support)
    InvoiceResponseDTO generateInvoice(Long bookingId);

    // Explicit method for Payment
    InvoiceResponseDTO generateInvoiceForPayment(Payment payment);

    // Get invoice by id
    InvoiceResponseDTO getInvoice(Long id);

    // Invoice stats
    InvoiceStatsDTO getInvoiceStats();
}
