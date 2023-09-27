module com.jsmarier.jeu21 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jsmarier.jeu21.Controller;
    opens com.jsmarier.jeu21 to javafx.fxml;
    exports com.jsmarier.jeu21;
}