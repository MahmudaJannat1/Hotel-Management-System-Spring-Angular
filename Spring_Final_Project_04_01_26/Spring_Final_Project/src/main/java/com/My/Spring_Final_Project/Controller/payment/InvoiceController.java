package com.My.Spring_Final_Project.Controller.payment;

import com.My.Spring_Final_Project.DTO.payment.InvoiceResponseDTO;
import com.My.Spring_Final_Project.DTO.payment.InvoiceStatsDTO;
import com.My.Spring_Final_Project.Service.payment.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    // Generate invoice from booking
    @PostMapping("/booking/{bookingId}")
    public ResponseEntity<InvoiceResponseDTO> generate(@PathVariable Long bookingId) {
        return ResponseEntity.ok(invoiceService.generateInvoice(bookingId));
    }

    // Get invoice
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponseDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(invoiceService.getInvoice(id));
    }

    // ===== Invoice Statistics (Admin) =====
    @GetMapping("/stats")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    public ResponseEntity<InvoiceStatsDTO> getInvoiceStats() {
        return ResponseEntity.ok(invoiceService.getInvoiceStats());
    }
}
