package shop.market.shopwarehouseservice.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.market.shopwarehouseservice.warehouse.entity.WarehouseItem;

public interface WarehouseRepository extends JpaRepository<WarehouseItem, Long> {
}
