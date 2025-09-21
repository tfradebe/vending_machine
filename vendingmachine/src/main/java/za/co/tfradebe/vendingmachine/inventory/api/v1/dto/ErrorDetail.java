package za.co.tfradebe.vendingmachine.inventory.api.v1.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ErrorDetail {
    private String code;
    private String message;
    private Map<String, String> details;
}
