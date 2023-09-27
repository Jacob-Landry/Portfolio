package com.jsmarier.jeu21.Business.Player;

import com.jsmarier.jeu21.Model.Interfaces.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlayerFactoryTest {

    private PlayerFactory playerFactoryUnderTest;

    @BeforeEach
    void setUp() {
        playerFactoryUnderTest = new PlayerFactory();
    }

    /**
     * Given a name.
     * When creating a player.
     * Then should create a new player with the given name.
     */
    @ParameterizedTest
    @ValueSource(strings = {"nom1", "nom2"})
    void createPlayer(String name) {
        //given

        //when
        Player player = playerFactoryUnderTest.createPlayer(name);

        //then
        assertThat(player.getName()).isEqualTo(name);
    }

    /**
     * Given a list of names.
     * When creating a list of players.
     * Then should create a new list of players with the given names in order.
     */
    @ParameterizedTest
    @CsvSource(value = {"nom1, nom2", "nom2, nom1"})
    void createPlayerList(String name1, String name2) {
        //given
        List<String> nameList = new ArrayList<>(Arrays.asList(name1, name2));
        List<Player> playerList;

        //when
        playerList = playerFactoryUnderTest.createPlayerList(nameList);

        //then
        assertThat(playerList.get(0).getName()).isEqualTo(name1);
        assertThat(playerList.get(1).getName()).isEqualTo(name2);
    }
}