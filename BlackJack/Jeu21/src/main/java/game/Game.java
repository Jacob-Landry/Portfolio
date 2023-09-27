package game;

import game.card.deck.Deck;
import game.player.Decision;
import game.player.Player;
import util.Display;

public class Game {

    private Player player;
    private Player dealer;
    private Deck deck;
    private Display display;

    public Game(Deck deck, Display display, Player player, Player dealer) {
        this.player = player;
        this.dealer = dealer;
        this.deck = deck;
        this.display = display;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getDealer() {
        return dealer;
    }

    public Deck getDeck() {
        return deck;
    }

    public Display getDisplay() {
        return display;
    }

    public void start() {
        display.print("Début de la partie!");

        display.print("C'est le tour du joueur!");
        turn(player, "joueur");

        display.print("C'est le tour du croupier!");
        turn(dealer, "croupier");

        determineWinner();

        clearHands();

        playAgain();
    }

    public void playAgain() {
        String replayResponse = display.promptUntilCorrectFormat("Voulez-vous jouer une autre partie? (o/n)", "^(O|o)|(N|n)$").toLowerCase();

        if (replayResponse.equals("o")) {
            start();
        } else {
            display.print("Au revoir!");
        }
    }

    public void clearHands() {
        player.getHand().emptyHand();
        dealer.getHand().emptyHand();
    }

    public void turn(Player currentPlayer, String role) {
        Decision currentDecision = Decision.HIT;

        currentPlayer.addCardToHand(deck.draw());
        currentPlayer.addCardToHand(deck.draw());

        display.print(currentPlayer.getHand().toString());

        while (currentPlayer.getHand().calculateValue() < 21 && currentDecision != Decision.STAND) {
            currentDecision = currentPlayer.play();
            display.print("le " + role + " a joué: " + currentDecision);

            if (currentDecision == Decision.HIT) {
                currentPlayer.addCardToHand(deck.draw());
            }

            display.print(currentPlayer.getHand().toString());
        }
    }

    public void determineWinner() {
        int dealerHandValue = dealer.getHand().calculateValue();
        int playerHandValue = player.getHand().calculateValue();

        display.print("Les mains sont: ");
        display.print("- Joueur -");
        display.print(player.getHand().toString());
        display.print("");
        display.print("- Croupier -");
        display.print(dealer.getHand().toString());


        if (playerHandValue == dealerHandValue) {
            display.print("Le croupier gagne, il a la même valeur que le joueur!");
            return;
        }

        if (dealerHandValue == 21) {
            display.print("Le croupier a gagné avec un 21!");
            return;
        }

        if (playerHandValue == 21) {
            display.print("Le joueur a gagné avec un 21!");
            return;
        }

        if (playerHandValue > 21) {
            display.print("Le joueur a dépassé 21, le croupier gagne!");
            return;
        }

        if (dealerHandValue > 21) {
            display.print("Le croupier a dépassé 21 et pas le joueur; le joueur gagne!");
            return;
        }

        if (playerHandValue < dealerHandValue) {
            display.print("Le croupier gagne, il a une meuilleur main que le joueur!");
        } else {
            display.print("Le joueur gagne, il a une meuilleur main que le croupier!");
        }
    }
}
