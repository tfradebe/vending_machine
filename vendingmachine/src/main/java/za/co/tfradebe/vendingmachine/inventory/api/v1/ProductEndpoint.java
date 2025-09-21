package za.co.tfradebe.vendingmachine.inventory.api.v1;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.tfradebe.vendingmachine.inventory.api.v1.dto.ProductRequest;
import za.co.tfradebe.vendingmachine.inventory.api.v1.dto.ProductResponse;
import za.co.tfradebe.vendingmachine.inventory.api.v1.dto.ResponseUtil;
import za.co.tfradebe.vendingmachine.inventory.service.ProductService;

@RestController()
@RequestMapping("/api/v1/products")
@Slf4j
public class ProductEndpoint {

    private final ProductService productService;

    public ProductEndpoint(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ProductResponse> findAll() {
        try {
            var products = productService.findAll();
            var response = ResponseUtil.createSuccessResponse(products);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Something went wrong with findAll ", e);
            throw e;
        }
    }

    @PostMapping
    public ResponseEntity<Object> checkout(@Valid ProductRequest request) {
        try {
            productService.checkout(request);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            log.error("Something went wrong with checkout: {}", request.toString(), e);
            throw e;
        }
    }
}
