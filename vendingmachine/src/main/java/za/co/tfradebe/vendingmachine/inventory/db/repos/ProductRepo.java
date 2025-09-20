package za.co.tfradebe.vendingmachine.inventory.db.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import za.co.tfradebe.vendingmachine.inventory.db.entities.ProductEntity;

import java.util.List;

public interface ProductRepo extends CrudRepository<ProductEntity, Long> {

    @Query("SELECT p FROM ProductEntity p")
    List<ProductEntity> findAll();
}
