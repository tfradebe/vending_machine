package za.co.tfradebe.vendingmachine.dispense.api.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import za.co.tfradebe.vendingmachine.error.ErrorDetail;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DispenseResponse {
    private String status;
    private String code;
    private List<ErrorDetail> error;
}
