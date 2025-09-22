package za.co.tfradebe.vendingmachine.payment.api.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.tfradebe.vendingmachine.payment.db.AMOUNT;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDao {
    private Integer change;
    private List<AMOUNT> changeAsDenominators;
}
