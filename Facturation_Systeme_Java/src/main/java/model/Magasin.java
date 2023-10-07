package model;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Double.parseDouble;

/**
 * Établissement commercial où l'on expose et vend des marchandises.
 */
public class Magasin {

    private List<Facture> listeDeFactures;
    private Comptable comptable;
    private double totalDesDons;
    private static final DecimalFormat formatEnDollard = new DecimalFormat("0.00");

    public Magasin() {
        this.listeDeFactures = new ArrayList<>();
        this.totalDesDons = 0.00;
    }

    public Magasin(Comptable comptable) {
        this.listeDeFactures = new ArrayList<>();
        this.comptable = comptable;
        this.totalDesDons = 0.00;
    }

    public List<Facture> getListeDeFactures() {
        return listeDeFactures;
    }

    public void setListeDeFactures(List<Facture> listeDeFactures) {
        this.listeDeFactures = listeDeFactures;
    }

    public Comptable getComptable() {
        return comptable;
    }

    public void setComptable(Comptable comptable) {
        this.comptable = comptable;
    }

    public double getTotalDesDons() {
        return totalDesDons;
    }

    public void setTotalDesDons(double totalDesDons) {
        this.totalDesDons = totalDesDons;
    }

    private void mettreAJourLeTotalDesDons() {
        formatEnDollard.setRoundingMode(RoundingMode.DOWN);
        setTotalDesDons(Double.parseDouble(formatEnDollard.format(calculTotalDesDons()).replace(',', '.')));
        System.out.println();
    }

    /**
     * Lorsqu'une facture est créée, une partie de son total avec taxes est ajouté aux dons de charité de la façon mathématique suivante:
     * Dons de charité = 2 % * (total avec taxes - frais liés au mode de paiement).
     * @return la valeur totale des dons
     */
    private double calculTotalDesDons() {
        double totalAvecTaxesDesFactures = 0;
        double totalDesFraisLieAuModeDePaiement = 0;

        for (Facture facture : listeDeFactures) {
            totalAvecTaxesDesFactures += parseDouble(facture.getTotalAvecTaxes().replace(',', '.'));
            totalDesFraisLieAuModeDePaiement += parseDouble(facture.getTotalAvecTaxes().replace(',', '.')) * facture.getModeDePaiement().getFrais();
        }

        return (0.02 * (totalAvecTaxesDesFactures - totalDesFraisLieAuModeDePaiement));
    }

    /**
     * Ajoute la facture passée en paramètre à la liste de facture du magasin
     * puis mets à jour le total des dons
     */
    public void ajouterFacture(Facture nouvelleFacture) {
        listeDeFactures.add(nouvelleFacture);
        mettreAJourLeTotalDesDons();
    }

}
