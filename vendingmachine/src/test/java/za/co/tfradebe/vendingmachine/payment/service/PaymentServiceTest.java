package za.co.tfradebe.vendingmachine.payment.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.co.tfradebe.vendingmachine.payment.db.AMOUNT;
import za.co.tfradebe.vendingmachine.payment.db.entities.MoneyEntity;
import za.co.tfradebe.vendingmachine.payment.db.repos.MoneyRepo;
import za.co.tfradebe.vendingmachine.payment.exception.NotEnoughMoneyLoaded;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
        var moneyOnTheMachine = new HashMap<AMOUNT,Integer>();
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
        var moneyOnTheMachine = new HashMap<AMOUNT,Integer>();
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
        var moneyOnTheMachine = new HashMap<AMOUNT,Integer>();
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

        when(moneyRepo.findAll()).thenReturn(getMoneyEntities());

        var totalInCart = 40;
        var moneyInserted = List.of(FIFTY_RAND);

        var result = paymentService.calculateChange(totalInCart,moneyInserted);

        assertNotNull(result);
        assertEquals(1,result.size());
    }

    @Test
    public void test_updateMoneyOnTheMachine(){
        var entities = getMoneyEntities();

        var newMoneyOnTheMachine = new HashMap<AMOUNT,Integer>();
        newMoneyOnTheMachine.put(TWO_HUNDRED_RAND,5);
        newMoneyOnTheMachine.put(ONE_HUNDRED_RAND,5);
        newMoneyOnTheMachine.put(FIFTY_RAND,5);
        newMoneyOnTheMachine.put(TWENTY_RAND,5);
        newMoneyOnTheMachine.put(TEN_RAND,5);
        newMoneyOnTheMachine.put(FIVE_RAND,5);
        newMoneyOnTheMachine.put(TWO_RAND,5);
        newMoneyOnTheMachine.put(ONE_RAND,5);

        paymentService.updateMoneyOnTheMachine(entities,newMoneyOnTheMachine);

        for(var entity: entities){
            assertEquals(5,entity.getQuantity());
        }
    }

    @Test
    public void test_addInsertedMoneyToTheMachine(){
        var moneyOnTheMachine = new HashMap<AMOUNT,Integer>();
        moneyOnTheMachine.put(TWO_HUNDRED_RAND,10);
        moneyOnTheMachine.put(ONE_HUNDRED_RAND,10);
        moneyOnTheMachine.put(FIFTY_RAND,10);
        moneyOnTheMachine.put(TWENTY_RAND,10);
        moneyOnTheMachine.put(TEN_RAND,10);
        moneyOnTheMachine.put(FIVE_RAND,10);
        moneyOnTheMachine.put(TWO_RAND,10);
        moneyOnTheMachine.put(ONE_RAND,10);

        var insertedMoney = List.of(TWO_RAND,ONE_RAND,FIFTY_RAND,TWENTY_RAND,TEN_RAND,FIVE_RAND,TWO_HUNDRED_RAND,ONE_HUNDRED_RAND);

        paymentService.addInsertedMoneyToTheMachine(insertedMoney,moneyOnTheMachine);

        for(var money: insertedMoney){
            assertEquals(11,moneyOnTheMachine.get(money));
        }
    }

    private List<MoneyEntity> getMoneyEntities(){

        var moneyOnTheMachine = new HashMap<AMOUNT,Integer>();
        moneyOnTheMachine.put(TWO_HUNDRED_RAND,10);
        moneyOnTheMachine.put(ONE_HUNDRED_RAND,10);
        moneyOnTheMachine.put(FIFTY_RAND,10);
        moneyOnTheMachine.put(TWENTY_RAND,10);
        moneyOnTheMachine.put(TEN_RAND,10);
        moneyOnTheMachine.put(FIVE_RAND,10);
        moneyOnTheMachine.put(TWO_RAND,10);
        moneyOnTheMachine.put(ONE_RAND,10);

        return moneyOnTheMachine.entrySet().stream().map(amountIntegerEntry -> new MoneyEntity(amountIntegerEntry.getKey(),amountIntegerEntry.getValue())).collect(Collectors.toList());
    }

}
