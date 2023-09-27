//Développeur ayant travailler sur ce projet. Moi: Jacob Landry et Un coéquipier: Jean Sebastien Marier

import game.Game;
import game.card.Rank;
import game.card.deck.Deck;
import game.card.deck.StandardDeck;
import game.card.hand.HandJeu21;
import game.player.*;
import util.Display;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Deck deck = new StandardDeck();
        deck.regenerateCards();

        Display display = new Display();
        ComputerStrategy strategy = new DefaultStrategy();

        Map<Rank, Integer> cardValueMap = new HashMap<>();
        cardValueMap.put(Rank.ACE, 11);
        cardValueMap.put(Rank.TWO, 2);
        cardValueMap.put(Rank.THREE, 3);
        cardValueMap.put(Rank.FOUR, 4);
        cardValueMap.put(Rank.FIVE, 5);
        cardValueMap.put(Rank.SIX, 6);
        cardValueMap.put(Rank.SEVEN, 7);
        cardValueMap.put(Rank.EIGHT, 8);
        cardValueMap.put(Rank.NINE, 9);
        cardValueMap.put(Rank.TEN, 10);
        cardValueMap.put(Rank.JACK, 10);
        cardValueMap.put(Rank.QUEEN, 10);
        cardValueMap.put(Rank.KING, 10);

        Player hPlayer = new HumanPlayer(new HandJeu21(cardValueMap), display);
        Player cPlayer = new ComputerPlayer(new HandJeu21(cardValueMap), strategy);

        Game game = new Game(deck, display, hPlayer, cPlayer);

        game.start();
    }
}
