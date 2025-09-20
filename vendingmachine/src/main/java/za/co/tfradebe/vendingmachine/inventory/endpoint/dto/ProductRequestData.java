package za.co.tfradebe.vendingmachine.inventory.endpoint.dto;

import lombok.Data;

@Data
public class ProductRequestData {
    private Long id;
    private Integer quantityRequired;
}
