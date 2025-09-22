package za.co.tfradebe.vendingmachine.payment.db.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import za.co.tfradebe.vendingmachine.payment.db.AMOUNT;
import za.co.tfradebe.vendingmachine.payment.db.entities.MoneyEntity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface MoneyRepo extends CrudRepository<MoneyEntity, Long> {

    @Override
    @NonNull
    List<MoneyEntity> findAll();

    default Map<AMOUNT, Integer> findAllAsMap(){
        return findAll().stream().collect(Collectors.toMap(moneyEntity -> moneyEntity.getAmount() , moneyEntity -> moneyEntity.getQuantity()));
    }
}
