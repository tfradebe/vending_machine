package za.co.tfradebe.vendingmachine.inventory.service;

import org.springframework.stereotype.Service;
import za.co.tfradebe.vendingmachine.inventory.db.repos.ProductRepo;
import za.co.tfradebe.vendingmachine.inventory.endpoint.dto.ProductDao;
import za.co.tfradebe.vendingmachine.inventory.endpoint.dto.ProductRequest;
import za.co.tfradebe.vendingmachine.inventory.mapper.ProductMapper;
import za.co.tfradebe.vendingmachine.inventory.service.exception.NotEnoughQuantityException;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepo productRepo;
    private final ProductMapper productMapper;

    public ProductService(ProductRepo productRepo, ProductMapper productMapper) {
        this.productRepo = productRepo;
        this.productMapper = productMapper;
    }

    public List<ProductDao> findAll(){
          var entities = productRepo.findAll();
        return productMapper.map(entities);
    }

    public void checkout(ProductRequest request) {
        var productsMap = productRepo.findAllAsMap();
        for(var productRequestData: request.getData()){
            var product = productsMap.get(productRequestData.getId());
            if(product == null){
                continue;
            }
            if(productRequestData.getQuantityRequired() > product.getQuantity()){
                throw new NotEnoughQuantityException(String.format("Product: %s has quantity %d and %d are required. Not enough products",product.getId(),product.getQuantity(),productRequestData.getQuantityRequired()));
            }
            product.setQuantity(product.getQuantity()-productRequestData.getQuantityRequired());
            productRepo.save(product);
        }
    }
}
