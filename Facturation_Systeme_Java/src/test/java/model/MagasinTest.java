package model;

import model.modeDePaiement.ModeDePaiement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

class MagasinTest {
    private Magasin magasinSousTest;
    @Mock
    private Comptable mockComptable;
    @Mock
    private Facture mockFacture;
    @Mock
    private ModeDePaiement mockModeDePaiement;

    @BeforeEach
    void setUp() {
        mockComptable = mock(Comptable.class);
        mockFacture = mock(Facture.class);
        mockModeDePaiement = mock(ModeDePaiement.class);

        magasinSousTest = new Magasin(mockComptable);
    }

    @Test
    void etantDonneUnMagasin_quandAjouterFacture_alorsMettreAJourTotalDesDons() {
        //ÉTANT DONNÉ
        when(mockFacture.getTotalAvecTaxes()).thenReturn("10");
        when(mockModeDePaiement.getFrais()).thenReturn(0.03);
        when(mockFacture.getModeDePaiement()).thenReturn(mockModeDePaiement);

        //QUAND
        magasinSousTest.ajouterFacture(mockFacture);
        double totalDesDons = magasinSousTest.getTotalDesDons();

        //ALORS
        assertThat(totalDesDons).isEqualTo(0.19);
    }
}