package nc.cvsi.productmanager.model;

public abstract class Utilisateur {
    private final String email;
    private String name;

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

    public void setName(String name) {
        this.name = name;
    }

    abstract public String role();

    abstract public void genererUnRapport();

}
