package com.jsmarier.jeu21.Model.Interfaces;

import com.jsmarier.jeu21.Model.Beans.CardBean;

import java.util.List;

public interface Deck {
    List<CardBean> getCardList();

    void setCardList(List<CardBean> cardList);
}
