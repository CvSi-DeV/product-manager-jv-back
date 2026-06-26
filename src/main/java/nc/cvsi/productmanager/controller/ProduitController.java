package nc.cvsi.productmanager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nc.cvsi.productmanager.service.Catalogue;
import nc.cvsi.productmanager.model.*;
import java.util.List;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {
    private final Catalogue catalogue;

    public ProduitController(Catalogue catalogue) {
        this.catalogue = catalogue;
    }

    @GetMapping("")
    public List<Produit> getProduit() {
        return this.catalogue.produits();
    }

    @GetMapping("/{id}")
    public Produit getProduit(@PathVariable Long id) {
        return this.catalogue.trouverParIdOuLever(id);
    }

    @GetMapping("/categorie/{categorie}")
    public List<Produit> getProduitParCategorie(@PathVariable String categorie) {
        Categorie cCategorie = new Categorie(categorie);
        return this.catalogue.trouverParCategorie(cCategorie);
    }

    @GetMapping("/promos")
    public List<Produit> getProduitEnPromo() {
        return this.catalogue.produitsEnPromo();
    }

    @GetMapping("/valeur-totale")
    public Double getValeurTotale() {
        return this.catalogue.valeurTotaleStock();
    }

    @PostMapping("")
    public ResponseEntity<Produit> addProduit(@RequestBody Produit produit) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.catalogue.addProduit(produit));
    }
}
