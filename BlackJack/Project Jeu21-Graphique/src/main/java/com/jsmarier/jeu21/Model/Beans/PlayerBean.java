package com.jsmarier.jeu21.Model.Beans;

import com.jsmarier.jeu21.Model.Interfaces.Player;

import java.io.Serializable;

public class PlayerBean implements Player, Serializable {
    private String name;
    private boolean isEliminated;
    private HandBean hand;

    public PlayerBean(String name, HandBean hand) {
        this.name = name;
        this.hand = hand;
    }

    @Override
    public HandBean getHand() {
        return hand;
    }

    @Override
    public void setHand(HandBean hand) {
        this.hand = hand;
    }

    @Override
    public int getHandValue() {
        return hand.getValue();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
       this.name = name;
    }

    @Override
    public String toString() {
        return "PlayerBean{" +
                "hand=" + hand +
                '}';
    }
}
