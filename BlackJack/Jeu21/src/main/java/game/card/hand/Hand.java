package game.card.hand;

import game.card.Card;

import java.util.List;

public interface Hand {

    void addCard(Card card);

    List<Card> getCardList();

    int calculateValue();

    void emptyHand();

    String toString();
}
