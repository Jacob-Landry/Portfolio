package game.card.deck;

import game.card.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuadDeckTest {

    private QuadDeck deckUnderTest;

    @BeforeEach
    void setUp() {
        deckUnderTest = new QuadDeck();
    }

    @Test
    void givenAnEmptyDeck_whenReGeneratingCards_shouldCreate208CardsFor4DecksInItsDeck() {
        //GIVEN
        //see setUp()

        //WHEN
        deckUnderTest.regenerateCards();

        //THEN
        assertThat(deckUnderTest.getCardList().size()).isEqualTo(4 * 52);
        assertThat(deckUnderTest.getCardList().stream().distinct().count()).isEqualTo(52);

    }

    @Test
    void givenAQuadDeck_whenDrawingACard_shouldReturnACard() {
        //GIVEN
        //see setUp()
        deckUnderTest.regenerateCards();

        //WHEN
        Card drawnCard = deckUnderTest.draw();

        //THEN
        assertThat(drawnCard).isInstanceOf(Card.class);
    }

    @Test
    void givenAQuadDeck_whenDrawingACard_shouldReduceDeckSize() {
        //GIVEN
        //see setUp()
        deckUnderTest.regenerateCards();

        //WHEN
        Card drawnCard = deckUnderTest.draw();

        //THEN
        assertThat(deckUnderTest.getCardList().size()).isEqualTo((4 * 52) - 1);
        assertThat(deckUnderTest.getCardList()
                .stream()
                .filter(x -> x.equals(drawnCard))
                .count()).isEqualTo(3);
    }
}
