package za.co.tfradebe.vendingmachine.payment.exception;

public class NotEnoughMoneyLoaded extends RuntimeException {
    public NotEnoughMoneyLoaded(String message) {
        super(message);
    }
}
