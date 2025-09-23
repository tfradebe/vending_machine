package za.co.tfradebe.vendingmachine.inventory.api.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDao {
    private Long id;
    private String name;
    private String location;
    private int price;
    private int quantity;
}
