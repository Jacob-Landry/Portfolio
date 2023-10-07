package controller;

import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import model.*;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import javafx.event.ActionEvent;
import model.modeDePaiement.ModeDePaiement;
import model.modeDePaiement.ModeDePaiementFactory;

import java.net.URL;
import java.util.ResourceBundle;

//Développeur ayant travailler sur ce projet. Moi: Jacob Landry et Une coéquipière: Dorine Morin

public class ControleurSystemeFacturation extends Application {

    private Magasin magasin;

    private Comptable comptable;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button boutonCreerFacture;

    @FXML
    private TextField champsNom;

    @FXML
    private TextField champsPrenom;

    @FXML
    private TextField champsTotalAvantTaxes;

    @FXML
    private TextField champsTotalDesTaxes;

    @FXML
    private MenuButton menuMethodePaiement;

    @FXML
    private Text totalAvecTaxes;

    @FXML
    private Text totalDesDons;

    @FXML
    void creerFacture(ActionEvent event) {
        //Création facture
        String prenom = champsPrenom.getText();
        String nom = champsNom.getText();
        double totalAvantTaxes = Double.parseDouble(champsTotalAvantTaxes.getText());
        double totalDesTaxes = Double.parseDouble(champsTotalDesTaxes.getText());
        String modeDePaiement = menuMethodePaiement.getText();
        System.out.println(modeDePaiement);

        this.magasin.ajouterFacture(comptable.creerNouvelleFacture(prenom + " " + nom, totalAvantTaxes, totalDesTaxes, new ModeDePaiementFactory().creerModeDePaiement(modeDePaiement)));
        this.totalAvecTaxes.setText(magasin.getListeDeFactures().get(magasin.getListeDeFactures().size()-1).getTotalAvecTaxes());

        //Mise à jour des dons
        String totalDesDons = String.valueOf(magasin.getTotalDesDons()).replace(".", ",") + "$";

        this.totalAvecTaxes.setText(magasin.getListeDeFactures().get(magasin.getListeDeFactures().size() - 1).getTotalAvecTaxes());
        this.totalDesDons.setText(totalDesDons);
    }

    @FXML
    void initialize() {
        comptable = new Comptable();
        magasin = new Magasin(comptable);


        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                menuMethodePaiement.setText(((MenuItem) e.getSource()).getText());
            }
        };

        menuMethodePaiement.getItems().get(0).setOnAction(event1);
        menuMethodePaiement.getItems().get(1).setOnAction(event1);
        menuMethodePaiement.getItems().get(2).setOnAction(event1);

        this.totalAvecTaxes.setText("Inconnu");

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Système de facturation");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/TP2.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setScene(scene);
        primaryStage.show();

    }




}
