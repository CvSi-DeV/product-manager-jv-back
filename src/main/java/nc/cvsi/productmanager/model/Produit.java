package nc.cvsi.productmanager.model;

import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Produit implements Remisable {
    private String nom;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double prix;
    private int stock;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;
    private Double pourcentagePromo;

    // Constructeur
    protected Produit() {

    }

    public Produit(String nom, double prix, int stock, Categorie categorie) {
        this(nom, prix, stock, categorie, null);
    }

    @JsonCreator
    public Produit(@JsonProperty("nom") String nom,
            @JsonProperty("prix") Double prix,
            @JsonProperty("stock") int stock,
            @JsonProperty("categorie") Categorie categorie,
            @JsonProperty("pourcentagePromo") Double pourcentagePromo) {
        this.nom = nom;
        this.prix = prix;
        this.stock = stock;
        this.categorie = categorie;
        this.pourcentagePromo = pourcentagePromo;
    }

    // Getter
    public String getNom() {
        return this.nom;
    }

    public Double getPrix() {
        return this.prix;
    }

    public int getStock() {
        return this.stock;
    }

    // Setter
    public void setStock(int stock) {
        this.stock = stock;
    }

    public Categorie getCategorie() {
        return this.categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Double getPourcentagePromo() {
        return this.pourcentagePromo;
    }

    public Long getId() {
        return this.id;
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
        if (this == o)
            return true;
        if (!(o instanceof Produit produit))
            return false;
        return Objects.equals(id, produit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
