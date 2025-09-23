package za.co.tfradebe.vendingmachine.inventory.db.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PRODUCT")
@Getter
@Setter
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private int price;
    private int quantity;
}
