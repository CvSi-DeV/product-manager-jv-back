package nc.cvsi.productmanager.exception;

import java.util.function.Supplier;

public class ProduitNonTrouveException extends RuntimeException{
    public ProduitNonTrouveException(String message) {
        super(message);
    }
}
