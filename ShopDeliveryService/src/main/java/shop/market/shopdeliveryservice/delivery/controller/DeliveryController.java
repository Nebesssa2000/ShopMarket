package shop.market.shopdeliveryservice.delivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.market.shopdeliveryservice.delivery.entity.Delivery;
import shop.market.shopdeliveryservice.delivery.service.DeliveryService;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @PostMapping
    public Delivery createDelivery(@RequestParam Long orderId) {
        return deliveryService.createDelivery(orderId);
    }

    @GetMapping
    public Iterable<Delivery> getAllDeliveries() {
        return deliveryService.getAllDeliveries();
    }
}
