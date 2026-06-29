package nc.cvsi.productmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProduitNonTrouveException.class)
    public ResponseEntity<String> handleProduitNonTrouveException(ProduitNonTrouveException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(StockInsuffisantException.class)
    public ResponseEntity<String> handleStockInsuffisantException(StockInsuffisantException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(CategorieNonTrouveException.class)
    public ResponseEntity<String> handleCategorieNonTrouveException(CategorieNonTrouveException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
