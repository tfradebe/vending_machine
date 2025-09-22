package za.co.tfradebe.vendingmachine.payment.db;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AMOUNT {
    TWO_HUNDRED_RAND(200),
    ONE_HUNDRED_RAND(100),
    FIFTY_RAND(50),
    TWENTY_RAND(20),
    TEN_RAND(10),
    FIVE_RAND(5),
    TWO_RAND(2),
    ONE_RAND(1);

    private Integer amount;

    private AMOUNT(Integer amount){
        this.amount = amount;
    }

    public int getAmount(){
        return amount;
    }

    @JsonValue
    public Integer getValue() {
        return amount;
    }

    @JsonCreator
    public static AMOUNT forValue(Integer value) {
        for (AMOUNT amount : AMOUNT.values()) {
            if (amount.amount.equals(value)) {
                return amount;
            }
        }
        throw new IllegalArgumentException("Unknown amount value: " + value);
    }

}
