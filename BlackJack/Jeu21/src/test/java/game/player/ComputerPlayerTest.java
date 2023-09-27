package game.player;

import game.card.Card;
import game.card.hand.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ComputerPlayerTest {

    private ComputerPlayer playerUnderTest;

    @Mock
    private Hand mockHand;

    @Mock
    private ComputerStrategy mockStrategy;

    @BeforeEach
    void setUp() {
        mockHand = mock(Hand.class);
        mockStrategy = mock(ComputerStrategy.class);
        playerUnderTest = new ComputerPlayer(mockHand, mockStrategy);
    }

    @Test
    void givenNewPlayer_whenInstanciating_shouldProperlyAssignAttributes() {
        //GIVEN
        //see setUp()

        //WHEN
        // Constructor called in setUp()

        //THEN
        assertThat(playerUnderTest).isInstanceOf(ComputerPlayer.class);
        assertThat(playerUnderTest.getHand()).isEqualTo(mockHand);
    }

    @Test
    void givenAComputerPlayer_whenAddingCardToHand_thenCardIsAddedToHand() {
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
    void given_whenPlaying_should() { //TODO
        // GIVEN
        // See setUp()
        when(mockStrategy.makeADecision(mockHand)).thenReturn(Decision.HIT);

        // WHEN
        Decision decision = playerUnderTest.play();

        // THEN
        assertThat(decision).isEqualTo(Decision.HIT);
    }
}