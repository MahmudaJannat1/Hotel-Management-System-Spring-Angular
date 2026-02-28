package com.My.Spring_Final_Project.Service.payment;

import com.My.Spring_Final_Project.DTO.payment.InvoiceResponseDTO;
import com.My.Spring_Final_Project.DTO.payment.PaymentRequestDTO;
import com.My.Spring_Final_Project.DTO.payment.PaymentResponseDTO;
import com.My.Spring_Final_Project.DTO.payment.PaymentStatsDTO;
import com.My.Spring_Final_Project.Entity.auth.User;
import com.My.Spring_Final_Project.Entity.booking.Booking;
import com.My.Spring_Final_Project.Entity.payment.Invoice;
import com.My.Spring_Final_Project.Entity.payment.Payment;
import com.My.Spring_Final_Project.Enum.Event.PaymentMethod;
import com.My.Spring_Final_Project.Enum.booking.BookingStatus;
import com.My.Spring_Final_Project.Enum.payment.PaymentStatus;
import com.My.Spring_Final_Project.Repository.auth.UserRepository;
import com.My.Spring_Final_Project.Repository.booking.BookingRepository;
import com.My.Spring_Final_Project.Repository.payment.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final InvoiceService invoiceService;

    @Override
    public PaymentResponseDTO createPayment(PaymentRequestDTO request) {
        // 1️⃣ Validate Booking
        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // 2️⃣ Get logged-in user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 3️⃣ Create Payment
        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setUsers(user);
        payment.setAmount(request.getAmount() != null ? BigDecimal.valueOf(request.getAmount()) : BigDecimal.ZERO);
        payment.setPaymentMethod(request.getMethod() != null ? request.getMethod() : PaymentMethod.CASH);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setStatus(PaymentStatus.COMPLETED);

        paymentRepository.save(payment);

        // 4️⃣ ✅ Update Booking status to CONFIRMED if payment completed
        booking.setStatus(BookingStatus.CONFIRMED);
        bookingRepository.save(booking);

        // 4️⃣ Generate Invoice linked with Payment
        InvoiceResponseDTO invoiceDTO = invoiceService.generateInvoice(payment);
        return mapToResponse(payment);

    }

    @Override
    public PaymentResponseDTO getPayment(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        return mapToResponse(payment);
    }

    @Override
    public List<PaymentResponseDTO> getAllPayments() {
        return paymentRepository.findByIsActiveTrue().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentResponseDTO updatePayment(Long id, PaymentRequestDTO request) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        if (request.getAmount() != null) {
            payment.setAmount(BigDecimal.valueOf(request.getAmount()));
        }
        if (request.getMethod() != null) {
            payment.setPaymentMethod(request.getMethod());
        }

        paymentRepository.save(payment);
        return mapToResponse(payment);
    }

    @Override
    public void cancelPayment(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        payment.setIsActive(false);  // Soft delete
        paymentRepository.save(payment);
    }

    @Override
    public PaymentStatsDTO getPaymentStats() {
        PaymentStatsDTO dto = new PaymentStatsDTO();
        dto.setTotalPayments(paymentRepository.count());
        dto.setCompletedPayments(paymentRepository.countByStatus(PaymentStatus.COMPLETED));
        dto.setPendingPayments(paymentRepository.countByStatus(PaymentStatus.PENDING));
        dto.setCancelledPayments(paymentRepository.countByStatus(PaymentStatus.CANCELLED));
        dto.setTotalRevenue(paymentRepository.sumTotalRevenue());
        return dto;
    }

    @Override
    public List<PaymentResponseDTO> getMyPayments() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Payment> payments = paymentRepository.findByUsers(user);
        return payments.stream().map(this::mapToResponse).toList();
    }

    @Override
    public PaymentStatsDTO getMyInvoiceStats() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        long total = paymentRepository.countByUsers_UserNameAndStatus(username, PaymentStatus.COMPLETED);
        long pending = paymentRepository.countByUsers_UserNameAndStatus(username, PaymentStatus.PENDING);

        return PaymentStatsDTO.builder()
                .totalInvoices(total)
                .pendingInvoices(pending)
                .build();
    }

    // Helper: map Payment → PaymentResponseDTO
    private PaymentResponseDTO mapToResponse(Payment payment) {
        PaymentResponseDTO dto = new PaymentResponseDTO();
        dto.setId(payment.getId());
        dto.setAmount(payment.getAmount() != null ? payment.getAmount().doubleValue() : null);
        dto.setMethod(payment.getPaymentMethod());
        dto.setPaymentDate(payment.getPaymentDate());
        dto.setBookingId(payment.getBooking().getId());

        // ✅ Access Invoice via Payment directly
        if (payment.getInvoice() != null) {
            dto.setInvoiceId(payment.getInvoice().getId());
            dto.setInvoiceNumber(payment.getInvoice().getInvoiceNumber());
            dto.setTotalAmount(payment.getInvoice().getTotalAmount() != null ?
                    payment.getInvoice().getTotalAmount().toString() : null);
        }

        return dto;
    }
}
