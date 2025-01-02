package shop.market.shopwarehouseservice.warehouse.service;

import shop.market.shopwarehouseservice.warehouse.entity.WarehouseItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.market.shopwarehouseservice.warehouse.repository.WarehouseRepository;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    public WarehouseItem addItem(String product, int quantity) {
        WarehouseItem item = new WarehouseItem();
        item.setProduct(product);
        item.setQuantity(quantity);
        return warehouseRepository.save(item);
    }

    public Iterable<WarehouseItem> getAllItems() {
        return warehouseRepository.findAll();
    }

    public WarehouseItem updateItem(Long id, String product, int quantity) {
        WarehouseItem item = warehouseRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        item.setProduct(product);
        item.setQuantity(quantity);
        return warehouseRepository.save(item);
    }

    public void deleteItem(Long id) {
        warehouseRepository.deleteById(id);
    }
}
