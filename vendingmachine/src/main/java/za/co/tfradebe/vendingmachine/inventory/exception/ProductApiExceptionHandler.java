package za.co.tfradebe.vendingmachine.inventory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import za.co.tfradebe.vendingmachine.inventory.api.v1.ProductController;
import za.co.tfradebe.vendingmachine.error.ErrorDetail;
import za.co.tfradebe.vendingmachine.inventory.api.v1.dto.ProductResponse;

import java.util.List;


@ControllerAdvice(assignableTypes = {ProductController.class})
public class ProductApiExceptionHandler {

    @ExceptionHandler(NotEnoughQuantityException.class)
    public ResponseEntity<ProductResponse> notEnoughQuantityExceptionHandlerException(NotEnoughQuantityException e){
        var code = "NO_ENOUGH_STOCK";
        return new ResponseEntity<>(createProductResponse(code, e.getMessage(),List.of(createErrorDetails(code,e.getMessage()))), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ProductResponse> validationHandlerException(ValidationException e){
        return new ResponseEntity<>(createProductResponse("VALIDATION_ERROR", e.getMessage(),e.getErrors()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProductResponse> handleException(Exception e){
        var code = "GENERAL_ERROR";
        return new ResponseEntity<>(createProductResponse(code, e.getMessage(),List.of(createErrorDetails(code,e.getMessage()))), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ProductResponse createProductResponse( String code, String message, List<ErrorDetail> errors){
        var response = new ProductResponse();
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
