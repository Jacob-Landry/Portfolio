package model;

import model.modeDePaiement.ModeDePaiement;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Pièce attestant de l'achat d'un bien, et qui en indique le nom de l'acheteur, le prix et le mode de paiement.
 */
public class Facture {
    private String nomAcheteur;
    private double totalSansTaxes;
    private double montantDesTaxes;
    private ModeDePaiement modeDePaiement;
    private String totalAvecTaxes;
    private static final DecimalFormat formatEnDollard = new DecimalFormat("0.00");

    public Facture(String nomAcheteur, double totalSansTaxes, double montantDesTaxes, ModeDePaiement modeDePaiement) {
        this.nomAcheteur = nomAcheteur;
        this.totalSansTaxes = totalSansTaxes;
        this.montantDesTaxes = montantDesTaxes;
        this.modeDePaiement = modeDePaiement;
        mettreAJourLeTotalAvecTaxes();
    }

    public String getNomAcheteur() {
        return nomAcheteur;
    }

    public void setNomAcheteur(String nomAcheteur) {
        this.nomAcheteur = nomAcheteur;
    }

    public double getTotalSansTaxes() {
        return totalSansTaxes;
    }

    public void setTotalSansTaxes(double totalSansTaxes) {
        this.totalSansTaxes = totalSansTaxes;
    }

    public double getMontantDesTaxes() {
        return montantDesTaxes;
    }

    public void setMontantDesTaxes(double montantDesTaxes) {
        this.montantDesTaxes = montantDesTaxes;
    }

    public ModeDePaiement getModeDePaiement() {
        return modeDePaiement;
    }

    public void setModeDePaiement(ModeDePaiement modeDePaiement) {
        this.modeDePaiement = modeDePaiement;
    }

    public String getTotalAvecTaxes() {
        return totalAvecTaxes;
    }

    public void setTotalAvecTaxes(String totalAvecTaxes) {
        this.totalAvecTaxes = totalAvecTaxes;
    }

    /**
     * Met à jour le total de la facture incluant les taxes en faisant le calcule (total sans les taxe + montant Des Taxes).
     * Le montant est ensuite formater pour respecter la présentation voulue par le client (deux décimal après la virgule).
     */
    public void mettreAJourLeTotalAvecTaxes(){
        formatEnDollard.setRoundingMode(RoundingMode.UP);
        setTotalAvecTaxes(formatEnDollard.format(totalSansTaxes + montantDesTaxes));
    }

    @Override
    public String toString() {
        return "Facture{" +
                "nomAcheteur='" + nomAcheteur + '\'' +
                ", totalSansTaxe=" + totalSansTaxes +
                ", montantDesTaxe=" + montantDesTaxes +
                ", methodeDePaiement=" + modeDePaiement +
                '}';
    }
}
