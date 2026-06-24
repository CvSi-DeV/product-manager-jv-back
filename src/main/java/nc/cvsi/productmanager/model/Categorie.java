package nc.cvsi.productmanager.model;

import java.util.Objects;

public class Categorie {
    private final String nom;

    public Categorie(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categorie categorie)) return false;
        return Objects.equals(nom, categorie.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nom);
    }
}
