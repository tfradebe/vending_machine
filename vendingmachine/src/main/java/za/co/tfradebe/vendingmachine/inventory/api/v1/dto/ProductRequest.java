package za.co.tfradebe.vendingmachine.inventory.api.v1.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class ProductRequest {
    @NotNull
    @NotEmpty
    private List<ProductRequestData> data;
}
