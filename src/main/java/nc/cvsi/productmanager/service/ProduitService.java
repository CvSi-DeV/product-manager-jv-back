package nc.cvsi.productmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import nc.cvsi.productmanager.exception.CategorieNonTrouveException;
import nc.cvsi.productmanager.exception.ProduitNonTrouveException;
import nc.cvsi.productmanager.exception.StockInsuffisantException;
import nc.cvsi.productmanager.model.Categorie;
import nc.cvsi.productmanager.model.Produit;
import nc.cvsi.productmanager.repository.CategorieRepository;
import nc.cvsi.productmanager.repository.ProduitRepository;

@Service
public class ProduitService {
    private final ProduitRepository produitRepository;
    private final CategorieRepository categorieRepository;

    public ProduitService(ProduitRepository produitRepository, CategorieRepository categorieRepository) {
        this.produitRepository = produitRepository;
        this.categorieRepository = categorieRepository;
    }

    public List<Produit> findAll() {
        return this.produitRepository.findAll();
    }

    public Produit findById(long id) {
        return this.produitRepository.findById(id)
                .orElseThrow(() -> new ProduitNonTrouveException("Produit non trouvé"));
    }

    public List<Produit> findByCategorie(String categorie) {
        List<Produit> produits = this.produitRepository.findByCategorieNom(categorie);
        if (produits.isEmpty())
            throw new CategorieNonTrouveException("categorie : " + categorie + " non trouvée");
        return produits;
    }

    public Produit decrementerStock(long produitId, int quantite) {
        Produit saveProduit = this.findById(produitId);

        if (saveProduit.getStock() < quantite)
            throw new StockInsuffisantException("Quantité produit insuffisante");
        saveProduit.setStock(saveProduit.getStock() - quantite);
        saveProduit = this.produitRepository.save(saveProduit);

        return saveProduit;
    }

    public Double valeurTotaleStock() {
        List<Produit> produits = this.produitRepository.findAll();
        return produits.stream().mapToDouble(p -> p.getPrix() * p.getStock()).sum();
    }

    public Produit save(@NonNull Produit produitToSave) {
        String catToFind = produitToSave.getCategorie().getNom();

        Optional<Categorie> categorie = this.categorieRepository.findByNom(catToFind);
        // si l'optional contient une categorie, alors la mettre dans le produit. evite
        // de créer une nouvelle instance en table.
        categorie.ifPresent(c -> produitToSave.setCategorie(c));
        // forme concatener
        // this.categorieRepository.findByNom(catToFind).ifPresent(c->produitToSave.setCategorie(c));

        return this.produitRepository.save(produitToSave);
    }

    public List<Produit> produitsEnPromo() {
        return this.produitRepository.findByPourcentagePromoIsNotNull();
    }
}
