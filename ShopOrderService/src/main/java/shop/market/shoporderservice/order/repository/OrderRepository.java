package shop.market.shoporderservice.order.repository;

import org.springframework.stereotype.Repository;
import shop.market.shoporderservice.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
