package com.jsmarier.jeu21.Business.Deck;

import com.jsmarier.jeu21.Model.Beans.CardBean;
import com.jsmarier.jeu21.Model.Interfaces.Deck;

import java.util.List;

public class SimpleDeckDecorator extends AbstractDeckDecorator {

    public SimpleDeckDecorator(Deck deck) {
        super(deck);
    }
}
