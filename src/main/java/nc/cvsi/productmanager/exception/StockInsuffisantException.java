package nc.cvsi.productmanager.exception;

public class StockInsuffisantException extends RuntimeException{
    public StockInsuffisantException(String message) {
        super(message);
    }
}
