package za.co.tfradebe.vendingmachine.payment.api.v1;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.tfradebe.vendingmachine.payment.api.v1.dto.PaymentRequest;
import za.co.tfradebe.vendingmachine.payment.api.v1.dto.PaymentResponse;
import za.co.tfradebe.vendingmachine.payment.service.PaymentService;

import static za.co.tfradebe.vendingmachine.payment.util.PaymentResponseUtil.createSuccessResponse;


@RestController
@RequestMapping("/api/v1/payment")
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
public ResponseEntity<PaymentResponse> process(@Valid PaymentRequest request){
        try{
            var response = paymentService.calculateChange(request.getCartTotal(),request.getMoneyInserted());
            return new ResponseEntity<>(createSuccessResponse(response), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Something went wrong with checkout: {}", request.toString(), e);
            throw e;
        }
    }

}
