package za.co.tfradebe.vendingmachine.inventory.api.v1;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.tfradebe.vendingmachine.inventory.api.v1.dto.ProductRequest;
import za.co.tfradebe.vendingmachine.inventory.api.v1.dto.ProductResponse;
import za.co.tfradebe.vendingmachine.inventory.service.ProductService;
import java.util.List;
import static za.co.tfradebe.vendingmachine.inventory.api.v1.dto.ProductResponseUtil.createSuccessResponse;

@RestController
@RequestMapping("/api/v1/products")
@Slf4j
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ProductResponse> findAll() {
        try {
            var products = productService.findAll();
            var response = createSuccessResponse(products);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Something went wrong with findAll ", e);
            throw e;
        }
    }

    @PostMapping
    public ResponseEntity<ProductResponse> checkout(@Valid @RequestBody ProductRequest request) {
        try {
            productService.checkout(request);
            return new ResponseEntity<>(createSuccessResponse(List.of()),HttpStatus.ACCEPTED);
        } catch (Exception e) {
            log.error("Something went wrong with checkout: {}", request.toString(), e);
            throw e;
        }
    }
}
