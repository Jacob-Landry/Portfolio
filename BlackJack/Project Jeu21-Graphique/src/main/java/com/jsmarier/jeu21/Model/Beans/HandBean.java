package com.jsmarier.jeu21.Model.Beans;

import com.jsmarier.jeu21.Model.Interfaces.Hand;

import java.io.Serializable;
import java.util.List;

public class HandBean implements Hand, Serializable {
    private List<CardBean> cardList;
    private int value;

    public HandBean(List<CardBean> cardList, int value) {
        this.cardList = cardList;
        this.value = value;
    }

    public List<CardBean> getCardList() {
        return cardList;
    }

    public void setCardList(List<CardBean> cardList) {
        this.cardList = cardList;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "HandBean{" +
                "cardList=" + cardList +
                ", value=" + value +
                '}';
    }
}
