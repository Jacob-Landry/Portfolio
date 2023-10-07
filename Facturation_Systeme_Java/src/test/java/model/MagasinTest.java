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
    private Facture mockFacture1;
    @Mock
    private Facture mockFacture2;
    @Mock
    private Facture mockFacture3;

    @Mock
    private ModeDePaiement mockModeDePaiement;

    @BeforeEach
    void setUp() {
        mockComptable = mock(Comptable.class);
        mockFacture1 = mock(Facture.class);
        mockFacture2 = mock(Facture.class);
        mockFacture3 = mock(Facture.class);
        mockModeDePaiement = mock(ModeDePaiement.class);

        magasinSousTest = new Magasin(mockComptable);
    }

    @Test
    void etantDonneUnMagasin_quandAjouterFacture_alorsMettreAJourTotalDesDons() {
        //ÉTANT DONNÉ
        when(mockFacture1.getTotalAvecTaxes()).thenReturn("10");
        when(mockModeDePaiement.getFrais()).thenReturn(0.03);
        when(mockFacture1.getModeDePaiement()).thenReturn(mockModeDePaiement);

        //QUAND
        magasinSousTest.ajouterFacture(mockFacture1);
        double totalDesDons = magasinSousTest.getTotalDesDons();

        //ALORS
        assertThat(totalDesDons).isEqualTo(0.19);
    }

    @Test
    void etantUneListeDeFactureEtLeTotalDesDons_quandOnMettreAJourLePourcentageDesDons_alorsLePourcentageDesDonsEstMisAJourProprement() {
        //Etant Donne
        when(mockFacture1.getTotalAvecTaxes()).thenReturn("11");
        when(mockModeDePaiement.getFrais()).thenReturn(0.03);
        when(mockFacture1.getModeDePaiement()).thenReturn(mockModeDePaiement);

        when(mockFacture2.getTotalAvecTaxes()).thenReturn("114");
        when(mockModeDePaiement.getFrais()).thenReturn(0.03);
        when(mockFacture2.getModeDePaiement()).thenReturn(mockModeDePaiement);

        when(mockFacture3.getTotalAvecTaxes()).thenReturn("131");
        when(mockModeDePaiement.getFrais()).thenReturn(0.03);
        when(mockFacture3.getModeDePaiement()).thenReturn(mockModeDePaiement);

        //QUAND
        magasinSousTest.ajouterFacture(mockFacture1);
        magasinSousTest.ajouterFacture(mockFacture2);
        magasinSousTest.ajouterFacture(mockFacture3);
        magasinSousTest.mettreAJourLePourcentageDesDons();

        //Alors
        assertThat(magasinSousTest.getPourcentageDesDons()).isEqualTo(1.93);
    }
}