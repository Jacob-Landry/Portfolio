package com.jsmarier.jeu21.Business.Player;

import com.jsmarier.jeu21.Model.Interfaces.Player;

public class WinningPlayerDecorator extends AbstractPlayerDecorator {
    public WinningPlayerDecorator(Player player) {
        super(player);
    }

    public String generateWinnerString(){
        return "Congratulations! " + getName() + " has won the game!";
    }
}
