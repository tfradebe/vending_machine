package za.co.tfradebe.vendingmachine.inventory.endpoint.exception;

import lombok.Data;
import za.co.tfradebe.vendingmachine.inventory.endpoint.dto.ErrorDetail;

import java.util.List;

@Data
public class ValidationException extends RuntimeException {

    private final List<ErrorDetail> errors;

    public ValidationException(String message, List<ErrorDetail> errors){
        super(message);
        this.errors = errors;
    }
}
