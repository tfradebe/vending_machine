package za.co.tfradebe.vendingmachine.payment.db.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.tfradebe.vendingmachine.payment.db.AMOUNT;
import za.co.tfradebe.vendingmachine.payment.db.entities.MoneyEntity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public interface MoneyRepo extends CrudRepository<MoneyEntity, Long> {
    @Override
    List<MoneyEntity> findAll();

}
