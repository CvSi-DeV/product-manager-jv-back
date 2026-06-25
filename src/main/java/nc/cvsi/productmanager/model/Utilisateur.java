package nc.cvsi.productmanager.model;

public abstract class Utilisateur {
    private final String email;
    private final String name;

    public Utilisateur(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    abstract public String role();

    abstract public void genererUnRapport();

}
