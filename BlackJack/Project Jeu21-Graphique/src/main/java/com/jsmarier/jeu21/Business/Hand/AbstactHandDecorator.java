package com.jsmarier.jeu21.Business.Hand;

import com.jsmarier.jeu21.Model.Beans.CardBean;
import com.jsmarier.jeu21.Model.Interfaces.Hand;

import java.util.List;

public abstract class AbstactHandDecorator implements Hand {
    private Hand hand;

    public AbstactHandDecorator(Hand hand) {
        this.hand = hand;
    }

    @Override
    public List<CardBean> getCardList() {
        return hand.getCardList();
    }
    @Override
    public void setCardList(List<CardBean> cardList) {
        hand.setCardList(cardList);
    }
    @Override
    public int getValue() {
        return hand.getValue();
    }
    @Override
    public void setValue(int value) {
        hand.setValue(value);
    }

    public void addCard(CardBean card){
        hand.getCardList().add(card);
        hand.setValue(hand.getValue() + calculateCardValue(card));
    }

    private int calculateCardValue(CardBean card) {
        int cardRankOrdinal = card.getCardRank().ordinal() + 1;

        return Math.min(cardRankOrdinal, 10);
    }
}
