package gui;

import domein.Spel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WelkomScherm extends Pane{
    private AantalSpelersScherm aantalSpelersKeuze;

    public WelkomScherm() {
        Button btnStart = new Button("Start spel");
        Button btnHighscore = new Button("Highscores");
        Button btnAfsluiten = new Button("Spel afsluiten");
                
        btnStart.setMaxSize(750, 300);
        btnHighscore.setMaxSize(750, 300);
        btnAfsluiten.setMaxSize(750, 300);
        
        btnStart.getStyleClass().add("addBobOk");
        btnHighscore.getStyleClass().add("addBobOk");
        btnAfsluiten.getStyleClass().add("addBobOk");
        
        btnStart.setStyle("-fx-font: 35 arial; -fx-base: #b6e7c9;");
        btnHighscore.setStyle("-fx-font: 35 arial; -fx-base: #b6e7c9;");
        btnAfsluiten.setStyle("-fx-font: 35 arial; -fx-base: #b6e7c9;");
        
        btnAfsluiten.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                Stage stage = (Stage) btnAfsluiten.getScene().getWindow();
                stage.close();
            }
            });
        
        btnStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) getScene().getWindow();        
                // nieuwe titel instellen
                stage.setTitle("For Sale : Keuze aantal Spelers");
                Scene scene = new Scene(new AantalSpelersScherm(WelkomScherm.this), 1250,700);
                stage.setScene(scene);
                scene.getStylesheets().add("main/style.css");
            }
        });
        
        VBox vbButtons = new VBox();
        vbButtons.setSpacing(10);
        vbButtons.setMaxSize(1500, 5000);
        vbButtons.getChildren().addAll(btnStart, btnHighscore, btnAfsluiten);
        vbButtons.setLayoutX(850);
        vbButtons.setLayoutY(225);       
        
        this.getChildren().addAll(vbButtons);
        
    }
    
}
