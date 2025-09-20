package za.co.tfradebe.vendingmachine.inventory.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.tfradebe.vendingmachine.inventory.dto.ProductDao;
import za.co.tfradebe.vendingmachine.inventory.service.ProductService;

import java.util.List;

@RestController()
@RequestMapping("/api/products")
public class ProductEndpoint {

    private final ProductService productService;

    public ProductEndpoint(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDao> findAll(){
        var products = productService.findAll();
        return products;
    }
}
