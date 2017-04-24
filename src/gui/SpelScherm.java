package gui;

import domein.Spel;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;


public class SpelScherm extends BorderPane {

    private Spel dc;
    private Label[] lblNaamSpelers;
    private Label[] lblMuntenSpelers, lblGebodenMunten;
    private HBox hboxRondeHuiskaarten;
    private VBox[] vboxSpelers;
    private HBox[] spelersKaarten;
    boolean completed = false;
    private Label lblRonde, lblHoogsteBod, lblSpelerAanZet;
    private Button btnPassen;
    private Button btnBieden;
    private TextField txfAantalBieden;
    private Scanner s;
    private HBox hboxTop, hboxBottom;
    private StackPane spCenter;
    private int playerCounter=0, rondeCounter=0;
    private Image[] rondeHuiskaarten;
    private ImageView[] ivHuiskaarten;
    private TextField txfInzet;
    private VBox vboxRightSide;
    
    

    public SpelScherm(Spel dc) {
        this.dc = dc;
        int aantalSp = dc.getAantalSpelers();
        lblSpelerAanZet = new Label("Speler aan zet: " + dc.geefSpeler(playerCounter).getNaam());
        lblRonde = new Label("Ronde: ");
        btnPassen = new Button("Passen");
        btnBieden = new Button("Bieden");
        s = new Scanner(System.in);
        lblNaamSpelers = new Label[dc.getAantalSpelers()];
        hboxRondeHuiskaarten = new HBox();
        lblMuntenSpelers = new Label[dc.getAantalSpelers()];
        lblGebodenMunten = new Label[dc.getAantalSpelers()];
        vboxSpelers = new VBox[aantalSp];
        spelersKaarten = new HBox[dc.getAantalSpelers()];
        hboxBottom = new HBox(20);
        hboxTop = new HBox(20);
        spCenter = new StackPane();
        lblHoogsteBod = new Label("Hoogste bod:" +dc.geefHoogsteBod());
        rondeHuiskaarten = new Image[dc.getAantalSpelers()];
        ivHuiskaarten = new ImageView[dc.getAantalSpelers()];
        txfInzet = new TextField("Hoeveel munten?");
        vboxRightSide = new VBox(10);
        buildGUI();
        stelLayoutIn();
        speelFase1();
        
    }
    private void showExceptionMessage() {
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Te weinig munten");
        alert.setHeaderText("Je kan niet bieden omdat je te weinig munten hebt. Er wordt automatisch gepast!");
        alert.setContentText("Je krijgt de kaart met waarde ");                                           
        alert.showAndWait();
    }

    private void buildGUI() {
        for (int i = 0; i < dc.getAantalSpelers() ; i++) {
            lblNaamSpelers[i] = new Label("Speler "+ (i+1) +": "+ dc.geefSpeler(i).getNaam());
            lblMuntenSpelers[i] = new Label("Aantal munten: "+ (dc.geefSpeler(i).getMunten()));
            spelersKaarten[i] = new HBox(10);
            vboxSpelers[i] = new VBox();
            lblGebodenMunten[i] = new Label("Geboden munten: " + dc.geefSpeler(i).getGebodenMunten());
            vboxSpelers[i].getChildren().addAll(lblNaamSpelers[i], lblMuntenSpelers[i], lblGebodenMunten[i], spelersKaarten[i]);

        }
        hboxBottom.getChildren().addAll(vboxSpelers[0], vboxSpelers[1], vboxSpelers[2]);
        switch (dc.getAantalSpelers()) {
            case 4:
                hboxTop.getChildren().addAll(vboxSpelers[3]);
                break;
            case 5:
                hboxTop.getChildren().addAll(vboxSpelers[3],vboxSpelers[4]);
                break;
            case 6:
                hboxTop.getChildren().addAll(vboxSpelers[3],vboxSpelers[4], vboxSpelers[5]);
                break;
        }
        spCenter.getChildren().addAll(lblRonde, lblHoogsteBod, hboxRondeHuiskaarten, lblSpelerAanZet);
        vboxRightSide.getChildren().addAll(btnBieden,txfInzet );
        vboxRightSide.setAlignment(Pos.CENTER);

        
        this.setTop(hboxTop);
        this.setBottom(hboxBottom);
        this.setLeft(btnPassen);
        BorderPane.setAlignment(btnPassen, Pos.CENTER_LEFT);
        this.setRight(vboxRightSide);
        BorderPane.setAlignment(vboxRightSide, Pos.CENTER_RIGHT);
        this.setCenter(spCenter);
      
        this.setId("woodenBackground");
    }
    private void stelLayoutIn(){
        btnBieden.setTextAlignment(TextAlignment.CENTER);
        btnBieden.getStyleClass().add("btnStyle");
        btnBieden.setPrefSize(130, 150);
        btnBieden.setStyle("-fx-font: 25 arial;");
        
        btnPassen.setTextAlignment(TextAlignment.CENTER);
        btnPassen.getStyleClass().add("btnStyle");
        btnPassen.setPrefSize(130, 150);
        btnPassen.setStyle("-fx-font: 25 arial;"); 
        
        for (int i = 0; i < dc.getAantalSpelers() ; i++){
        lblNaamSpelers[i].setStyle("-fx-font: 20 arial;");
        lblNaamSpelers[i].setTextFill(Paint.valueOf("white"));    
        
        lblGebodenMunten[i].setStyle("-fx-font: 20 arial;");
        lblGebodenMunten[i].setTextFill(Paint.valueOf("white"));
        
        lblMuntenSpelers[i].setStyle("-fx-font: 20 arial;");
        lblMuntenSpelers[i].setTextFill(Paint.valueOf("white"));
        }
        lblHoogsteBod.setStyle("-fx-font: 20 arial;");
        lblHoogsteBod.setTextFill(Paint.valueOf("white"));
        
        lblRonde.setStyle("-fx-font: 20 arial;");
        lblRonde.setTextFill(Paint.valueOf("white"));
        
        lblSpelerAanZet.setStyle("-fx-font: 20 arial;");
        lblSpelerAanZet.setTextFill(Paint.valueOf("white")); 
        
        hboxBottom.setAlignment(Pos.CENTER);
        hboxBottom.setSpacing(40);
        hboxBottom.minHeight(225);
        hboxBottom.maxHeight(225);
        hboxTop.setAlignment(Pos.CENTER);
        hboxTop.setSpacing(40);
        hboxTop.minHeight(225);
        hboxTop.maxHeight(225);
        spCenter.maxHeight(250);
        hboxRondeHuiskaarten.setAlignment(Pos.CENTER);
        
        StackPane.setAlignment(lblRonde,Pos.TOP_LEFT);
        StackPane.setAlignment(lblHoogsteBod, Pos.TOP_RIGHT);
        StackPane.setAlignment(lblSpelerAanZet,Pos.TOP_CENTER);
        StackPane.setAlignment(hboxRondeHuiskaarten, Pos.CENTER);
        
        for (VBox vboxspeler : vboxSpelers) {
            vboxspeler.maxHeight(225);
            vboxspeler.minHeight(225);
            
        }
        
    }

    private void speelFase1() {
        dc.maakDeck();
        dc.maakCheques();
        dc.shuffleDeck();
        dc.verwijderKaarten4Spelers();
        startNieuweRonde();
        lblRonde.setText("Ronde "+rondeCounter);
        btnPassen.setOnAction(new EventHandler<ActionEvent>() {                        
            @Override
            public void handle(ActionEvent event) {
                dc.vindLaagsteKaartInRonde();
                dc.keuzePassenBieden("ja", playerCounter);               
                spelersKaarten[playerCounter].getChildren().addAll(ivHuiskaarten[dc.geefLaagsteRondeKaart()]);
                hboxRondeHuiskaarten.getChildren().remove(ivHuiskaarten[dc.geefLaagsteRondeKaart()]);
                dc.toonRondeKaarten().remove(dc.geefLaagsteRondeKaart());
                    
                checkPlayerCounter();
                if(dc.getAantalBeurtenOvergeslaan() < dc.getAantalSpelers()){
                    checkVolgendeSpeler();
                }
                lblSpelerAanZet.setText("Speler aanzet: " + dc.geefSpeler(playerCounter).getNaam());
                if(dc.toonRondeKaarten().size() == 1){
                    if(rondeCounter != (int) Math.floor(30/dc.getAantalSpelers())){
                    dc.spelersUpdateEindeRonde();
                    dc.vindLaagsteKaartInRonde();
                    spelersKaarten[playerCounter].getChildren().addAll(ivHuiskaarten[dc.geefLaagsteRondeKaart()]);
                    hboxRondeHuiskaarten.getChildren().remove(ivHuiskaarten[dc.geefLaagsteRondeKaart()]);
                    dc.toonRondeKaarten().remove(dc.geefLaagsteRondeKaart());
                    startNieuweRonde();
                    } else{
                        dc.spelersUpdateEindeRonde();
                        dc.toonRondeKaarten().remove(0);
                        startFase2();
                    }
                }
            }

        });
        btnBieden.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(checkMuntenSpeler(playerCounter) != true){                        
                    dc.bieden(Integer.parseInt(txfInzet.getText()), playerCounter);
                };
            }                
        });
             
    }
    private boolean checkMuntenSpeler(int playerCounter) {
        if (dc.geefSpeler(playerCounter).getMunten() == 0 || dc.geefSpeler(playerCounter).getMunten() <= dc.geefHoogsteBod()) {
            showExceptionMessage();
            dc.setAantalBeurtenOvergeslaan(dc.getAantalBeurtenOvergeslaan() + 1);
            dc.vindLaagsteKaartInRonde();
            dc.geefSpeler(playerCounter).getSpelerKaarten().add(dc.toonRondeKaarten().get(dc.geefLaagsteRondeKaart()));
            spelersKaarten[playerCounter].getChildren().addAll(ivHuiskaarten[dc.geefLaagsteRondeKaart()]);
            hboxRondeHuiskaarten.getChildren().remove(ivHuiskaarten[dc.geefLaagsteRondeKaart()]);
            dc.toonRondeKaarten().remove(dc.geefLaagsteRondeKaart());
            if (!dc.toonRondeKaarten().isEmpty()) {
                dc.geefSpeler(playerCounter).setHeeftBeurtOvergeslaan(true);
            }
            return true;
        }
        return false;
    }
    private void checkPlayerCounter(){
        if(playerCounter <dc.getAantalSpelers()-1){
            playerCounter++;
        } else 
            playerCounter=0;
    }
    private void checkVolgendeSpeler(){
        while(dc.geefSpeler(playerCounter).getHeeftBeurtOvergeslaan() == true){
            checkPlayerCounter();
        }         
    }
    private void startNieuweRonde(){
        rondeCounter++;
        lblRonde.setText("Ronde "+rondeCounter);
        
        dc.resetHoogsteBod();
        dc.spelerReset();
        dc.aantalOverGeslagenReset();
        dc.voegRondeKaartenToe();
        for (int j = 0; j < dc.getAantalSpelers(); j++) {
            rondeHuiskaarten[j] = new Image(dc.geefKaartString(dc.toonRondeKaarten().get(j).getWaarde()));
            ivHuiskaarten[j] = new ImageView(rondeHuiskaarten[j]);
            ivHuiskaarten[j].setFitHeight(166);
            ivHuiskaarten[j].setFitWidth(100);
            hboxRondeHuiskaarten.getChildren().add(ivHuiskaarten[j]);
        }
    }
    
    
    
    
    private void startFase2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}





