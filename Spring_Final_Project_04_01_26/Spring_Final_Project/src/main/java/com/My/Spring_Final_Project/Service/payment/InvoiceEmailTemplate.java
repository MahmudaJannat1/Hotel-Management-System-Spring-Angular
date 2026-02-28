package com.My.Spring_Final_Project.Service.payment;

import java.math.BigDecimal;

public class InvoiceEmailTemplate {

    public static String build(
            String customerName,
            String invoiceNumber,
            BigDecimal totalAmount
    ) {
        return """
            <div style="font-family: Arial; padding:20px">
                <h2>Invoice Generated Successfully</h2>

                <p>Hello <strong>%s</strong>,</p>

                <p>Your payment was successful and invoice has been generated.</p>

                <table border="1" cellpadding="10" cellspacing="0">
                    <tr>
                        <td>Invoice Number</td>
                        <td>%s</td>
                    </tr>
                    <tr>
                        <td>Total Amount</td>
                        <td>৳ %s</td>
                    </tr>
                </table>

                <p>Thank you for choosing our hotel.</p>
                <p><strong>Hotel Management System</strong></p>
            </div>
            """.formatted(customerName, invoiceNumber, totalAmount);
    }
}
