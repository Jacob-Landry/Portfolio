package com.jsmarier.jeu21.Business.Deck;

import com.jsmarier.jeu21.Model.Beans.CardBean;
import com.jsmarier.jeu21.Model.CardSuitEnum;
import com.jsmarier.jeu21.Model.Interfaces.Deck;
import com.jsmarier.jeu21.Model.CardRankEnum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractDeckDecorator implements Deck {

    private Deck deck;

    public AbstractDeckDecorator(Deck deck) {
        this.deck = deck;
    }

    @Override
    public List<CardBean> getCardList() {
        return deck.getCardList();
    }

    @Override
    public void setCardList(List<CardBean> cardList) {
        deck.setCardList(cardList);
    }

    public void fill() {
        List<CardBean> newCardList = new ArrayList<>();

        for (int i = 0; i < 13; i++) {
            newCardList.add(new CardBean(CardRankEnum.values()[i], CardSuitEnum.SPADE));
            newCardList.add(new CardBean(CardRankEnum.values()[i], CardSuitEnum.HEART));
            newCardList.add(new CardBean(CardRankEnum.values()[i], CardSuitEnum.CLUB));
            newCardList.add(new CardBean(CardRankEnum.values()[i], CardSuitEnum.DIAMOND));
        }

        deck.setCardList(newCardList);
    }

    public CardBean drawCard() {
        return deck.getCardList().remove(0);
    }

    public void shuffle() {
        Collections.shuffle(deck.getCardList());
    }
}
