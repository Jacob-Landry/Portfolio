package game.card.deck;

import game.card.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StandardDeckTest {

    private StandardDeck deckUnderTest;

    @BeforeEach
    void setUp() {
        deckUnderTest = new StandardDeck();
    }

    @Test
    void givenAnEmptyDeck_whenReGeneratingCards_shouldCreate52UniqueCardsForItsDeck() {
        //GIVEN
        //see setUp()

        //WHEN
        deckUnderTest.regenerateCards();

        //THEN
        assertThat(deckUnderTest.getCardList()).doesNotHaveDuplicates();
        assertThat(deckUnderTest.getCardList().size()).isEqualTo(52);

    }

    @Test
    void givenAStandardDeck_whenDrawingACard_shouldReturnACard() {
        //GIVEN
        //see setUp()
        deckUnderTest.regenerateCards();

        //WHEN
        Card drawnCard = deckUnderTest.draw();

        //THEN
        assertThat(drawnCard).isInstanceOf(Card.class);
    }

    @Test
    void givenAStandardDeck_whenDrawingACard_shouldNotRemainInTheDeck() {
        //GIVEN
        //see setUp()
        deckUnderTest.regenerateCards();

        //WHEN
        Card drawnCard = deckUnderTest.draw();

        //THEN
        assertThat(deckUnderTest.getCardList()).doesNotContain(drawnCard);
    }


}
