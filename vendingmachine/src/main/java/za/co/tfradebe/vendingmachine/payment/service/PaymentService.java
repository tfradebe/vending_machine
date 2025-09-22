package za.co.tfradebe.vendingmachine.payment.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import za.co.tfradebe.vendingmachine.payment.db.AMOUNT;
import za.co.tfradebe.vendingmachine.payment.db.repos.MoneyRepo;
import za.co.tfradebe.vendingmachine.payment.exception.NotEnoughMoneyLoaded;
import java.util.*;
import static za.co.tfradebe.vendingmachine.payment.util.PaymentResponseUtil.calculateAmount;

@Service
@Slf4j
public class PaymentService {

    private final MoneyRepo moneyRepo;

    public PaymentService(MoneyRepo moneyRepo) {
        this.moneyRepo = moneyRepo;
    }

    public List<AMOUNT>  calculateChange(int totalPrice, List<AMOUNT> loadedAmount) {

        var moneyOnTheMachine = moneyRepo.findAllAsMap();
        loadedAmount.stream().forEach(amount -> moneyOnTheMachine.put(amount,moneyOnTheMachine.get(amount)+1));

        var totalLoaded = calculateAmount(loadedAmount);

        var change = totalLoaded - totalPrice;

        if(change < 0){
            throw new NotEnoughMoneyLoaded("Change exceeds money loaded");
        }

        var moneyInTheMachine = moneyRepo.findAllAsMap();

        var changeDenominators = createChange(change,moneyInTheMachine);

        return changeDenominators;
    }

    protected List<AMOUNT> createChange(int change, Map<AMOUNT, Integer> moneyOnTheMachine){
        List<AMOUNT> resultAmount = new ArrayList<>();
        var amounts = new ArrayList<>(moneyOnTheMachine.keySet());
        Collections.sort(amounts,(o1, o2) -> o2.getAmount() - o1.getAmount());
        for(var amount: amounts){
            var value = (double) (change / amount.getAmount());
            if(value < 1){
                continue;
            } else if(value >=2 && moneyOnTheMachine.get(amount)>=value){
                var coinMultiple = (int) Math.floor(value);
                change = change - amount.getAmount()*coinMultiple;
                while(coinMultiple > 0){
                    resultAmount.add(amount);
                    moneyOnTheMachine.put(amount,moneyOnTheMachine.get(amount)-1);
                    coinMultiple--;
                }
            } else {
                change = change - amount.getAmount();
                resultAmount.add(amount);
                moneyOnTheMachine.put(amount,moneyOnTheMachine.get(amount)-1);
            }
        }
        if(change != 0){
            throw new NotEnoughMoneyLoaded("Change cannot be calculated accurately");
        }
        return resultAmount;
    }
}
