package com.jsmarier.jeu21.Business.Hand;

import com.jsmarier.jeu21.Model.Beans.CardBean;
import com.jsmarier.jeu21.Model.CardRankEnum;
import com.jsmarier.jeu21.Model.Interfaces.Hand;
import org.eclipse.sisu.Parameters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AbstactHandDecoratorTest {
    @Mock
    private Hand mockHand;
    @Mock
    private CardBean mockCard;
    private AbstactHandDecorator abstractHandDecoratorUnderTest;

    @BeforeEach
    void setUp() {
        mockHand = mock(Hand.class);
        mockCard = mock(CardBean.class);
        abstractHandDecoratorUnderTest = new SimpleHandDecorator(mockHand);
    }
    /**
     * Given a hand.
     * When adding a card to the hand.
     * Then should add a card to the hand (cardList) and increase the value.
     */
    @ParameterizedTest
    @CsvSource(value = {"1, 0, 1", "11, 0, 10", "10, 0, 10", "13, 0, 10", "1, 2, 3"})
    void addCard(int rank, int initialHandValue, int expected) {
        //given
        List<CardBean> testCardList = new ArrayList<>();
        when(mockHand.getCardList()).thenReturn(testCardList);
        when(mockHand.getValue()).thenReturn(initialHandValue);
        when(mockCard.getCardRank()).thenReturn(CardRankEnum.values()[rank]);

        //when
        abstractHandDecoratorUnderTest.addCard(mockCard);

        //then
        assertThat(testCardList.size()).isEqualTo(1);
        verify(mockHand, times(1)).setValue(expected);
    }
}