package za.co.tfradebe.vendingmachine.inventory.mapper;

import org.mapstruct.Mapper;
import za.co.tfradebe.vendingmachine.inventory.db.entities.ProductEntity;
import za.co.tfradebe.vendingmachine.inventory.api.v1.dto.ProductDao;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDao map(ProductEntity productEntity);
    List<ProductDao> map(List<ProductEntity> productEntities);
}
