package za.co.tfradebe.vendingmachine.inventory.mapper;

import org.junit.jupiter.api.Test;
import za.co.tfradebe.vendingmachine.inventory.db.entities.ProductEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductMapperTest {

    private ProductMapper classUnderTest = new ProductMapperImpl();

    @Test
    public void test_product_to_productDao(){
        ProductEntity entity = new ProductEntity();
        entity.setId(1L);
        entity.setName("TestName");
        entity.setPrice(20);
        entity.setQuantity(10);

        var response = classUnderTest.map(entity);

        assertNotNull(response);
        assertEquals(1L, response.getId(),"id failed to test");
        assertEquals("TestName", response.getName(),"name failed to test");
        assertEquals(20, response.getPrice(),"price failed to test");
        assertEquals(10, response.getQuantity(),"quantity failed to test");
    }


    @Test
    public void test_product_to_productDao_list(){

        var list = List.of(new ProductEntity(),new ProductEntity());

        var response = classUnderTest.map(list);

        assertNotNull(response);

        assertEquals(2, response.size(), "Size of list not equal");

    }



}
