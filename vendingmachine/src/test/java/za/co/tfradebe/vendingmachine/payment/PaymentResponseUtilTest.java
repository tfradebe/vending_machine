package za.co.tfradebe.vendingmachine.payment;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static za.co.tfradebe.vendingmachine.payment.db.AMOUNT.*;
import static za.co.tfradebe.vendingmachine.payment.db.AMOUNT.FIFTY_RAND;
import static za.co.tfradebe.vendingmachine.payment.db.AMOUNT.ONE_HUNDRED_RAND;
import static za.co.tfradebe.vendingmachine.payment.db.AMOUNT.TEN_RAND;
import static za.co.tfradebe.vendingmachine.payment.db.AMOUNT.TWENTY_RAND;
import static za.co.tfradebe.vendingmachine.payment.db.AMOUNT.TWO_HUNDRED_RAND;
import static za.co.tfradebe.vendingmachine.payment.util.PaymentResponseUtil.calculateAmount;

public class PaymentResponseUtilTest {


    @Test
    public void test_totalOfLoadedMoney(){
        var sum = calculateAmount(List.of(ONE_RAND, TWO_RAND, FIVE_RAND,TEN_RAND,TWENTY_RAND,FIFTY_RAND, ONE_RAND, ONE_HUNDRED_RAND,TWO_HUNDRED_RAND));
        assertEquals(389,sum);
    }


}
