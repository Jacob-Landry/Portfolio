package com.jsmarier.jeu21.Controller;

import com.jsmarier.jeu21.Business.Player.SimplePlayerDecorator;
import com.jsmarier.jeu21.Model.DecisionEnum;
import com.jsmarier.jeu21.Model.Interfaces.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Function;

public class GameGraphicalController {

    private Function<DecisionEnum, Void> playerTurnAction;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text currentPlayerText;

    @FXML
    private Button hitButton;

    @FXML
    private HBox player1CardArea;

    @FXML
    private Text player1Name;

    @FXML
    private HBox player2CardArea;

    @FXML
    private Text player2Name;

    @FXML
    private HBox player3CardArea;

    @FXML
    private Text player3Name;

    @FXML
    private HBox player4CardArea;

    @FXML
    private Text player4Name;

    @FXML
    private Button standButton;

    @FXML
    void hitButtonClickAction(ActionEvent event) {
        playerTurnAction.apply(DecisionEnum.HIT);
    }

    @FXML
    void standButtonClickAction(ActionEvent event) {
        playerTurnAction.apply(DecisionEnum.STAND);
    }



    @FXML
    void initialize() {
        assert currentPlayerText != null : "fx:id=\"currentPlayerText\" was not injected: check your FXML file 'Jeu.fxml'.";
        assert hitButton != null : "fx:id=\"hitButton\" was not injected: check your FXML file 'Jeu.fxml'.";
        assert player1CardArea != null : "fx:id=\"player1CardArea\" was not injected: check your FXML file 'Jeu.fxml'.";
        assert player1Name != null : "fx:id=\"player1Name\" was not injected: check your FXML file 'Jeu.fxml'.";
        assert player2CardArea != null : "fx:id=\"player2CardArea\" was not injected: check your FXML file 'Jeu.fxml'.";
        assert player2Name != null : "fx:id=\"player2Name\" was not injected: check your FXML file 'Jeu.fxml'.";
        assert player3CardArea != null : "fx:id=\"player3CardArea\" was not injected: check your FXML file 'Jeu.fxml'.";
        assert player3Name != null : "fx:id=\"player3Name\" was not injected: check your FXML file 'Jeu.fxml'.";
        assert player4CardArea != null : "fx:id=\"player4CardArea\" was not injected: check your FXML file 'Jeu.fxml'.";
        assert player4Name != null : "fx:id=\"player4Name\" was not injected: check your FXML file 'Jeu.fxml'.";
        assert standButton != null : "fx:id=\"standButton\" was not injected: check your FXML file 'Jeu.fxml'.";

    }

    public void setPlayerNames(List<Player> playerList) {
        player1Name.setText(playerList.get(0).getName());
        player2Name.setText(playerList.get(1).getName());
        player3Name.setText(playerList.get(2).getName());
        player4Name.setText(playerList.get(3).getName());
    }

    public Button getHitButton() {
        return hitButton;
    }

    public Button getStandButton() {
        return standButton;
    }

    public void setActivePlayerName(String name) {
        this.currentPlayerText.setText(name);
    }

    public void setPlayerTurnAction(Function<DecisionEnum, Void> playerTurnAction) {
        this.playerTurnAction = playerTurnAction;
    }

    public void setPlayerIdAsEliminated(int playerId) {
        Text playerName = switch (playerId) {
            case 0 -> player1Name;
            case 1 -> player2Name;
            case 2 -> player3Name;
            case 3 -> player4Name;
            default -> null;
        };

        playerName.getStyleClass().add("eliminated");
    }

    public void addCardToPlayerArea(int playerId, String cardName) {
        Pane cardArea = switch (playerId) {
            case 0 -> player1CardArea;
            case 1 -> player2CardArea;
            case 2 -> player3CardArea;
            case 3 -> player4CardArea;
            default -> null;
        };

        Pane card = new Pane();
        card.setPrefHeight(160);
        card.setPrefWidth(115);

        card.getStyleClass().add("card");
        card.getStyleClass().add(cardName.toLowerCase());

        cardArea.getChildren().add(card);
    }
}