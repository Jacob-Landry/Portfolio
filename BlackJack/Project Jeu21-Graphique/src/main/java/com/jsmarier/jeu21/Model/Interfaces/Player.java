package com.jsmarier.jeu21.Model.Interfaces;

import com.jsmarier.jeu21.Model.Beans.HandBean;

public interface Player {
    HandBean getHand();

    void setHand(HandBean hand);

    int getHandValue();

    String getName();
    void setName(String name);
}
