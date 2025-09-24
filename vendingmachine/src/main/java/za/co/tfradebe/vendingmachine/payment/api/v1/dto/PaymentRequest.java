package za.co.tfradebe.vendingmachine.payment.api.v1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import za.co.tfradebe.vendingmachine.payment.db.AMOUNT;

import java.util.List;

@Getter
@Setter
public class PaymentRequest {
    private Integer cartTotal;
    @NotNull( message = "Money is required")
    @NotEmpty( message = "Money is required")
    private List<AMOUNT> moneyInserted;
}
