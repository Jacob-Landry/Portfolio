package game.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SuitTest {

    @Test
    void givenASuit_whenCallingToString_thenShouldReturnProperSymbol() {
        // GIVEN
        Suit suit = Suit.CLUB;
        String expected = "♣";

        // WHEN
        String actual = suit.toString();

        // THEN
        assertThat(actual).isEqualTo(expected);
    }
}
