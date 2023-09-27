//Développeur ayant travailler sur ce projet. Moi: Jacob Landry et Un coéquipier: Jean Sebastien Marier

package com.jsmarier.jeu21;

import com.jsmarier.jeu21.Business.Deck.SimpleDeckDecorator;
import com.jsmarier.jeu21.Business.Player.PlayerFactory;
import com.jsmarier.jeu21.Controller.Dealer;
import com.jsmarier.jeu21.Controller.GameGraphicalController;
import com.jsmarier.jeu21.Controller.MenuGraphicalController;
import com.jsmarier.jeu21.Model.Beans.DeckBean;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class App extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        PlayerFactory playerFactory = new PlayerFactory();
        primaryStage.setTitle("BLACKJACK : BATTLE ROYALE EDITION");
        primaryStage.setResizable(false);

        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("Jeu.fxml"));
        loader2.setControllerFactory(param -> {
            return new GameGraphicalController();
        });
        Scene gameScene = new Scene(loader2.load());

        Dealer dealer = new Dealer(loader2.getController(), new SimpleDeckDecorator(
                new DeckBean(new ArrayList<>())
        ));

        GameGraphicalController graphicalController = loader2.getController();
        graphicalController.setPlayerTurnAction(decision -> {
            dealer.playerTurn(decision);

            return null;
        });

        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("Menu.fxml"));
        loader1.setControllerFactory(param -> {

            Function<List<String>, Void> startGameAction = strings -> {
                dealer.setPlayerMapFromList(playerFactory.createPlayerList(strings));
                dealer.initializeGame();
                dealer.startPlayerTurn();

                primaryStage.setScene(gameScene);

                return null;
            };

            return new MenuGraphicalController(startGameAction);
        });
        Scene menuScene = new Scene(loader1.load());

        primaryStage.setScene(menuScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
