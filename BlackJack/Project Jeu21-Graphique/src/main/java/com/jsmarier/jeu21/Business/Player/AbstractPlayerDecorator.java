package com.jsmarier.jeu21.Business.Player;

import com.jsmarier.jeu21.Business.Hand.SimpleHandDecorator;
import com.jsmarier.jeu21.Model.Beans.CardBean;
import com.jsmarier.jeu21.Model.Beans.HandBean;
import com.jsmarier.jeu21.Model.Interfaces.Player;

public abstract class AbstractPlayerDecorator implements Player {
    private Player player;

    public AbstractPlayerDecorator(Player player) {
        this.player = player;
    }


    @Override
    public HandBean getHand() {
        return player.getHand();
    }

    @Override
    public void setHand(HandBean hand) {
        player.setHand(hand);
    }

    @Override
    public int getHandValue() {
        return player.getHandValue();
    }

    @Override
    public String getName() {
        return player.getName();
    }

    @Override
    public void setName(String name) {
        player.setName(name);
    }

    public void takeCard(CardBean card) {
        new SimpleHandDecorator(player.getHand()).addCard(card);
    }
}
