package game.card.hand;

import game.card.Card;
import game.card.Rank;
import game.card.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HandJeu21Test {

    private HandJeu21 handJeu21UnderTest;

    @Mock
    private Card mockCard;

    @Mock
    private Map<Rank, Integer> mockCardValueMap;

    @BeforeEach
    void setUp() {
        mockCardValueMap = mock(Map.class);
        mockCard = mock(Card.class);
        handJeu21UnderTest = new HandJeu21(mockCardValueMap);
    }

    @Test
    void givenAnEptyHand_whenInstanciating_thenCardListShouldBeEmpty() {
        // GIVEN
        // See setUp()

        // WHEN
        handJeu21UnderTest = new HandJeu21(mockCardValueMap);

        // THEN
        assertThat(handJeu21UnderTest.getCardList()).isEqualTo(new ArrayList<>());
    }

    @Test
    void givenACard_whenAddingItToTheHand_thenTheHandShouldContainTheCard() {
        // GIVEN
        // See setUp()

        // WHEN
        handJeu21UnderTest.addCard(mockCard);

        // THEN
        assertThat(handJeu21UnderTest.getCardList().get(0)).isEqualTo(mockCard);
    }

    @Test
    void givenSingleCardInHand_whenCalculatingHandValue_thenTheCardValueShouldBeReturned() {
        // GIVEN
        // See setUp();
        handJeu21UnderTest.addCard(mockCard);
        when(mockCard.getRank()).thenReturn(mock(Rank.class));
        when(mockCardValueMap.get(any(Rank.class))).thenReturn(1);

        // WHEN
        int value = handJeu21UnderTest.calculateValue();

        // THEN
        assertThat(value).isEqualTo(1);
    }

    @Test
    void givenMultipleCardsInHand_whenCalculatingHandValue_thenTheSumOfTheCardsValueShouldBeReturned() {
        // GIVEN
        // See setUp();
        Card arbitraryCard = new Card(Rank.ACE, Suit.CLUB);

        handJeu21UnderTest.addCard(mockCard);
        when(mockCard.getRank()).thenReturn(mock(Rank.class));

        handJeu21UnderTest.addCard(arbitraryCard);
        when(mockCardValueMap.get(any(Rank.class))).thenReturn(2);

        // WHEN
        int value = handJeu21UnderTest.calculateValue();

        // THEN
        assertThat(value).isEqualTo(4);
    }

    @Test
    void givenAHand_whenCallingClearCard_thenShouldClearTheCardOfTheHand() {
        // GIVEN
        // See setUp();
        handJeu21UnderTest.addCard(mockCard);
        handJeu21UnderTest.addCard(mockCard);

        // WHEN
        handJeu21UnderTest.emptyHand();

        // THEN
        assertThat(handJeu21UnderTest.getCardList()).isEmpty();
    }

    @Test
    void givenAHandWithACard_whenconvertingtoAString_thenProperStringShouldBeReturned() {
        // GIVEN
        // See setUp()
        handJeu21UnderTest.addCard(mockCard);
        when(mockCard.toString()).thenReturn("[CARD_STRING]");
        when(mockCard.getRank()).thenReturn(Rank.ACE);
        when(mockCardValueMap.get(any(Rank.class))).thenReturn(1);

        // WHEN
        String returnedString = handJeu21UnderTest.toString();

        // THEN
        assertThat(returnedString).isEqualTo("[CARD_STRING]\nValeur: 1");
    }

    @Test
    void givenAHandWithNoCards_whenconvertingtoAString_thenProperStringShouldBeReturned() {
        // GIVEN
        // See setUp()
        when(mockCard.toString()).thenReturn("[CARD_STRING]");

        // WHEN
        String returnedString = handJeu21UnderTest.toString();

        // THEN
        assertThat(returnedString).isEqualTo("Valeur: 0");
    }
}
