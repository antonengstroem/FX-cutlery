package FXcutlery.exceptionhandling;

public class IsoNotTradeableOnDateException extends RuntimeException {
    public IsoNotTradeableOnDateException(String message) {
        super(message);
    }
}
