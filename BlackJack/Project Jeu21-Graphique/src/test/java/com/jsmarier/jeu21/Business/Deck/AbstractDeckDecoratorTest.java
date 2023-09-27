package com.jsmarier.jeu21.Business.Deck;

import com.jsmarier.jeu21.Model.Beans.CardBean;
import com.jsmarier.jeu21.Model.Beans.DeckBean;
import com.jsmarier.jeu21.Model.CardRankEnum;
import com.jsmarier.jeu21.Model.CardSuitEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

class AbstractDeckDecoratorTest {
    @Mock
    private DeckBean mockDeckBean;
    @Mock
    private CardBean mockCardBean1;
    @Mock
    private CardBean mockCardBean2;
    @Mock
    private CardBean mockCardBean3;
    private AbstractDeckDecorator abstractDeckDecoratorUnderTest;

    @BeforeEach
    void setUp() {
        mockDeckBean = mock(DeckBean.class);
        mockCardBean1 = mock(CardBean.class);
        mockCardBean2 = mock(CardBean.class);
        mockCardBean3 = mock(CardBean.class);
        abstractDeckDecoratorUnderTest = new SimpleDeckDecorator(mockDeckBean);
    }

    /**
     * Given a list of cards.
     * When filling the deck.
     * Then should add 52 diffrente cards to the deck.
     */
    @Test
    void fill() {
        //Given
        List<CardBean> exempleCardList = new ArrayList<>();

        exempleCardList.add(new CardBean(CardRankEnum.ACE, CardSuitEnum.SPADE));    exempleCardList.add(new CardBean(CardRankEnum.ACE, CardSuitEnum.HEART));    exempleCardList.add(new CardBean(CardRankEnum.ACE, CardSuitEnum.CLUB));     exempleCardList.add(new CardBean(CardRankEnum.ACE, CardSuitEnum.DIAMOND));
        exempleCardList.add(new CardBean(CardRankEnum.DEUCE, CardSuitEnum.SPADE));  exempleCardList.add(new CardBean(CardRankEnum.DEUCE, CardSuitEnum.HEART));  exempleCardList.add(new CardBean(CardRankEnum.DEUCE, CardSuitEnum.CLUB));   exempleCardList.add(new CardBean(CardRankEnum.DEUCE, CardSuitEnum.DIAMOND));
        exempleCardList.add(new CardBean(CardRankEnum.THREE, CardSuitEnum.SPADE));  exempleCardList.add(new CardBean(CardRankEnum.THREE, CardSuitEnum.HEART));  exempleCardList.add(new CardBean(CardRankEnum.THREE, CardSuitEnum.CLUB));   exempleCardList.add(new CardBean(CardRankEnum.THREE, CardSuitEnum.DIAMOND));
        exempleCardList.add(new CardBean(CardRankEnum.FOUR, CardSuitEnum.SPADE));   exempleCardList.add(new CardBean(CardRankEnum.FOUR, CardSuitEnum.HEART));   exempleCardList.add(new CardBean(CardRankEnum.FOUR, CardSuitEnum.CLUB));    exempleCardList.add(new CardBean(CardRankEnum.FOUR, CardSuitEnum.DIAMOND));
        exempleCardList.add(new CardBean(CardRankEnum.FIVE, CardSuitEnum.SPADE));   exempleCardList.add(new CardBean(CardRankEnum.FIVE, CardSuitEnum.HEART));   exempleCardList.add(new CardBean(CardRankEnum.FIVE, CardSuitEnum.CLUB));    exempleCardList.add(new CardBean(CardRankEnum.FIVE, CardSuitEnum.DIAMOND));
        exempleCardList.add(new CardBean(CardRankEnum.SIX, CardSuitEnum.SPADE));    exempleCardList.add(new CardBean(CardRankEnum.SIX, CardSuitEnum.HEART));    exempleCardList.add(new CardBean(CardRankEnum.SIX, CardSuitEnum.CLUB));     exempleCardList.add(new CardBean(CardRankEnum.SIX, CardSuitEnum.DIAMOND));
        exempleCardList.add(new CardBean(CardRankEnum.SEVEN, CardSuitEnum.SPADE));  exempleCardList.add(new CardBean(CardRankEnum.SEVEN, CardSuitEnum.HEART));  exempleCardList.add(new CardBean(CardRankEnum.SEVEN, CardSuitEnum.CLUB));   exempleCardList.add(new CardBean(CardRankEnum.SEVEN, CardSuitEnum.DIAMOND));
        exempleCardList.add(new CardBean(CardRankEnum.EIGHT, CardSuitEnum.SPADE));  exempleCardList.add(new CardBean(CardRankEnum.EIGHT, CardSuitEnum.HEART));  exempleCardList.add(new CardBean(CardRankEnum.EIGHT, CardSuitEnum.CLUB));   exempleCardList.add(new CardBean(CardRankEnum.EIGHT, CardSuitEnum.DIAMOND));
        exempleCardList.add(new CardBean(CardRankEnum.NINE, CardSuitEnum.SPADE));   exempleCardList.add(new CardBean(CardRankEnum.NINE, CardSuitEnum.HEART));   exempleCardList.add(new CardBean(CardRankEnum.NINE, CardSuitEnum.CLUB));    exempleCardList.add(new CardBean(CardRankEnum.NINE, CardSuitEnum.DIAMOND));
        exempleCardList.add(new CardBean(CardRankEnum.TEN, CardSuitEnum.SPADE));    exempleCardList.add(new CardBean(CardRankEnum.TEN, CardSuitEnum.HEART));    exempleCardList.add(new CardBean(CardRankEnum.TEN, CardSuitEnum.CLUB));     exempleCardList.add(new CardBean(CardRankEnum.TEN, CardSuitEnum.DIAMOND));
        exempleCardList.add(new CardBean(CardRankEnum.JACK, CardSuitEnum.SPADE));   exempleCardList.add(new CardBean(CardRankEnum.JACK, CardSuitEnum.HEART));   exempleCardList.add(new CardBean(CardRankEnum.JACK, CardSuitEnum.CLUB));    exempleCardList.add(new CardBean(CardRankEnum.JACK, CardSuitEnum.DIAMOND));
        exempleCardList.add(new CardBean(CardRankEnum.QUEEN, CardSuitEnum.SPADE));  exempleCardList.add(new CardBean(CardRankEnum.QUEEN, CardSuitEnum.HEART));  exempleCardList.add(new CardBean(CardRankEnum.QUEEN, CardSuitEnum.CLUB));   exempleCardList.add(new CardBean(CardRankEnum.QUEEN, CardSuitEnum.DIAMOND));
        exempleCardList.add(new CardBean(CardRankEnum.KING, CardSuitEnum.SPADE));   exempleCardList.add(new CardBean(CardRankEnum.KING, CardSuitEnum.HEART));   exempleCardList.add(new CardBean(CardRankEnum.KING, CardSuitEnum.CLUB));    exempleCardList.add(new CardBean(CardRankEnum.KING, CardSuitEnum.DIAMOND));


        //When
        abstractDeckDecoratorUnderTest.fill();

        //Then
        verify(mockDeckBean, times(1)).setCardList(exempleCardList);
    }

    /**
     * Given a deck.
     * When Drawing a card.
     * Then should remouve a card from the deck and return it.
     */
    @Test
    void drawCard() {
        //Given
        List<CardBean> testCardList = new ArrayList<>();
        testCardList.add(mockCardBean1);
        when(mockDeckBean.getCardList()).thenReturn(testCardList);

        //When
        CardBean drawnCard = abstractDeckDecoratorUnderTest.drawCard();

        //Then
        assertThat(drawnCard).isEqualTo(mockCardBean1);
        assertThat(abstractDeckDecoratorUnderTest.getCardList()).isEmpty();
    }

    /**
     * Given a deck.
     * When shuffeling the deck.
     * Then should randomize the card order in the deck (cardList).
     */
    @Test
    void shuffle() {
        //Given
        List<CardBean> testUnshuffleCardList = new ArrayList<>();
        testUnshuffleCardList.add(mockCardBean1);
        testUnshuffleCardList.add(mockCardBean2);
        testUnshuffleCardList.add(mockCardBean3);
        List<CardBean> testShuffleCardList = new ArrayList<>();
        testShuffleCardList.add(mockCardBean1);
        testShuffleCardList.add(mockCardBean2);
        testShuffleCardList.add(mockCardBean3);

        when(mockDeckBean.getCardList()).thenReturn(testShuffleCardList);

        //When
        abstractDeckDecoratorUnderTest.shuffle();

        //Then
        assertThat(abstractDeckDecoratorUnderTest.getCardList()).isNotEqualTo(testUnshuffleCardList);
    }


}