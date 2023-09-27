package com.jsmarier.jeu21.Model.Interfaces;

import com.jsmarier.jeu21.Model.Beans.CardBean;

import java.util.List;

public interface Hand {
    List<CardBean> getCardList();

    void setCardList(List<CardBean> cardList);

    int getValue();

    void setValue(int value);
}
