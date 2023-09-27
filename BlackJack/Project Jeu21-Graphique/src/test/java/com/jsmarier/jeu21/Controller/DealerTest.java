package com.jsmarier.jeu21.Controller;

import com.jsmarier.jeu21.Business.Deck.SimpleDeckDecorator;
import com.jsmarier.jeu21.Business.Hand.SimpleHandDecorator;
import com.jsmarier.jeu21.Business.Player.SimplePlayerDecorator;
import com.jsmarier.jeu21.Model.Beans.CardBean;
import com.jsmarier.jeu21.Model.Beans.HandBean;
import com.jsmarier.jeu21.Model.CardRankEnum;
import com.jsmarier.jeu21.Model.DecisionEnum;
import com.jsmarier.jeu21.Model.Interfaces.Deck;
import com.jsmarier.jeu21.Model.Interfaces.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DealerTest {
    private Dealer dealerUnderTest;
    private List<Player> playerListTest;
    @Mock
    private SimpleDeckDecorator mockDeck;
    @Mock
    GameGraphicalController mockGraphicalController;
    @Mock
    private SimplePlayerDecorator mockPlayer1;
    @Mock
    private SimplePlayerDecorator mockPlayer2;
    @Mock
    private SimplePlayerDecorator mockPlayer3;
    @Mock
    private SimplePlayerDecorator mockPlayer4;
    @Mock
    private HandBean mockHand;
    @Mock
    private CardBean mockCardBean;


    @BeforeEach
    void setUp() {
        mockDeck = mock(SimpleDeckDecorator.class);
        mockGraphicalController = mock(GameGraphicalController.class);
        mockPlayer1 = mock(SimplePlayerDecorator.class);
        mockPlayer2 = mock(SimplePlayerDecorator.class);
        mockPlayer3 = mock(SimplePlayerDecorator.class);
        mockPlayer4 = mock(SimplePlayerDecorator.class);
        mockHand = mock(HandBean.class);
        mockCardBean = mock(CardBean.class);

        dealerUnderTest = new Dealer(mockGraphicalController, mockDeck);
        playerListTest = new ArrayList<>();
        playerListTest.add(mockPlayer1);
        playerListTest.add(mockPlayer2);
        playerListTest.add(mockPlayer3);
        playerListTest.add(mockPlayer4);
        dealerUnderTest.setPlayerMapFromList(playerListTest);
    }

    /**
     * Given a list of player.
     * When determining the loser of the round.
     * Then return the loser of the current round.
     */
    @Test
    void determineRoundLoser() {
        //Given
        when(mockPlayer1.getHandValue()).thenReturn(21);
        when(mockPlayer2.getHandValue()).thenReturn(18);
        when(mockPlayer3.getHandValue()).thenReturn(15);
        when(mockPlayer4.getHandValue()).thenReturn(13);

        //When
        Player losingPlayerTest = dealerUnderTest.determineRoundLoser();

        //Then
        assertThat(losingPlayerTest).isEqualTo(mockPlayer4);
    }

    /**
     * Given a dealer
     * When initializing the game
     * Then should shuffle the deck and give a card to each player
     */
    @Test
    void initializeGame() {
        //Given
        List<CardBean> testDeckCardList = new ArrayList<>();
        List<CardBean> testPlayer1CardList = new ArrayList<>();
        List<CardBean> testPlayer2CardList = new ArrayList<>();
        List<CardBean> testPlayer3CardList = new ArrayList<>();
        List<CardBean> testPlayer4CardList = new ArrayList<>();

        when(mockDeck.getCardList()).thenReturn(testDeckCardList);
        when(mockDeck.drawCard()).thenReturn(mockCardBean);

        when(mockPlayer1.getHand()).thenReturn(mockHand);
        when(mockPlayer2.getHand()).thenReturn(mockHand);
        when(mockPlayer3.getHand()).thenReturn(mockHand);
        when(mockPlayer4.getHand()).thenReturn(mockHand);

        when(mockPlayer1.getHand().getCardList()).thenReturn(testPlayer1CardList);
        when(mockPlayer2.getHand().getCardList()).thenReturn(testPlayer2CardList);
        when(mockPlayer3.getHand().getCardList()).thenReturn(testPlayer3CardList);
        when(mockPlayer4.getHand().getCardList()).thenReturn(testPlayer4CardList);

        when(mockHand.getCardList()).thenReturn(testPlayer1CardList, testPlayer2CardList, testPlayer3CardList, testPlayer4CardList);
        when(mockHand.getValue()).thenReturn(12);
        when(mockCardBean.getCardRank()).thenReturn(CardRankEnum.ACE);


        //When
        dealerUnderTest.initializeGame();

        //Then
        verify(mockDeck, times(1)).fill();
        verify(mockDeck, times(1)).shuffle();
        assertThat(mockPlayer1.getHand().getCardList().size()).isEqualTo(1);
        assertThat(mockPlayer2.getHand().getCardList().size()).isEqualTo(1);
        assertThat(mockPlayer3.getHand().getCardList().size()).isEqualTo(1);
        assertThat(mockPlayer4.getHand().getCardList().size()).isEqualTo(1);
    }

    /**
     * Given
     * When
     * Then
     */
    @Test
    void playerTurn() {

    }

    /**
     * Given a game
     * When Starting a player's turn
     * Then update the stand button for the player (50% to be active). Also show the name of the player playing.
     */
    @Test
    void startPlayerTurn() {
        //Given
        List<CardBean> testDeckCardList = new ArrayList<>();
        List<CardBean> testPlayerCardList = new ArrayList<>();

        when(mockDeck.getCardList()).thenReturn(testDeckCardList);
        when(mockDeck.drawCard()).thenReturn(mockCardBean);

        when(mockPlayer1.getHand()).thenReturn(mockHand);
        when(mockPlayer2.getHand()).thenReturn(mockHand);
        when(mockPlayer3.getHand()).thenReturn(mockHand);
        when(mockPlayer4.getHand()).thenReturn(mockHand);

        when(mockPlayer1.getHand().getCardList()).thenReturn(testPlayerCardList);
        when(mockPlayer2.getHand().getCardList()).thenReturn(testPlayerCardList);
        when(mockPlayer3.getHand().getCardList()).thenReturn(testPlayerCardList);
        when(mockPlayer4.getHand().getCardList()).thenReturn(testPlayerCardList);

        when(mockHand.getCardList()).thenReturn(testPlayerCardList);
        when(mockHand.getValue()).thenReturn(12);
        when(mockCardBean.getCardRank()).thenReturn(CardRankEnum.ACE);

        when(mockPlayer1.getName()).thenReturn("test");

        Button testButton = new GameGraphicalController().getStandButton();
        when(mockGraphicalController.getStandButton()).thenReturn(testButton);

        //When
        dealerUnderTest.initializeGame();
        dealerUnderTest.startPlayerTurn();

        //Then
        verify(mockGraphicalController, times(1)).setActivePlayerName("test");
        verify(mockGraphicalController, times(1)).getStandButton();
        verify(mockGraphicalController.getStandButton(), times(1)).setDisable(anyBoolean());

    }

}