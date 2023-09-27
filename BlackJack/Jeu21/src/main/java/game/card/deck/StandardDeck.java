package game.card.deck;

import game.card.Card;
import game.card.Rank;
import game.card.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StandardDeck implements Deck {

    private List<Card> cardList;

    public StandardDeck() {
        this.cardList = new ArrayList<>();
    }

    public List<Card> getCardList() {
        return cardList;
    }

    @Override
    public void regenerateCards() {
        cardList.clear();

        for (int i = 0; i < 13; i++) {
            cardList.add(new Card(Rank.values()[i], Suit.SPADE));
            cardList.add(new Card(Rank.values()[i], Suit.HEART));
            cardList.add(new Card(Rank.values()[i], Suit.CLUB));
            cardList.add(new Card(Rank.values()[i], Suit.DIAMOND));
        }

        Collections.shuffle(cardList);
    }

    @Override
    public Card draw() {
        return cardList.remove(0);
    }
}
