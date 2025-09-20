package za.co.tfradebe.vendingmachine.inventory.endpoint.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import za.co.tfradebe.vendingmachine.inventory.endpoint.dto.ErrorDetail;
import za.co.tfradebe.vendingmachine.inventory.endpoint.dto.ProductRequest;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ProductValidation {

    public List<ErrorDetail> validate(ProductRequest productRequest){
        var errors = new ArrayList<ErrorDetail>();
        if(productRequest == null || productRequest.getData() == null || productRequest.getData().isEmpty()){
            log.error("event=INVALID_REQUEST, Request data missing");
            errors.add(createErrorDetails("INVALID_REQUEST","Request data missing"));
        } else {
            for (int i = 0; i < productRequest.getData().size(); i++) {
                if ( productRequest.getData().get(i) != null && (productRequest.getData().get(i).getId() == null || productRequest.getData().get(i).getId() < 0)) {
                    log.error("event=INVALID_REQUEST_ID_FIELD, Invalid field: {}", productRequest.getData().get(i).toString());
                    errors.add(createErrorDetails("INVALID_REQUEST_ID_FIELD", String.format("Request id of index %d is invalid",i)));
                }
                if (productRequest.getData().get(i) != null && (productRequest.getData().get(i).getQuantityRequired() == null || productRequest.getData().get(i).getId() < 1)) {
                    log.error("event=INVALID_REQUEST_QUANTITY_FIELD, Invalid field: {}", productRequest.getData().get(i).toString());
                    errors.add(createErrorDetails("INVALID_REQUEST_QUANTITY_FIELD", String.format("Request quantity of index %d is invalid",i)));
                }
            }
        }
        return errors;
    }

    private ErrorDetail createErrorDetails(String code, String message, String... details){
        var error = new ErrorDetail();
        error.setCode("INVALID_REQUEST");
        error.setMessage("Request null body");
        return error;
    }
}
