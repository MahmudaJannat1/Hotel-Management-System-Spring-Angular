package com.My.Spring_Final_Project.Service.common;

import com.My.Spring_Final_Project.Entity.payment.Invoice;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendInvoiceEmail(String to, String subject, String htmlBody) {
        try {
            // ✅ STRONG validation
            if (to == null || to.isBlank() || !to.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                to = "mahmudajannat890@gmail.com"; // testing email
            }

            // ❗ Block fake domains like @pass
            if (to.endsWith("@pass")) {
                to = "mahmudajannat890@gmail.com";
            }

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlBody, true);

            mailSender.send(message);

            System.out.println("✅ Email sent to: " + to);

        } catch (Exception e) {
            System.out.println("❌ Email sending failed: " + e.getMessage());
        }
    }




//    public void sendInvoiceEmail(
//            String to,
//            String subject,
//            String htmlBody
//    ) {
//        try {
//            MimeMessage message = mailSender.createMimeMessage();
//            MimeMessageHelper helper =
//                    new MimeMessageHelper(message, true, "UTF-8");
//
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setText(htmlBody, true);
//
//            mailSender.send(message);
//
//        } catch (Exception e) {
//            // ❗ Email failure must not break payment/invoice flow
//            System.out.println("Email sending failed: " + e.getMessage());
//        }
//    }


}
