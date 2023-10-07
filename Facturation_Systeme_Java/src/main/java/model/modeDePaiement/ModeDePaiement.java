package model.modeDePaiement;

public abstract class ModeDePaiement {
    private double frais;
    private String nom;

    public ModeDePaiement(double frais, String nom) {
        this.frais = frais;
        this.nom = nom;
    }

    public double getFrais() {
        return frais;
    }

    public void setFrais(double frais) {
        this.frais = frais;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
