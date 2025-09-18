package za.co.tfradebe.vendingmachine.inventory.db.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProductEntity {
    @Id
    private Long id;
    private String name;
    private double price;
    private int quantity;
}
