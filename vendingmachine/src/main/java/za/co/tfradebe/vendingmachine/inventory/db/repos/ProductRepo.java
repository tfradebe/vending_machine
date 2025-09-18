package za.co.tfradebe.vendingmachine.inventory.db.repos;

import org.springframework.data.repository.CrudRepository;
import za.co.tfradebe.vendingmachine.inventory.db.entities.ProductEntity;

import java.util.List;

public interface ProductRepo extends CrudRepository<ProductEntity, Long> {

    List<ProductEntity> findAll();
}
