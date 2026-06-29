package nc.cvsi.productmanager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nc.cvsi.productmanager.model.Produit;
import nc.cvsi.productmanager.service.ProduitService;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {
    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping("")
    public List<Produit> getProduit() {
        return this.produitService.findAll();
    }

    @GetMapping("/{id}")
    public Produit getProduit(@PathVariable @NonNull Long id) {
        return this.produitService.findById(id);
    }

    @GetMapping("/categorie/{categorie}")
    public List<Produit> getProduitParCategorie(@PathVariable String categorie) {
        return this.produitService.findByCategorie(categorie);
    }

    @GetMapping("/promos")
    public List<Produit> getProduitEnPromo() {
        return this.produitService.produitsEnPromo();
    }

    @GetMapping("/valeur-totale")
    public Double getValeurTotale() {
        return this.produitService.valeurTotaleStock();
    }

    @PostMapping("")
    public ResponseEntity<Produit> addProduit(@RequestBody @NonNull Produit produit) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.produitService.save(produit));
    }

    @PatchMapping("/{id}/decrement/{quantite}")
    public ResponseEntity<Produit> decrementeProduit(@PathVariable long id, @PathVariable int quantite) {
        return ResponseEntity.status(HttpStatus.OK).body(this.produitService.decrementerStock(id, quantite));
    }
}
