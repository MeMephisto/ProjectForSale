/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.Spel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author Jonas
 */
public class NamenIngevenScherm extends StackPane {
    private Spel dc;
    private AantalSpelersScherm keuzePaneel;   
    private Label titel;
    private GridPane namen;
    private Button btnBevestig;
    private HBox header;
    private TextField[] txfNamen;
    private SpelScherm SpelS;

    public NamenIngevenScherm(Spel dc, AantalSpelersScherm keuzePaneel) {
        this.dc = dc;
        this.keuzePaneel = keuzePaneel;
        buildGUI(dc.getAantalSpelers());
    }


    private void buildGUI(int aantal) {
        titel = new Label("Geef de namen in voor de spelers.");
        titel.setTextFill(Paint.valueOf("white"));
        titel.setStyle("-fx-font: 35 arial;");
        titel.setPadding(new Insets(10, 100, 10, 250));
        
        namen = new GridPane();
        namen.setAlignment(Pos.CENTER);
        txfNamen = new TextField[aantal];
        
        for (int i = 0; i < aantal; i++){           
            Label lblSpeler = new Label("Speler "+ (i+1));
            
            lblSpeler.setStyle("-fx-font: 35 arial;");
            lblSpeler.setTextFill(Paint.valueOf("white"));
            lblSpeler.setId("naamSpeler"+ (i+1));           
            
            
            // tekstveld voorzien
            txfNamen[i] = new TextField();
           
            namen.add(lblSpeler, 0, i+1); // label toevoegen
            namen.add(txfNamen[i], 1, i+1); // tekstveld toevoegen
        }
        namen.setPadding(new Insets(10));
        namen.setHgap(20);
        namen.setVgap(35);
        
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHalignment(HPos.CENTER);
        col1.setMinWidth(USE_PREF_SIZE);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setMaxWidth(600);
        namen.getColumnConstraints().addAll(col1, col2);
        
        btnBevestig = new Button("Bevestigen");
        btnBevestig.setTextAlignment(TextAlignment.CENTER);
        btnBevestig.getStyleClass().add("btnStyle");
        btnBevestig.setPrefSize(200, 70);
        btnBevestig.setStyle("-fx-font: 30 arial;");
        
        
        btnBevestig.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                for (int i = 0; i < dc.getAantalSpelers(); i++) {
                    String naam = txfNamen[i].getText();
                    dc.maakSpelers(naam, i+1);
                }                
                Stage stage = (Stage) getScene().getWindow(); 
                stage.setTitle("For Sale : Spelen Fase 1");
                Scene scene = new Scene(new SpelScherm(dc), 1250,700);
                stage.setScene(scene);
                scene.getStylesheets().add("main/style.css");
            }
            });
        
        Image image = new Image("/images/door.png");
        ImageView exit = new ImageView();
        exit.setImage(image);
                
        header = new HBox(exit, titel);
               
        this.getChildren().addAll(header, namen, btnBevestig);
        this.header.setAlignment(Pos.TOP_LEFT);
        this.namen.setAlignment(Pos.CENTER);
        StackPane.setAlignment(btnBevestig, Pos.BOTTOM_CENTER);
        this.setId("woodenBackground");
        
    }
    
}
