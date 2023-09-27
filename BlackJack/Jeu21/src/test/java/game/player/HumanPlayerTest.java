package game.player;

import game.card.Card;
import game.card.hand.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import util.Display;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class HumanPlayerTest {

    private HumanPlayer playerUnderTest;

    @Mock
    private Hand mockHand;
    @Mock
    private Display mockDisplay;

    @BeforeEach
    void setUp() {
        mockDisplay = mock(Display.class);
        mockHand = mock(Hand.class);
        playerUnderTest = new HumanPlayer(mockHand, mockDisplay);
    }

    @Test
    void givenAHumanPlayer_whenReplyIsS_thenDecisionIsStand() {
        // GIVEN
        // See setUp()
        when(mockDisplay.promptUntilCorrectFormat(anyString(), anyString())).thenReturn("s");

        // WHEN
        Decision decision = playerUnderTest.play();

        // THEN
        assertThat(decision).isEqualTo(Decision.STAND);
    }

    @Test
    void givenAHumanPlayer_whenReplyIsH_thenDecisionIsHit() {
        // GIVEN
        // See setUp()
        when(mockDisplay.promptUntilCorrectFormat(anyString(), anyString())).thenReturn("h");

        // WHEN
        Decision decision = playerUnderTest.play();

        // THEN
        assertThat(decision).isEqualTo(Decision.HIT);
    }

    @Test
    void givenAHumanPlayer_whenReplyIsOther_thenDecisionIsNull() {
        // GIVEN
        // See setUp()
        when(mockDisplay.promptUntilCorrectFormat(anyString(), anyString())).thenReturn("OTHER");

        // WHEN
        Decision decision = playerUnderTest.play();

        // THEN
        assertThat(decision).isEqualTo(null);
    }

    @Test
    void givenAHumanPlayer_whenAddingCardToHand_thenCardIsAddedToHand() {
        // GIVEN
        // See setUp()
        Card mockCard = mock(Card.class);

        // WHEN
        playerUnderTest.addCardToHand(mockCard);

        // THEN
        verify(mockHand, only()).addCard(any(Card.class));
        verify(mockHand, times(1)).addCard(any(Card.class));
    }

    @Test
    void givenAHumanPlayer_whenGettingHand_thenPlayerHandIsReturned() {
        // GIVEN
        // See setUp()

        // WHEN
        Hand returnedHand = playerUnderTest.getHand();

        // THEN
        assertThat(returnedHand).isEqualTo(mockHand);
    }
}
