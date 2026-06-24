package nc.cvsi.productmanager.model;

public class Client extends Utilisateur {
    public Client(String email, String nom){
        super(email, nom);
    }
    @Override
    public String role() {
        return "CLIENT";
    }
    @Override
    public void  genererUnRapport() {
        System.out.println("Générer un rapport");
    }
}
