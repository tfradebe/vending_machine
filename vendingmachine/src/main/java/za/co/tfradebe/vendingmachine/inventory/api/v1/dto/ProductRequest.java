package za.co.tfradebe.vendingmachine.inventory.api.v1.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class ProductRequest {
    @NotNull( message = "data is required")
    @NotEmpty( message = "data is required")
    private List<ProductRequestData> data;
}
