package com.jsmarier.jeu21.Controller;

import com.jsmarier.jeu21.Business.Deck.SimpleDeckDecorator;
import com.jsmarier.jeu21.Business.Player.SimplePlayerDecorator;
import com.jsmarier.jeu21.Model.Beans.CardBean;
import com.jsmarier.jeu21.Model.DecisionEnum;
import com.jsmarier.jeu21.Model.Interfaces.Player;

import java.util.*;

import static java.lang.Math.abs;
import static java.lang.Math.random;

public class Dealer {

    private GameGraphicalController graphicalController;
    private TreeMap<Integer, Player> playerMap;
    private int currentPlayerId;
    private SimpleDeckDecorator deck;

    public Dealer(GameGraphicalController graphicalController, SimpleDeckDecorator deck) {
        this.graphicalController = graphicalController;
        this.deck = deck;
    }

    public void initializeGame() {
        loadPlayers();
        deck.fill();
        deck.shuffle();

        for (Player player : playerMap.values()) {
            new SimplePlayerDecorator(player).takeCard(deck.drawCard());
        }

        currentPlayerId = playerMap.firstKey();
    }

    private void loadPlayers() {
        graphicalController.setPlayerNames(playerMap.values().stream().toList());
    }

    public void playerTurn(DecisionEnum decision) {
        if (decision == DecisionEnum.HIT) {
            CardBean card = deck.drawCard();
            new SimplePlayerDecorator(playerMap.get(currentPlayerId)).takeCard(card);
            String cardName = (card.getCardRank().toString() + "_" + card.getSuit().toString()).toLowerCase();
            graphicalController.addCardToPlayerArea(currentPlayerId, cardName);
        }

        if (playerMap.keySet().size() == 1) {
            gameEnd();
            return;
        } else if (currentPlayerId == playerMap.lastKey()) {
            endOfRound();
        } else {
            currentPlayerId = playerMap.ceilingKey(currentPlayerId + 1);
            startPlayerTurn();
        }

        startPlayerTurn();
    } // TODO -> TEST

    private void gameEnd() {
        graphicalController.getStandButton().setDisable(true);
        graphicalController.getHitButton().setDisable(true);
    }

    public void startPlayerTurn() {
        boolean canstand = (random() >= 0.5);

        updateStandButtonState(canstand);
        graphicalController.setActivePlayerName(playerMap.get(currentPlayerId).getName());
    }

    private void endOfRound() {
        Player loser = determineRoundLoser();
        int loserId = findPlayerId(loser);

        graphicalController.setPlayerIdAsEliminated(loserId);
        playerMap.remove(loserId);

        currentPlayerId = playerMap.firstKey();
    }

    private int findPlayerId(Player player) {
        int id = -1;

        for (int key : playerMap.keySet()) {
            if (playerMap.get(key).equals(player)) {
                id = key;
            }
        }
        return id;
    }

    public Player determineRoundLoser() {
        int largestDiff = 0;
        Player currentLoser = null;

        for (Player player : playerMap.values()) {
            int handValue = player.getHandValue();

            if (handValue > 21) {
                return player;

            } else {
                int playerDiff = abs(21 - handValue);

                if (playerDiff - largestDiff >= 1) {
                    largestDiff = playerDiff;
                    currentLoser = player;
                }
            }
        }
        return currentLoser; // TODO -> TEST
    }

    private void updateStandButtonState(boolean canstand) {
        graphicalController.getStandButton().setDisable(canstand);
    }

    public void setPlayerMapFromList(List<Player> playerList) {
        TreeMap<Integer, Player> playerMap = new TreeMap<>();

        for (int i = 0; i < playerList.size(); i++) {
            playerMap.put(i, playerList.get(i));
        }

        this.playerMap = playerMap;
    }
}
