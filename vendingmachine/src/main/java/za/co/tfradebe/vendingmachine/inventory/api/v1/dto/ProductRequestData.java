package za.co.tfradebe.vendingmachine.inventory.api.v1.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequestData {
    @NotNull
    private Long id;
    @NotNull
    private Integer quantityRequired;
}
