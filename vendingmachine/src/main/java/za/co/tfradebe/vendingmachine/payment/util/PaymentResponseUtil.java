package za.co.tfradebe.vendingmachine.payment.util;

import za.co.tfradebe.vendingmachine.payment.api.v1.dto.PaymentDao;
import za.co.tfradebe.vendingmachine.payment.api.v1.dto.PaymentResponse;
import za.co.tfradebe.vendingmachine.payment.db.AMOUNT;

import java.util.List;

public class PaymentResponseUtil {

    private PaymentResponseUtil(){}

    public static PaymentResponse createSuccessResponse(List<AMOUNT> change){
        var response = new PaymentResponse();
        response.setData(new PaymentDao(calculateAmount(change),change));
        response.setStatus("SUCCESS");
        return response;
    }

    public static Integer calculateAmount(List<AMOUNT> money){
        return money.stream().mapToInt(amount -> amount.getAmount()).sum();
    }

}
