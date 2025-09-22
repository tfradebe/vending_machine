package za.co.tfradebe.vendingmachine.payment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import za.co.tfradebe.vendingmachine.error.ErrorDetail;
import za.co.tfradebe.vendingmachine.payment.api.v1.PaymentController;
import za.co.tfradebe.vendingmachine.payment.api.v1.dto.*;

import java.util.List;

@ControllerAdvice(assignableTypes = {PaymentController.class})

public class PaymentApiExceptionHandler {

    @ExceptionHandler(NotEnoughMoneyLoaded.class)
    public ResponseEntity<PaymentResponse> notEnoughMoneyLoaded(NotEnoughMoneyLoaded e){
        var code = "INSUFFICIENT_MONEY_IN_MACHINE_ERROR";
        return new ResponseEntity<>(createProductResponse(code, e.getMessage(), List.of(createErrorDetails(code,e.getMessage()))), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<PaymentResponse> handleException(Exception e){
        var code = "GENERAL_ERROR";
        return new ResponseEntity<>(createProductResponse(code, e.getMessage(), List.of(createErrorDetails(code,e.getMessage()))), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private PaymentResponse createProductResponse(String code, String message, List<ErrorDetail> errors){
        var response = new PaymentResponse();
        response.setStatus("ERROR");
        response.setCode(code);
        response.setError(errors);
        return response;
    }

    private ErrorDetail createErrorDetails(String code, String message){
        var error = new ErrorDetail();
        error.setCode(code);
        error.setMessage(message);
        return error;
    }
}
