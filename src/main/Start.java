/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import gui.WelkomScherm;

/**
 *
 * @author Nick
 */
public class Start extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        WelkomScherm root = new WelkomScherm();
        
        Scene scene = new Scene(root, 1250, 700);

        
        StackPane root1 = new StackPane();
        root.setId("pane");
        primaryStage.setTitle("For Sale");
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        primaryStage.getIcons().add(new Image("/images/ico.jpg"));
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
