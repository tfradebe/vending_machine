package za.co.tfradebe.vendingmachine.inventory.api.v1.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequestData {
    @NotNull(message = "id is required")
    private Long id;
    @NotNull(message = "quantity is required")
    private Integer quantityRequired;
}
