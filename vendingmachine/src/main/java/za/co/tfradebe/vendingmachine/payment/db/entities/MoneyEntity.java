package za.co.tfradebe.vendingmachine.payment.db.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.tfradebe.vendingmachine.payment.db.AMOUNT;

@Entity
@Table(name="LOADED_MONEY")
@Data
@NoArgsConstructor
public class MoneyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private AMOUNT amount;
    private Integer quantity;

    public MoneyEntity(AMOUNT amount, Integer quantity){
        this.amount = amount;
        this.quantity = quantity;
    }
}
