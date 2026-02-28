package com.My.Spring_Final_Project.Service.payment;

import com.My.Spring_Final_Project.DTO.payment.*;
import com.My.Spring_Final_Project.Entity.booking.Booking;
import com.My.Spring_Final_Project.Entity.payment.*;
import com.My.Spring_Final_Project.Enum.payment.PaymentStatus;
import com.My.Spring_Final_Project.Repository.booking.BookingItemRepository;
import com.My.Spring_Final_Project.Repository.booking.BookingRepository;
import com.My.Spring_Final_Project.Repository.payment.InvoiceItemRepository;
import com.My.Spring_Final_Project.Repository.payment.InvoiceRepository;
import com.My.Spring_Final_Project.Repository.payment.PaymentRepository;
import com.My.Spring_Final_Project.Service.common.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final PaymentRepository paymentRepository;
    private final EmailService emailService;

    // Payment থেকে invoice generate
    @Override
    public InvoiceResponseDTO generateInvoice(Payment payment) {
        return generateInvoiceForPayment(payment);
    }

    @Override
    public InvoiceResponseDTO generateInvoiceForPayment(Payment payment) {
        Booking booking = payment.getBooking();

        // 1️⃣ Create Invoice
        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber("INV-" + UUID.randomUUID().toString().substring(0, 8));
        invoice.setInvoiceDate(LocalDateTime.now());
        invoice.setBooking(booking);
        invoice.setTotalAmount(payment.getAmount());

        // 🔥 VERY IMPORTANT: link BOTH sides
        invoice.setPayment(payment);
        payment.setInvoice(invoice);

        // 2️⃣ Create InvoiceItem
        InvoiceItem item = new InvoiceItem();
        item.setInvoice(invoice);
        item.setDescription("Booking Payment");
        item.setQuantity(1);
        item.setAmount(payment.getAmount());

        invoice.setItems(List.of(item));

        // 3️⃣ Save invoice (payment auto-updated because of transaction)
        invoiceRepository.save(invoice);

        // 4️⃣ Send Email
        String customerEmail = booking.getCustomer().getEmail();
        if (customerEmail == null || customerEmail.isBlank() || !customerEmail.contains("@")) {
            customerEmail = "mahmudajannat890@gmail.com";
        }

        emailService.sendInvoiceEmail(
                customerEmail,
                "Your Booking Invoice: " + invoice.getInvoiceNumber(),
                "<h3>Invoice Number: " + invoice.getInvoiceNumber() + "</h3>"
                        + "<p>Total Amount: " + invoice.getTotalAmount() + "</p>"
                        + "<p>Booking Code: " + booking.getBookingCode() + "</p>"
        );

        // 5️⃣ Return DTO
        return mapToResponse(invoice);
    }

    // Legacy
    @Override
    public InvoiceResponseDTO generateInvoice(Long bookingId) {
        Payment payment = paymentRepository
                .findFirstByBookingIdOrderByPaymentDateDesc(bookingId)
                .orElseThrow(() -> new RuntimeException("Payment not found for booking"));

        return generateInvoice(payment);
    }

    @Override
    public InvoiceResponseDTO getInvoice(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        return mapToResponse(invoice);
    }

    @Override
    public InvoiceStatsDTO getInvoiceStats() {
        InvoiceStatsDTO stats = new InvoiceStatsDTO();
        stats.setTotalInvoices(paymentRepository.count());
        stats.setPendingInvoices(paymentRepository.countByStatus(PaymentStatus.PENDING));
        stats.setPaidInvoices(paymentRepository.countByStatus(PaymentStatus.COMPLETED));
        stats.setCancelledInvoices(paymentRepository.countByStatus(PaymentStatus.CANCELLED));
        return stats;
    }

    private InvoiceResponseDTO mapToResponse(Invoice invoice) {
        InvoiceResponseDTO dto = new InvoiceResponseDTO();
        dto.setId(invoice.getId());
        dto.setInvoiceNumber(invoice.getInvoiceNumber());
        dto.setInvoiceDate(invoice.getInvoiceDate());
        dto.setBookingId(invoice.getBooking().getId());
        dto.setTotalAmount(invoice.getTotalAmount());

        List<InvoiceItemResponseDTO> items = invoice.getItems().stream().map(i -> {
            InvoiceItemResponseDTO d = new InvoiceItemResponseDTO();
            d.setDescription(i.getDescription());
            d.setQuantity(i.getQuantity());
            d.setAmount(i.getAmount());
            return d;
        }).toList();

        dto.setItems(items);
        return dto;
    }
}
