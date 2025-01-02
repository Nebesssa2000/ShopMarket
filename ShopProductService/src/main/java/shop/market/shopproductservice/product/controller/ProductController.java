package shop.market.shopproductservice.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.market.shopproductservice.product.entity.Product;
import shop.market.shopproductservice.product.service.ProductService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product createProduct(@RequestParam String name, @RequestParam String description, @RequestParam BigDecimal price, @RequestParam int quantity) {
        return productService.createProduct(name, description, price, quantity);
    }

    @GetMapping
    public Iterable<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestParam String name, @RequestParam String description, @RequestParam BigDecimal price, @RequestParam int quantity) {
        return productService.updateProduct(id, name, description, price, quantity);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
