package nc.cvsi.productmanager.model;

import java.util.Objects;

public class Produit implements Remisable {
    private Long id;
    private final String nom;
    private Double prix;
    private int stock;
    private Categorie categorie;

    //Constructeur
    public Produit(String nom, Double prix, int stock, Categorie categorie) {
        this.nom = nom;
        this.prix = prix;
        this.stock = stock;
        this.categorie = categorie;
    }

    //Getter
    public String getNom() {
        return this.nom;
    }

    public  Double getPrix() {
        return this.prix;
    }

    public int getStock() {
        return this.stock;
    }

    public Categorie getCategorie() {
        return this.categorie;
    }

    public Long getId() {
        return this.id;
    }

    //Setter
    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setId(Long id) {
        this.id = id;
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

