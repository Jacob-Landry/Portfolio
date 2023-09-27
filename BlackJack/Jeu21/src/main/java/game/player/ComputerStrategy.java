package game.player;

import game.card.hand.Hand;

public interface ComputerStrategy {

    Decision makeADecision(Hand curentHand);
}
