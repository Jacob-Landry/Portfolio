package game.player;

import game.card.hand.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DefaultStrategyTest {
    private DefaultStrategy strategyUnderTest;
    @Mock
    private Hand mockHand;

    @BeforeEach
    void setUp() {
        mockHand = mock(Hand.class);
        strategyUnderTest = new DefaultStrategy();
    }

    @Test
    void givenAHandValueLessThan16_whenMakingADecision_thenReturnAHitDecision() {
        //GIVEN
        //see setUp()
        when(mockHand.calculateValue()).thenReturn(15);

        //WHEN
        Decision decision = strategyUnderTest.makeADecision(mockHand);

        //THEN
        assertThat(decision).isEqualTo(Decision.HIT);

    }

    @Test
    void givenAHandValueOver15_whenMakingADecision_thenReturnAStandDecision() {
        //GIVEN
        //see setUp()
        when(mockHand.calculateValue()).thenReturn(16);

        //WHEN
        Decision decision = strategyUnderTest.makeADecision(mockHand);

        //THEN
        assertThat(decision).isEqualTo(Decision.STAND);
    }
}