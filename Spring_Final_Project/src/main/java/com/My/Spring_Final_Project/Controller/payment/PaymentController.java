package com.My.Spring_Final_Project.Controller.payment;

import com.My.Spring_Final_Project.DTO.payment.PaymentRequestDTO;
import com.My.Spring_Final_Project.DTO.payment.PaymentResponseDTO;
import com.My.Spring_Final_Project.DTO.payment.PaymentStatsDTO;
import com.My.Spring_Final_Project.Service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;


import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    // Create a new payment
    @PostMapping
    public ResponseEntity<PaymentResponseDTO> create(@RequestBody PaymentRequestDTO request) {
        return ResponseEntity.ok(paymentService.createPayment(request));
    }

    // Get a payment by ID
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPayment(id));
    }

    // Get all payments
    @GetMapping
    public ResponseEntity<List<PaymentResponseDTO>> getAll() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    // Update a payment
    @PutMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> update(
            @PathVariable Long id,
            @RequestBody PaymentRequestDTO request
    ) {
        return ResponseEntity.ok(paymentService.updatePayment(id, request));
    }

    // Cancel a payment (Soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        paymentService.cancelPayment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/admin/payment-stats")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    public ResponseEntity<PaymentStatsDTO> getPaymentStats() {
        PaymentStatsDTO stats = paymentService.getPaymentStats();
        return ResponseEntity.ok(stats);
    }

//    @GetMapping("/my")
//    public ResponseEntity<List<PaymentResponseDTO>> myPayments(
//            Authentication authentication
//    ) {
//        String email = authentication.getName(); // JWT থেকে email
//        return ResponseEntity.ok(paymentService.getMyPayments(email));
//    }


    @GetMapping("/my")
    public ResponseEntity<List<PaymentResponseDTO>> myPayments() {
        return ResponseEntity.ok(paymentService.getMyPayments());
    }

}
