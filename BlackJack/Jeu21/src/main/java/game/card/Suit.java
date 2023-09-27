package game.card;

public enum Suit {
    SPADE("♠"),
    HEART("♥"),
    CLUB("♣"),
    DIAMOND("♦");

    private final String displayStr;
    Suit(String displayStr) {
        this.displayStr = displayStr;
    }

    @Override
    public String toString() {
        return displayStr;
    }
}
