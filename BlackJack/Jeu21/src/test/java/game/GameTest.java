package game;

import game.card.Card;
import game.card.deck.Deck;
import game.card.hand.HandJeu21;
import game.player.Decision;
import game.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import util.Display;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class GameTest {

    private Game gameUnderTest;

    @Mock
    Player mockPlayer1;

    @Mock
    Player mockPlayer2;

    @Mock
    Deck mockDeck;

    @Mock
    Display mockDisplay;

    @Mock
    Card mockCard;

    @Mock
    HandJeu21 mockHand;

    @BeforeEach
    void setUp() {
        mockPlayer1 = mock(Player.class);
        mockPlayer2 = mock(Player.class);
        mockDeck = mock(Deck.class);
        mockDisplay = mock(Display.class);
        mockCard = mock(Card.class);
        mockHand = mock(HandJeu21.class);

        gameUnderTest = new Game(mockDeck, mockDisplay, mockPlayer1, mockPlayer2);
    }


    @Test
    void givenNewGame_whenInstantiating_thenAttributesProperlyAssigned() {
        // GIVEN
        // See setUp()

        // WHEN
        // Constructor called in setUp()

        // THEN
        assertThat(gameUnderTest).isInstanceOf(Game.class);
        assertThat(gameUnderTest.getPlayer()).isEqualTo(mockPlayer1);
        assertThat(gameUnderTest.getDealer()).isEqualTo(mockPlayer2);
        assertThat(gameUnderTest.getDeck()).isEqualTo(mockDeck);
        assertThat(gameUnderTest.getDisplay()).isEqualTo(mockDisplay);
    }

    @Test
    void givenAGame_whenGameIsStarted_thenAllMethodsAreCalledInTheGoodOrder() {
        // GIVEN
        // See setUp()
        when(mockPlayer1.play()).thenReturn(Decision.STAND);
        when(mockPlayer2.play()).thenReturn(Decision.HIT, Decision.STAND);
        when(mockPlayer1.getHand()).thenReturn(mockHand);
        when(mockPlayer2.getHand()).thenReturn(mockHand);
        when(mockDisplay.promptUntilCorrectFormat(anyString(), anyString())).thenReturn("n");

        // WHEN
        gameUnderTest.start();

        // THEN
        verify(mockPlayer1, times(1)).play();
        verify(mockPlayer2, times(2)).play();
        verify(mockPlayer1.getHand(), times(7)).calculateValue();
        verify(mockPlayer2.getHand(), times(7)).calculateValue();
    }

    @Test
    void givenAPlayerAndHisRole_whenPlayingATurn_thenAdd2CardsToPlayerHand() {
        // GIVEN
        // See setUp()
        when(mockPlayer1.play()).thenReturn(Decision.STAND);
        when(mockPlayer1.getHand()).thenReturn(mockHand);
        when(mockDeck.draw()).thenReturn(mockCard);

        // WHEN
        gameUnderTest.turn(mockPlayer1, "mockPlayer");

        // THEN
        verify(mockPlayer1, times(2)).addCardToHand(any(Card.class));
    }

    @Test
    void givenAPlayerAndHisRole_whenPlayingATurn_thenAdd1CardToThePlayerHandIfDecisionIsHit() {
        // GIVEN
        // See setUp()
        when(mockPlayer1.play()).thenReturn(Decision.HIT, Decision.STAND);
        when(mockPlayer1.getHand()).thenReturn(mockHand);
        when(mockDeck.draw()).thenReturn(mockCard);

        // WHEN
        gameUnderTest.turn(mockPlayer1, "mockPlayer");

        // THEN
        verify(mockPlayer1, times(3)).addCardToHand(any(Card.class));

    }

    @Test
    void givenAPlayerAndHisRole_whenPlayingATurn_thenStopIfThePlayerHandValueIsGraterOrEqualsAt21() {
        // GIVEN
        // See setUp()
        when(mockPlayer1.play()).thenReturn(Decision.HIT);
        when(mockPlayer1.getHand()).thenReturn(mockHand);
        when(mockDeck.draw()).thenReturn(mockCard);
        when(mockPlayer1.getHand().calculateValue()).thenReturn(21);

        // WHEN
        gameUnderTest.turn(mockPlayer1, "mockPlayer");

        // THEN
        verify(mockPlayer1, times(2)).addCardToHand(any(Card.class));

    }

    @Test
    void givenAPlayerAndHisRole_whenPlayingATurn_thenStopIfDecisionIsStand() {
        // GIVEN
        // See setUp()
        when(mockPlayer1.play()).thenReturn(Decision.STAND);
        when(mockPlayer1.getHand()).thenReturn(mockHand);
        when(mockDeck.draw()).thenReturn(mockCard);

        // WHEN
        gameUnderTest.turn(mockPlayer1, "mockPlayer");

        // THEN
        verify(mockPlayer1, times(2)).addCardToHand(any(Card.class));

    }

    @Test
    void given2PlayersHand_whenDeterminingAWinner_thenShouldPrintTheDealerAsWinnerIfBothHaveTheSameHandValue() {
        // GIVEN
        // See setUp()
        when(mockPlayer1.getHand()).thenReturn(mockHand);
        when(mockPlayer2.getHand()).thenReturn(mockHand);
        when(mockPlayer1.getHand().calculateValue()).thenReturn(10);
        when(mockPlayer2.getHand().calculateValue()).thenReturn(10);

        // WHEN
        gameUnderTest.determineWinner();

        // THEN
        verify(mockDisplay, times(1)).print("Le croupier gagne, il a la même valeur que le joueur!");
    }

    @Test
    void given2PlayersHand_whenDeterminingAWinner_thenShouldPrintTheDealerAsWinnerIfTheDealerHasA21HandValue() {
        // GIVEN
        // See setUp()
        when(mockPlayer1.getHand()).thenReturn(mockHand);
        when(mockPlayer2.getHand()).thenReturn(mock(HandJeu21.class));
        when(mockPlayer1.getHand().calculateValue()).thenReturn(10);
        when(mockPlayer2.getHand().calculateValue()).thenReturn(21);

        // WHEN
        gameUnderTest.determineWinner();

        // THEN
        verify(mockDisplay, times(1)).print("Le croupier a gagné avec un 21!");
    }

    @Test
    void given2PlayersHand_whenDeterminingAWinner_thenShouldPrintThePlayerAsWinnerIfOnlyThePlayerHasA21HandValue() {
        // GIVEN
        // See setUp()
        when(mockPlayer1.getHand()).thenReturn(mockHand);
        when(mockPlayer2.getHand()).thenReturn(mock(HandJeu21.class));
        when(mockPlayer1.getHand().calculateValue()).thenReturn(21);
        when(mockPlayer2.getHand().calculateValue()).thenReturn(10);

        // WHEN
        gameUnderTest.determineWinner();

        // THEN
        verify(mockDisplay, times(1)).print("Le joueur a gagné avec un 21!");
    }

    @Test
    void given2PlayersHand_whenDeterminingAWinner_thenShouldPrintTheDealerAsWinnerIfThePlayerHasAGreaterThan21HandValue() {
        // GIVEN
        // See setUp()
        when(mockPlayer1.getHand()).thenReturn(mockHand);
        when(mockPlayer2.getHand()).thenReturn(mock(HandJeu21.class));
        when(mockPlayer1.getHand().calculateValue()).thenReturn(22);
        when(mockPlayer2.getHand().calculateValue()).thenReturn(10);

        // WHEN
        gameUnderTest.determineWinner();

        // THEN
        verify(mockDisplay, times(1)).print("Le joueur a dépassé 21, le croupier gagne!");
    }

    @Test
    void given2PlayersHand_whenDeterminingAWinner_thenShouldPrintThePlayerAsWinnerIfOnlyTheDealerHasAGreaterThan21HandValue() {
        // GIVEN
        // See setUp()
        when(mockPlayer1.getHand()).thenReturn(mockHand);
        when(mockPlayer2.getHand()).thenReturn(mock(HandJeu21.class));
        when(mockPlayer1.getHand().calculateValue()).thenReturn(10);
        when(mockPlayer2.getHand().calculateValue()).thenReturn(22);

        // WHEN
        gameUnderTest.determineWinner();

        // THEN
        verify(mockDisplay, times(1)).print("Le croupier a dépassé 21 et pas le joueur; le joueur gagne!");
    }

    @Test
    void given2PlayersHand_whenDeterminingAWinner_thenShouldPrintTheDealerAsWinnerIfBothHandValueAreLessThan21AndTheDealerHasTheGreatest() {
        // GIVEN
        // See setUp()
        when(mockPlayer1.getHand()).thenReturn(mockHand);
        when(mockPlayer2.getHand()).thenReturn(mock(HandJeu21.class));
        when(mockPlayer1.getHand().calculateValue()).thenReturn(19);
        when(mockPlayer2.getHand().calculateValue()).thenReturn(20);

        // WHEN
        gameUnderTest.determineWinner();

        // THEN
        verify(mockDisplay, times(1)).print("Le croupier gagne, il a une meuilleur main que le joueur!");
    }

    @Test
    void given2PlayersHand_whenDeterminingAWinner_thenShouldPrintThePlayerAsWinnerIfBothHandValueAreLessThan21AndThePlayerHasTheGreatest() {
        // GIVEN
        // See setUp()
        when(mockPlayer1.getHand()).thenReturn(mockHand);
        when(mockPlayer2.getHand()).thenReturn(mock(HandJeu21.class));
        when(mockPlayer1.getHand().calculateValue()).thenReturn(20);
        when(mockPlayer2.getHand().calculateValue()).thenReturn(19);

        // WHEN
        gameUnderTest.determineWinner();

        // THEN
        verify(mockDisplay, times(1)).print("Le joueur gagne, il a une meuilleur main que le croupier!");
    }

    @Test
    void given2PlayerHands_whenClearingHands_thenShouldClearAllPlayerHands() {
        // GIVEN
        // See setUp()
        when(mockPlayer1.getHand()).thenReturn(mockHand);
        when(mockPlayer2.getHand()).thenReturn(mock(HandJeu21.class));

        // WHEN
        gameUnderTest.clearHands();

        // THEN
        verify(mockPlayer1.getHand(), times(1)).emptyHand();
        verify(mockPlayer2.getHand(), times(1)).emptyHand();
    }

    @Test
    void givenAGame_whenPlayerIsAskToPlayAgain_thenShouldStartANewGameIfThePlayerRespondsO() {
        // GIVEN
        // See setUp()
        when(mockDisplay.promptUntilCorrectFormat(anyString(), anyString())).thenReturn("o");

        // WHEN
        try {
            gameUnderTest.playAgain();
        } catch (NullPointerException e) {
            System.out.println("Si cette exception est lancée c'est puisque start est appelée. Action désirée");
        }

        // THEN
        verify(mockDisplay, times(0)).print("Au revoir!");
    }

    @Test
    void givenAGame_whenPlayerIsAskToPlayAgain_thenShouldStartANewGameIfThePlayerRespondsN() {
        // GIVEN
        // See setUp()
        when(mockDisplay.promptUntilCorrectFormat(anyString(), anyString())).thenReturn("autre");

        // WHEN
        gameUnderTest.playAgain();


        // THEN
        verify(mockDisplay, times(1)).print("Au revoir!");
    }
}
