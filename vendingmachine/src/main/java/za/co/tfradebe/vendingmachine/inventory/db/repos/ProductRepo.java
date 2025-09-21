package za.co.tfradebe.vendingmachine.inventory.db.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import za.co.tfradebe.vendingmachine.inventory.db.entities.ProductEntity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface ProductRepo extends CrudRepository<ProductEntity, Long> {

    @Override
    @NonNull
    @Query("SELECT p FROM ProductEntity p")
    List<ProductEntity> findAll();

    default Map<Long, ProductEntity> findAllAsMap(){
        return findAll().stream().collect(Collectors.toMap(ProductEntity::getId, productEntity -> productEntity));
    }
}
