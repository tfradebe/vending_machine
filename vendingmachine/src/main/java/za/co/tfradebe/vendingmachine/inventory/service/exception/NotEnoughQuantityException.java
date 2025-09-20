package za.co.tfradebe.vendingmachine.inventory.service.exception;

public class NotEnoughQuantityException extends RuntimeException {
    public NotEnoughQuantityException(String message){
        super(message);
    }
}
