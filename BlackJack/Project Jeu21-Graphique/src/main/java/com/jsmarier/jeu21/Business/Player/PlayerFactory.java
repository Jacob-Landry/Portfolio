package com.jsmarier.jeu21.Business.Player;

import com.jsmarier.jeu21.Model.Beans.HandBean;
import com.jsmarier.jeu21.Model.Beans.PlayerBean;
import com.jsmarier.jeu21.Model.Interfaces.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerFactory {
    public Player createPlayer(String name) {
        return (name == null || name.equals("")) ?
                new PlayerBean("PLAYER", new HandBean(new ArrayList<>(), 0)) :
                new PlayerBean(name, new HandBean(new ArrayList<>(), 0));
    }

    public List<Player> createPlayerList(List<String> names) {
        List<Player> playerList = new ArrayList<>();

        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);

            name = (name == null || name.equals("")) ?
                    "PLAYER " + (i + 1) :
                    names.get(i);

            playerList.add(createPlayer(name));
        }

        return playerList;
    }
}
