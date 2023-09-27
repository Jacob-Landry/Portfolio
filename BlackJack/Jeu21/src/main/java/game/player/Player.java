package game.player;

import game.card.Card;
import game.card.hand.Hand;

public interface Player {
    Decision play();

    void addCardToHand(Card card);

    Hand getHand();
}
