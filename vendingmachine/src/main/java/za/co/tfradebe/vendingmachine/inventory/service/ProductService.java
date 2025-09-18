package za.co.tfradebe.vendingmachine.inventory.service;

import org.springframework.stereotype.Service;
import za.co.tfradebe.vendingmachine.inventory.db.repos.ProductRepo;
import za.co.tfradebe.vendingmachine.inventory.dto.ProductDao;
import za.co.tfradebe.vendingmachine.inventory.mapper.ProductMapper;

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

}
