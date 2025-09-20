package za.co.tfradebe.vendingmachine.inventory.endpoint.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductRequest {
    private List<ProductRequestData> data;
}
