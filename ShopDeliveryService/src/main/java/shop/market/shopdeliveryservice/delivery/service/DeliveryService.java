package shop.market.shopdeliveryservice.delivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import shop.market.shopdeliveryservice.delivery.entity.Delivery;
import shop.market.shopdeliveryservice.delivery.repository.DeliveryRepository;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public Delivery createDelivery(Long orderId) {
        Delivery delivery = new Delivery();
        delivery.setOrderId(orderId);
        delivery.setStatus("IN_DELIVERY");
        deliveryRepository.save(delivery);

        kafkaTemplate.send("delivery-topic", delivery);

        scheduler.schedule(() -> updateDeliveryStatus(delivery), 1, TimeUnit.MINUTES);

        return delivery;
    }

    private void updateDeliveryStatus(Delivery delivery) {
        Random random = new Random();
        if (random.nextBoolean()) {
            delivery.setStatus("DELIVERED");
        } else {
            delivery.setStatus("DELAYED");
        }
        deliveryRepository.save(delivery);
        kafkaTemplate.send("delivery-topic", delivery);
    }

    public Iterable<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }
}
