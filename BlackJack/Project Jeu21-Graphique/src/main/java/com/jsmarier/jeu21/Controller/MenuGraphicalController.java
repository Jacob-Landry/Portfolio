package com.jsmarier.jeu21.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Function;

public class MenuGraphicalController {

    public MenuGraphicalController(Function<List<String>, Void> startGameAction) {
        this.startGameAction = startGameAction;
    }

    private Function<List<String>, Void> startGameAction;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField player1Name;

    @FXML
    private TextField player2Name;

    @FXML
    private TextField player3Name;

    @FXML
    private TextField player4Name;

    @FXML
    private Button startGameButton;

    @FXML
    void startGameButtonClickAction(ActionEvent event) {
        startGameAction.apply(getNames());
    }

    private List<String> getNames() {
        return Arrays.asList(player1Name.getText(), player2Name.getText(), player3Name.getText(), player4Name.getText());
    }

    @FXML
    void initialize() {
        assert player1Name != null : "fx:id=\"player1Name\" was not injected: check your FXML file 'Menu.fxml'.";
        assert player2Name != null : "fx:id=\"player2Name\" was not injected: check your FXML file 'Menu.fxml'.";
        assert player3Name != null : "fx:id=\"player3Name\" was not injected: check your FXML file 'Menu.fxml'.";
        assert player4Name != null : "fx:id=\"player4Name\" was not injected: check your FXML file 'Menu.fxml'.";
        assert startGameButton != null : "fx:id=\"startGameButton\" was not injected: check your FXML file 'Menu.fxml'.";

    }

}


