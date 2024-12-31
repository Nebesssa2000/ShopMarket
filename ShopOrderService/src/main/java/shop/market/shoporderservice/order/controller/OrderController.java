package shop.market.shoporderservice.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.market.shoporderservice.order.entity.Order;
import shop.market.shoporderservice.order.service.OrderService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestParam String product, @RequestParam int quantity, @RequestParam BigDecimal price) {
        return orderService.createOrder(product, quantity, price);
    }

    @GetMapping
    public Iterable<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
}