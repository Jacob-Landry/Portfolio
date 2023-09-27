package game.card.hand;

import game.card.Card;
import game.card.Rank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HandJeu21 implements Hand {
    private List<Card> cardList;
    private final Map<Rank, Integer> cardValueMap;

    public HandJeu21(Map<Rank, Integer> cardValueMap) {
        cardList = new ArrayList<>();
        this.cardValueMap = cardValueMap;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    @Override
    public void addCard(Card card) {
        cardList.add(card);
    }

    @Override
    public int calculateValue() {
        int sum = 0;

        for (Card card : cardList) {
            sum += cardValueMap.get(card.getRank());
        }

        return sum;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Card card : cardList) {
            stringBuilder.append(card.toString()).append("\n");
        }

        stringBuilder.append("Valeur: ");
        stringBuilder.append(calculateValue());

        return stringBuilder.toString();
    }

    @Override
    public void emptyHand() {
        cardList.clear();
    }
}
