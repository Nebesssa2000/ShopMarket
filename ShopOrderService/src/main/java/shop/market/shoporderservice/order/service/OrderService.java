package shop.market.shoporderservice.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import shop.market.shoporderservice.order.entity.Order;
import shop.market.shoporderservice.order.repository.OrderRepository;

import java.math.BigDecimal;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public Order createOrder(String product, int quantity, BigDecimal price) {
        Order order = new Order();
        order.setProduct(product);
        order.setQuantity(quantity);
        order.setPrice(price);
        order.setStatus("CREATED");
        orderRepository.save(order);

        kafkaTemplate.send("order-topic", order);

        return order;
    }

    public Iterable<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
