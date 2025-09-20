package za.co.tfradebe.vendingmachine.inventory.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDao {
    private Long id;
    private String name;
    private double price;
    private int quantity;
}
