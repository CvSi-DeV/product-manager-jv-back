import nc.cvsi.productmanager.model.Admin;
import nc.cvsi.productmanager.model.Categorie;
import nc.cvsi.productmanager.model.Client;
import nc.cvsi.productmanager.model.Produit;
import nc.cvsi.productmanager.service.Catalogue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        //Créer des catégories
        List<Categorie> categories = new ArrayList<>();
        Categorie catVetements = new Categorie("Vetements");
        categories.add(catVetements);
        Categorie catVehicules = new Categorie("Vehicules");
        categories.add(catVehicules);
        Categorie catMeubles = new Categorie("Meubles");
        categories.add(catMeubles);
        Categorie catDivers = new Categorie("Divers");
        categories.add(catDivers);

        //Créer des produits
        Produit tShirt = new Produit("T-Shirt", 49.9, 50, catVetements);
        Produit renault = new Produit("Renault", 15490D, 5, catVehicules);
        Produit armoire = new Produit("Armoire", 159D, 15, catMeubles);
        Produit ampoule = new Produit("Ampoule", 159D, 15, catDivers);

        Client premierClient = new Client("pc@test.com", "Premier Client");
        Client secondClient = new Client("sc@test.com", "Second Client");

        Admin premierAdmin = new Admin("pa@test.com", "Premier Admin");
        Admin secondAdmin = new Admin("sa@test.com", "Second Admin");

        //Création du catalogue
        Catalogue monCatalogue = new Catalogue();

        //Ajout des produits
        monCatalogue.addProduit(tShirt);
        System.out.println("nombre de produits dans le catalogue :" + monCatalogue.produits().size());

        monCatalogue.addProduit(armoire);
        System.out.println("nombre de produits dans le catalogue :" + monCatalogue.produits().size());

        monCatalogue.addProduit(ampoule);
        System.out.println("nombre de produits dans le catalogue :" + monCatalogue.produits().size());

        //recherche du produit id=2
        Optional<Produit> pID2 = monCatalogue.trouverParId(2L);
        pID2.ifPresent(p -> System.out.println("le produit id 2 est " + p.getNom()));

        //recherche par catégorie
        List<Produit> vehiculesList = monCatalogue.trouverParCategorie(catVehicules);
        System.out.println("nombre de véhicule : " + vehiculesList.size());

        List<Produit> meublesList = monCatalogue.trouverParCategorie(catMeubles);
        System.out.println("nombre de meuble : " + meublesList.size());

        //tester l'exception
        try {
            Produit pID3 = monCatalogue.trouverParIdOuLever(3L);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //Valeur totale du stock
        System.out.println("Valeur totale du stock :" + monCatalogue.valeurTotaleStock());

        //décrémenter stock ok
        monCatalogue.decrementerStock(1L, 10);
        monCatalogue.trouverParId(1L).ifPresent(p -> System.out.println("le produit id 1 est " + p.getNom() + " stock " + p.getStock()));

        //décrémenter stock exception
        monCatalogue.decrementerStock(2L, 4000);
    }
}
