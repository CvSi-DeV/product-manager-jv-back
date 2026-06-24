package nc.cvsi.productmanager.model;

import java.util.Objects;
import java.util.Optional;

public class Produit implements Remisable {
    private final String nom;
    private Long id;
    private Double prix;
    private int stock;
    private Categorie categorie;
    private Double pourcentagePromo;

    //Constructeur
    public Produit(String nom, double prix, int stock, Categorie categorie) {
        this(nom, prix, stock, categorie, null);
    }

    public Produit(String nom, Double prix, int stock, Categorie categorie, Double pourcentagePromo) {
        this.nom = nom;
        this.prix = prix;
        this.stock = stock;
        this.categorie = categorie;
        this.pourcentagePromo = pourcentagePromo;
    }

    //Getter
    public String getNom() {
        return this.nom;
    }

    public Double getPrix() {
        return this.prix;
    }

    public int getStock() {
        return this.stock;
    }

    //Setter
    public void setStock(int stock) {
        this.stock = stock;
    }

    public Categorie getCategorie() {
        return this.categorie;
    }

    public Double getPourcentagePromo() {
        return this.pourcentagePromo;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void appliquerPromo(Double pourcentagePromo) {
        this.pourcentagePromo = pourcentagePromo;
    }

    public void retirerPromo() {
        this.pourcentagePromo = null;
    }

    /**
     * @return le prix avec ou sans remise
     */
    public Double getPrixFinal() {
        return Optional.ofNullable(pourcentagePromo).isPresent() ? prixRemise(pourcentagePromo) : this.prix;
    }

    @Override
    public double prixRemise(double pourcentage) {
        return this.prix - (this.prix * pourcentage / 100);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produit produit)) return false;
        return Objects.equals(id, produit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

