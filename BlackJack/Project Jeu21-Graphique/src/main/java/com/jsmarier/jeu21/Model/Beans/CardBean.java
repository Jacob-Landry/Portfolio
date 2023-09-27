package com.jsmarier.jeu21.Model.Beans;

import com.jsmarier.jeu21.Model.CardRankEnum;
import com.jsmarier.jeu21.Model.CardSuitEnum;

import java.io.Serializable;

public class CardBean implements Serializable {
    private CardRankEnum cardRank;
    private CardSuitEnum suit;

    public CardBean(CardRankEnum cardRank, CardSuitEnum suit) {
        this.cardRank = cardRank;
        this.suit = suit;
    }

    public CardRankEnum getCardRank() {
        return cardRank;
    }

    public void setCardRank(CardRankEnum cardRank) {
        this.cardRank = cardRank;
    }

    public CardSuitEnum getSuit() {
        return suit;
    }

    public void setSuit(CardSuitEnum suit) {
        this.suit = suit;
    }

    @Override
    public String toString() {
        return "CardBean{" +
                "cardRank=" + cardRank +
                ", cardSuit=" + suit +
                '}';
    }
}
