
package gui;

import domein.Spel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class AantalSpelersScherm extends VBox{
    private Label titel;
    private Spel dc;
    private Button btn3Spelers, btn4Spelers, btn5Spelers, btn6Spelers;
    private TilePane knoppen;
    private WelkomScherm hoofdPaneel;

    public AantalSpelersScherm(WelkomScherm parent) {
        this.dc = new Spel();
        this.hoofdPaneel = parent;
        buildGUI();
        
    }

    private void buildGUI() {
        titel = new Label("Met hoeveel spelers wens je te spelen?");
        titel.setStyle("-fx-font: 35 arial; ");
        titel.setTextFill(Paint.valueOf("white"));
        titel.setUnderline(true);
        titel.setPadding(new Insets(10, 100, 10, 300));
        
        
        btn3Spelers = new Button("3 Spelers");
        btn3Spelers.getStyleClass().add("btnStyle");
        btn3Spelers.setStyle("-fx-font: 35 arial;");
        btn3Spelers.setPrefSize(400, 250);
        
        btn4Spelers = new Button("4 Spelers");
        btn4Spelers.getStyleClass().add("btnStyle");
        btn4Spelers.setStyle("-fx-font: 35 arial;");
        btn4Spelers.setPrefSize(400, 250);
        
        btn5Spelers = new Button("5 Spelers");
        btn5Spelers.getStyleClass().add("btnStyle");
        btn5Spelers.setStyle("-fx-font: 35 arial;");
        btn5Spelers.setPrefSize(400, 250);
        
        btn6Spelers = new Button("6 Spelers");
        btn6Spelers.getStyleClass().add("btnStyle");
        btn6Spelers.setStyle("-fx-font: 35 arial;");
        btn6Spelers.setPrefSize(400, 250);
        
        knoppen = new TilePane(btn3Spelers, btn4Spelers, btn5Spelers, btn6Spelers);
        knoppen.setHgap(10);
        knoppen.setVgap(10);
        knoppen.setPrefColumns(2);
        knoppen.setPrefTileWidth(500);
        knoppen.setPrefTileHeight(300);
        
        Image image = new Image("/images/door.png");
        ImageView exit = new ImageView();
        exit.setImage(image);
        
        HBox header = new HBox(exit, titel);
        
        this.getChildren().addAll(header, knoppen);        
        this.knoppen.setAlignment(Pos.CENTER);
        this.setId("woodenBackground");
        
        btn3Spelers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openNieuweScene(3);
            }
        });
        btn4Spelers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openNieuweScene(4);
            }
        });
        btn5Spelers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openNieuweScene(5);
            }
        });
        btn6Spelers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openNieuweScene(6);
            }
        });
    }
    private void openNieuweScene(int aantalSpelers) {
        dc.setAantalSpelers(aantalSpelers);
        Stage stage = (Stage) getScene().getWindow(); 
        stage.setTitle("For Sale : Namen ingeven");
        Scene scene = new Scene(new NamenIngevenScherm(dc, AantalSpelersScherm.this), 1250,700);
        stage.setScene(scene);
        scene.getStylesheets().add("main/style.css");
    }
    
    
}
