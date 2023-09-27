package com.jsmarier.jeu21.Business.Player;

import com.jsmarier.jeu21.Model.Interfaces.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class WinningPlayerDecoratorTest {
    private WinningPlayerDecorator winningPlayerDecoratorUnderTest;
    @Mock
    private Player mockPlayer;

    @BeforeEach
    void setUp() {
        mockPlayer = mock(Player.class);
        winningPlayerDecoratorUnderTest = new WinningPlayerDecorator(mockPlayer);
    }

    /**
     * Given a Player.
     * When generating the Winner's String.
     * Then should return a string with the name of the winner.
     */
    @Test
    void generateWinnerString() {
        //Given
        String WinnerString = "Congratulations! null has won the game!";
        //When
        String generatedWinnerString = winningPlayerDecoratorUnderTest.generateWinnerString();
        //Then
        assertThat(generatedWinnerString).isEqualTo(WinnerString);
    }
}