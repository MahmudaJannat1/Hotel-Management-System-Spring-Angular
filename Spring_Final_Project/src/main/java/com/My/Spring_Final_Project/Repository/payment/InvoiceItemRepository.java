package com.My.Spring_Final_Project.Repository.payment;

import com.My.Spring_Final_Project.Entity.payment.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {
}
