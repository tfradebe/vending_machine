package za.co.tfradebe.vendingmachine.inventory.db.repos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
public class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;

    @Test
    public void test_find_all(){
        var products = productRepo.findAll();
        assertNotNull(products);
        assertEquals(5, products.size());
    }
    @Test
    public void test_find_all_as_Map(){
        var products = productRepo.findAllAsMap();
        assertNotNull(products);
        assertEquals(5, products.size());
    }

}
