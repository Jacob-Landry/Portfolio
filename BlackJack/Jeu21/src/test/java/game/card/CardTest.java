package game.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CardTest {

    Card cardUnderTest;

    @Mock
    Map<Rank, Integer> mockCardValueMap;

    @Mock
    Rank mockRank;

    @Mock
    Suit mockSuit;

    @BeforeEach
    void setUp() {
        mockCardValueMap = mock(HashMap.class);
        mockRank = mock(Rank.class);
        mockSuit = mock(Suit.class);
        cardUnderTest = new Card(mockRank, mockSuit);
    }

    @Test
    void givenNewObject_whenInstantiating_thenShouldProperlyAssignAttributes() {
        // GIVEN
        // See setUp()

        // WHEN
        // Instantiation in setUp()

        // THEN
        assertThat(cardUnderTest).isInstanceOf(Card.class);
        assertThat(cardUnderTest.getRank()).isEqualTo(mockRank);
        assertThat(cardUnderTest.getSuit()).isEqualTo(mockSuit);
    }

    @Test
    void givenACard_whenCallingToString_thenShouldReturnProperString() {
        // GIVEN
        // See setUp()
        when(mockRank.toString()).thenReturn("[RANK]");
        when(mockSuit.toString()).thenReturn("[SUIT]");
        String expected = "[RANK] [SUIT]";

        // WHEN
        String actual = cardUnderTest.toString();

        // THEN
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void givenACard_whenComparingToIdenticalCard_thenShouldBeEquals() {
        // GIVEN
        // See setUp()
        Card otherCard = new Card(mockRank, mockSuit);

        // WHEN
        boolean actual = cardUnderTest.equals(otherCard);

        // THEN
        assertThat(actual).isTrue();
    }

    @Test
    void givenACard_whenComparingToDifferentCard_thenShouldNotBeEquals() {
        // GIVEN
        // See setUp()
        Suit arbitrarySuit = Suit.CLUB;
        Card otherCard = new Card(mockRank, arbitrarySuit);

        // WHEN
        boolean actual = cardUnderTest.equals(otherCard);

        // THEN
        assertThat(actual).isFalse();
    }

    @Test
    void givenTwoIdenticalCard_whenHashing_thenResultShouldBeIdentical() {
        // GIVEN
        // See setUp()
        Card otherCard = new Card(mockRank, mockSuit);

        // WHEN
        int hash1 = cardUnderTest.hashCode();
        int hash2 = otherCard.hashCode();

        // THEN
        assertThat(hash1).isEqualTo(hash2);
    }
}
