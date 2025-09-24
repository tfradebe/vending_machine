package za.co.tfradebe.vendingmachine.payment.db.repos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
public class MoneyRepoTest {

    @Autowired
    private MoneyRepo moneyRepo;

    @Test
    public void test_find_all(){
        var money = moneyRepo.findAll();
        assertNotNull(money);
        assertEquals(7, money.size());
    }
}
