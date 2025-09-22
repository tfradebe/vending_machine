package za.co.tfradebe.vendingmachine.payment.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.co.tfradebe.vendingmachine.payment.db.AMOUNT;
import za.co.tfradebe.vendingmachine.payment.db.repos.MoneyRepo;
import za.co.tfradebe.vendingmachine.payment.exception.NotEnoughMoneyLoaded;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static za.co.tfradebe.vendingmachine.payment.db.AMOUNT.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    private MoneyRepo moneyRepo;

    @InjectMocks
    private PaymentService paymentService;

    @Test
    public void test_createChange_8(){
        var change = 8;
        Map<AMOUNT,Integer> moneyOnTheMachine = new HashMap<>();
        moneyOnTheMachine.put(TWO_HUNDRED_RAND,10);
        moneyOnTheMachine.put(ONE_HUNDRED_RAND,10);
        moneyOnTheMachine.put(FIFTY_RAND,10);
        moneyOnTheMachine.put(TWENTY_RAND,10);
        moneyOnTheMachine.put(TEN_RAND,10);
        moneyOnTheMachine.put(FIVE_RAND,10);
        moneyOnTheMachine.put(TWO_RAND,10);
        moneyOnTheMachine.put(ONE_RAND,10);

        var result = paymentService.createChange(change,moneyOnTheMachine);

        assertNotNull(result);
        assertEquals(3,result.size());

        assertEquals(9,moneyOnTheMachine.get(FIVE_RAND));
        assertEquals(9,moneyOnTheMachine.get(TWO_RAND));
        assertEquals(9,moneyOnTheMachine.get(ONE_RAND));

    }

    @Test
    public void test_createChange_18(){
        var change = 18;
        Map<AMOUNT,Integer> moneyOnTheMachine = new HashMap<>();
        moneyOnTheMachine.put(TWO_HUNDRED_RAND,10);
        moneyOnTheMachine.put(ONE_HUNDRED_RAND,10);
        moneyOnTheMachine.put(FIFTY_RAND,10);
        moneyOnTheMachine.put(TWENTY_RAND,10);
        moneyOnTheMachine.put(FIVE_RAND,10);
        moneyOnTheMachine.put(TWO_RAND,10);
        moneyOnTheMachine.put(ONE_RAND,10);

        var result = paymentService.createChange(change,moneyOnTheMachine);

        assertNotNull(result);
        assertEquals(5,result.size());

        assertEquals(7,moneyOnTheMachine.get(FIVE_RAND));
        assertEquals(9,moneyOnTheMachine.get(TWO_RAND));
        assertEquals(9,moneyOnTheMachine.get(ONE_RAND));

    }

    @Test
    public void test_createChange_1(){
        var change = 1;
        Map<AMOUNT,Integer> moneyOnTheMachine = new HashMap<>();
        moneyOnTheMachine.put(TWO_HUNDRED_RAND,10);
        moneyOnTheMachine.put(ONE_HUNDRED_RAND,10);
        moneyOnTheMachine.put(FIFTY_RAND,10);
        moneyOnTheMachine.put(TWENTY_RAND,10);
        moneyOnTheMachine.put(TEN_RAND,10);
        moneyOnTheMachine.put(FIVE_RAND,10);
        moneyOnTheMachine.put(TWO_RAND,10);

        assertThrows(NotEnoughMoneyLoaded.class, ()->paymentService.createChange(change,moneyOnTheMachine));

    }

    @Test
    public void test_calculateChange(){
        Map<AMOUNT,Integer> moneyOnTheMachine = new HashMap<>();
        moneyOnTheMachine.put(TWO_HUNDRED_RAND,10);
        moneyOnTheMachine.put(ONE_HUNDRED_RAND,10);
        moneyOnTheMachine.put(FIFTY_RAND,10);
        moneyOnTheMachine.put(TWENTY_RAND,10);
        moneyOnTheMachine.put(TEN_RAND,10);
        moneyOnTheMachine.put(FIVE_RAND,10);
        moneyOnTheMachine.put(TWO_RAND,10);
        moneyOnTheMachine.put(ONE_RAND,10);

        when(moneyRepo.findAllAsMap()).thenReturn(moneyOnTheMachine);

        var totalInCart = 40;
        var moneyInserted = List.of(FIFTY_RAND);

        var result = paymentService.calculateChange(totalInCart,moneyInserted);

        assertNotNull(result);
        assertEquals(1,result.size());
    }

}
