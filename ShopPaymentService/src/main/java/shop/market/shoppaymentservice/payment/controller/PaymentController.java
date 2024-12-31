package shop.market.shoppaymentservice.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.market.shoppaymentservice.payment.entity.Payment;
import shop.market.shoppaymentservice.payment.service.PaymentService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public Payment createPayment(@RequestParam Long orderId, @RequestParam BigDecimal amount) {
        return paymentService.createPayment(orderId, amount);
    }

    @GetMapping
    public Iterable<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }
}
