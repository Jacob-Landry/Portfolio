package game.player;

import game.card.Card;
import game.card.hand.Hand;

public class ComputerPlayer implements Player {

    private Hand hand;
    private ComputerStrategy strategy;

    public ComputerPlayer(Hand hand, ComputerStrategy computerStrategy) {
        this.hand = hand;
        this.strategy = computerStrategy;
    }

    @Override
    public Hand getHand() {
        return hand;
    }

    @Override
    public Decision play() {
        return strategy.makeADecision(hand);
    }

    @Override
    public void addCardToHand(Card card) {
        hand.addCard(card);
    }
}
