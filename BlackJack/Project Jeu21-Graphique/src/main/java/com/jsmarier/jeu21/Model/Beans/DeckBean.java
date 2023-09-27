package com.jsmarier.jeu21.Model.Beans;

import com.jsmarier.jeu21.Model.Interfaces.Deck;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class DeckBean implements Deck, Serializable {
    List<CardBean> cardList;

    public DeckBean(List<CardBean> cardList) {
        this.cardList = cardList;
    }

    public List<CardBean> getCardList() {
        return cardList;
    }

    public void setCardList(List<CardBean> cardList) {
        this.cardList = cardList;
    }

    @Override
    public String toString() {
        return "DeckBean{" +
                "cardList=" + cardList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeckBean)) return false;
        DeckBean deckBean = (DeckBean) o;
        return cardList.equals(deckBean.cardList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardList);
    }
}
