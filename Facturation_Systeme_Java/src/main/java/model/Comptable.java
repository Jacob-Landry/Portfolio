package model;

import model.modeDePaiement.ModeDePaiement;

public class Comptable {

    public Facture creerNouvelleFacture(String nomAcheteur, double totalSansTaxes, double montantDesTaxes, ModeDePaiement modeDePaiement){

        return new Facture(nomAcheteur, totalSansTaxes, montantDesTaxes, modeDePaiement);
    }

}
