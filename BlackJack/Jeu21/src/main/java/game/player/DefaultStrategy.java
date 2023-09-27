package game.player;

import game.card.hand.Hand;

public class DefaultStrategy implements ComputerStrategy {
    @Override
    public Decision makeADecision(Hand curentHand) {
        Decision decision;
        int handTotalValue = curentHand.calculateValue();

        if (handTotalValue <= 15){
            decision = Decision.HIT;

        } else {
            decision = Decision.STAND;
        }

        return decision;
    }
}
