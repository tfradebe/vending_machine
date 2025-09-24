package za.co.tfradebe.vendingmachine.payment.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import za.co.tfradebe.vendingmachine.payment.db.AMOUNT;
import za.co.tfradebe.vendingmachine.payment.db.entities.MoneyEntity;
import za.co.tfradebe.vendingmachine.payment.db.repos.MoneyRepo;
import za.co.tfradebe.vendingmachine.payment.exception.NotEnoughMoneyLoaded;
import java.util.*;
import java.util.stream.Collectors;

import static za.co.tfradebe.vendingmachine.payment.util.PaymentResponseUtil.calculateAmount;

@Service
@Slf4j
public class PaymentService {

    private final MoneyRepo moneyRepo;

    public PaymentService(MoneyRepo moneyRepo) {
        this.moneyRepo = moneyRepo;
    }

    public List<AMOUNT>  calculateChange(int totalPrice, List<AMOUNT> insertedAmount) {

        var moneyEntityListInTheMachine = moneyRepo.findAll();

        var moneyInTheMachine = moneyEntityListInTheMachine.stream().collect(Collectors.toMap(moneyEntity -> moneyEntity.getAmount() , moneyEntity -> moneyEntity.getQuantity()));

        addInsertedMoneyToTheMachine(insertedAmount, moneyInTheMachine);

        var totalLoaded = calculateAmount(insertedAmount);

        var change = totalLoaded - totalPrice;

        if(change < 0){
            throw new NotEnoughMoneyLoaded("Change exceeds money loaded");
        }

        var changeDenominators = createChange(change,moneyInTheMachine);

        updateMoneyOnTheMachine(moneyEntityListInTheMachine,moneyInTheMachine);

        moneyRepo.saveAll(moneyEntityListInTheMachine);

        return changeDenominators;
    }

    protected void updateMoneyOnTheMachine(List<MoneyEntity> moneyEntityListInTheMachine, Map<AMOUNT, Integer> moneyInTheMachine){
        for(var moneyEntity: moneyEntityListInTheMachine){
            var quantity = moneyInTheMachine.get(moneyEntity.getAmount());
            if(quantity >= 0){
                moneyEntity.setQuantity(quantity);
            }
        }
    }

    protected List<AMOUNT> createChange(int change, Map<AMOUNT, Integer> moneyOnTheMachine){
        List<AMOUNT> resultAmount = new ArrayList<>();
        var amounts = new ArrayList<>(moneyOnTheMachine.keySet());
        amounts.sort((o1, o2) -> o2.getAmount() - o1.getAmount());
        for(var amount: amounts){
            var value = (double) (change / amount.getAmount());
            if(value < 1){
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

    protected void addInsertedMoneyToTheMachine( List<AMOUNT> insertedAmount, Map<AMOUNT, Integer> moneyOnTheMachine){
        for(var amount: insertedAmount){
            moneyOnTheMachine.put(amount,moneyOnTheMachine.get(amount)+1);
        }
    }
}
