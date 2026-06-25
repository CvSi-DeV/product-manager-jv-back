package nc.cvsi.productmanager.model;

public class Admin extends Utilisateur {
    public Admin(String email, String nom) {
        super(email, nom);
    }

    @Override
    public String role() {
        return "ADMIN";
    }

    @Override
    public void genererUnRapport() {
        System.out.println("Rapport admin généré pour " + getName());
    }
}
