package nc.cvsi.productmanager.service;

import nc.cvsi.productmanager.exception.ProduitNonTrouveException;
import nc.cvsi.productmanager.exception.StockInsuffisantException;
import nc.cvsi.productmanager.model.Categorie;
import nc.cvsi.productmanager.model.Produit;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Catalogue {
    private final List<Produit> produits;
    private Long nextId = 1L;

    public Catalogue() {
        produits = new ArrayList<>();
    }

    /**
     * @param produit Incrémente l'Id du produit
     *                Ajoute un produit au catalogue
     */
    public void addProduit(Produit produit) {
        produit.setId(nextId++);
        produits.add(produit);
    }

    public List<Produit> produits() {
        return produits;
    }

    public Optional<Produit> trouverParId(Long id) {
        return produits.stream().filter(p -> Objects.equals(p.getId(), id)).findFirst();
    }

    public Produit trouverParIdOuLever(Long id) {
        return trouverParId(id).orElseThrow(() -> new ProduitNonTrouveException("Produit non trouvé"));
    }

    public List<Produit> trouverParCategorie(Categorie categorie) {
        return produits.stream().filter(p -> p.getCategorie().equals(categorie)).collect(Collectors.toList());
    }

    public Double valeurTotaleStock() {
        return produits.stream().mapToDouble(p -> p.getPrix() * p.getStock()).sum();
    }

    public void decrementerStock(Long produitId, Integer quantite) {
        Produit produit = trouverParIdOuLever(produitId);
        if (produit.getStock() < quantite) throw new StockInsuffisantException("Produit insuffisant");
        produit.setStock((produit.getStock() - quantite));
    }
}