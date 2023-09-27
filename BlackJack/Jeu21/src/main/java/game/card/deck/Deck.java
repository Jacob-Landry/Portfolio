package game.card.deck;

import game.card.Card;

public interface Deck {

    void regenerateCards();

    Card draw();

}
