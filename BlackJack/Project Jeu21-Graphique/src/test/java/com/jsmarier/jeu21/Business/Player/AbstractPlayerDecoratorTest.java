package com.jsmarier.jeu21.Business.Player;

import com.jsmarier.jeu21.Model.Beans.CardBean;
import com.jsmarier.jeu21.Model.Beans.HandBean;
import com.jsmarier.jeu21.Model.CardRankEnum;
import com.jsmarier.jeu21.Model.Interfaces.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AbstractPlayerDecoratorTest {
    private AbstractPlayerDecorator abstractPlayerDecoratorUnderTest;
    @Mock
    private Player mockPlayer;
    @Mock
    private CardBean mockCard;
    @Mock
    private HandBean mockHand;

    @BeforeEach
    void setUp() {
        mockPlayer = mock(Player.class);
        mockCard = mock(CardBean.class);
        mockHand = mock(HandBean.class);

        abstractPlayerDecoratorUnderTest = new SimplePlayerDecorator(mockPlayer);
    }

    /**
     * Given a player.
     * When taking a card.
     * Then a card should be added to the player's hand (cardList).
     */
    @Test
    void takeCard() {
        //Given
        List<CardBean> testCardList = new ArrayList<>();
        when(mockPlayer.getHand()).thenReturn(mockHand);
        when(mockHand.getCardList()).thenReturn(testCardList);
        when(mockCard.getCardRank()).thenReturn(CardRankEnum.ACE);

        //When
        abstractPlayerDecoratorUnderTest.takeCard(mockCard);

        //Then
        assertThat(abstractPlayerDecoratorUnderTest.getHand().getCardList().size()).isEqualTo(1);

    }
}