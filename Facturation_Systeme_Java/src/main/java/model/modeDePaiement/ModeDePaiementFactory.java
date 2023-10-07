package model.modeDePaiement;

public class ModeDePaiementFactory implements ModeDePaiementCreator {
    @Override
    public ModeDePaiement creerModeDePaiement(String type) {
        return switch (type) {
            case "Carte de crédit" -> new CarteDeCredit(0.03, "Carte de crédit");
            case "Carte de débit" -> new CarteDeDebit(0.02, "Carte de débit");
            case "Argent Comptant" -> new ArgentComptant(0.00, "Argent Comptant");
            default -> throw new RuntimeException("Type de mode de paiement invalide");
        };
    }
}
