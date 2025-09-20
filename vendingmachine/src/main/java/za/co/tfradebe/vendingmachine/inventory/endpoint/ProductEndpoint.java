package za.co.tfradebe.vendingmachine.inventory.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.tfradebe.vendingmachine.inventory.endpoint.dto.ProductRequest;
import za.co.tfradebe.vendingmachine.inventory.endpoint.dto.ProductResponse;
import za.co.tfradebe.vendingmachine.inventory.endpoint.dto.ResponseUtil;
import za.co.tfradebe.vendingmachine.inventory.endpoint.exception.ValidationException;
import za.co.tfradebe.vendingmachine.inventory.endpoint.validation.ProductValidation;
import za.co.tfradebe.vendingmachine.inventory.service.ProductService;

@RestController()
@RequestMapping("/api/v1/products")
@Slf4j
public class ProductEndpoint {

    private final ProductService productService;

    private final ProductValidation productValidation;

    public ProductEndpoint(ProductService productService, ProductValidation productValidation) {
        this.productService = productService;
        this.productValidation = productValidation;
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
    public ResponseEntity<Object> checkout(ProductRequest request) {
        try {
            var errors = productValidation.validate(request);
            if(!errors.isEmpty()){
                throw new ValidationException("VALIDATION_ERROR",errors);
            }
            productService.checkout(request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Something went wrong with update: {}", request.toString(), e);
            throw e;
        }
    }
}
