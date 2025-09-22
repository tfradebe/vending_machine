package za.co.tfradebe.vendingmachine.payment.api.v1.dto;

import lombok.Getter;
import lombok.Setter;
import za.co.tfradebe.vendingmachine.error.ErrorDetail;
import java.util.List;

@Getter
@Setter
public class PaymentResponse {
    private String status;
    private String code;
    private List<ErrorDetail> error;
    private PaymentDao data;
}
