package game.player;

import game.card.Card;
import game.card.hand.Hand;
import util.Display;

public class HumanPlayer implements Player{

    private Hand hand;
    private Display display;

    public HumanPlayer(Hand hand, Display display) {
        this.hand = hand;
        this.display = display;
    }

    @Override
    public Decision play() {
        String response = display.promptUntilCorrectFormat("Que voulez-vous faire?\n(H)it ou (S)tand?", "^(H|h)|(S|s)$").toLowerCase();

        // Null technically unreachable because of promptUntilCorrectFormat() but necessary to build
        return switch (response) {
            case "s" -> Decision.STAND;
            case "h" -> Decision.HIT;
            default -> null;
        };
    }

    @Override
    public void addCardToHand(Card card) {
        hand.addCard(card);
    }

    @Override
    public Hand getHand() {
        return hand;
    }
}
