package za.co.tfradebe.vendingmachine.inventory.api.v1.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductResponse {
    private String status;
    private String code;
    private List<ErrorDetail> error;
    private List<ProductDao> data;
}
