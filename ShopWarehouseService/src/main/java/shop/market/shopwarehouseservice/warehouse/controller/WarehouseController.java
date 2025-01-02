package shop.market.shopwarehouseservice.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.market.shopwarehouseservice.warehouse.entity.WarehouseItem;
import shop.market.shopwarehouseservice.warehouse.service.WarehouseService;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PostMapping
    public WarehouseItem addItem(@RequestParam String product, @RequestParam int quantity) {
        return warehouseService.addItem(product, quantity);
    }

    @GetMapping
    public Iterable<WarehouseItem> getAllItems() {
        return warehouseService.getAllItems();
    }

    @PutMapping("/{id}")
    public WarehouseItem updateItem(@PathVariable Long id, @RequestParam String product, @RequestParam int quantity) {
        return warehouseService.updateItem(id, product, quantity);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        warehouseService.deleteItem(id);
    }
}
