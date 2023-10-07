package model;

import model.modeDePaiement.ModeDePaiement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class FactureTest {
    private Facture factureSousTest;
    private String nomAcheteurTest;
    private double totalSansTaxesTest;
    private double montantDesTaxesTest;
    @Mock
    private ModeDePaiement mockModeDePaiement;

    @BeforeEach
    void setUp() {
        nomAcheteurTest = "Nom Test";
        totalSansTaxesTest = 57.34;
        montantDesTaxesTest = 3.16;
        mockModeDePaiement = mock(ModeDePaiement.class);

        factureSousTest = new Facture(nomAcheteurTest,totalSansTaxesTest,montantDesTaxesTest,mockModeDePaiement);
    }

    @Test
    void etantUnTotalSansTaxeEtUnMontantDeTaxes_quandOnMetAJourLeTotalAvecTaxes_alorsLeTotalAvecTaxeEstMisAJourProprement() {
        //Etant Donne

        //Quand
        factureSousTest.mettreAJourLeTotalAvecTaxes();

        //Alors
        assertThat(factureSousTest.getTotalAvecTaxes()).isEqualTo("60,50");
    }
}