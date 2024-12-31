package shop.market.shoppaymentservice.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.market.shoppaymentservice.payment.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
