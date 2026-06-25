package nc.cvsi.productmanager.exception;

public class ProduitNonTrouveException extends RuntimeException{
    public ProduitNonTrouveException(String message) {
        super(message);
    }
}
