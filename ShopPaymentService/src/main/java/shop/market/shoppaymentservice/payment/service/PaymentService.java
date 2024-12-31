package shop.market.shoppaymentservice.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import shop.market.shoppaymentservice.payment.entity.Payment;
import shop.market.shoppaymentservice.payment.repository.PaymentRepository;

import java.math.BigDecimal;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public Payment createPayment(Long orderId, BigDecimal amount) {
        Payment payment = new Payment();
        payment.setOrderId(orderId);
        payment.setAmount(amount);
        payment.setStatus("PAID");
        paymentRepository.save(payment);

        kafkaTemplate.send("payment-topic", payment);

        return payment;
    }

    public Iterable<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
