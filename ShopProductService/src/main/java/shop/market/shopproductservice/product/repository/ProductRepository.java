package shop.market.shopproductservice.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.market.shopproductservice.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
