package com.My.Spring_Final_Project.Repository.payment;

import com.My.Spring_Final_Project.Entity.payment.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
