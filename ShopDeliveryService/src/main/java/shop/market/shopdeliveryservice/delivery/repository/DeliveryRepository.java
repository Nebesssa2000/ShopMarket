package shop.market.shopdeliveryservice.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.market.shopdeliveryservice.delivery.entity.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
