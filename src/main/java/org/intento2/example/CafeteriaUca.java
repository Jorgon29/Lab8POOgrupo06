package org.intento2.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CafeteriaUca extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Cafeteria.fxml"));
        System.out.println("Antes de new Scene");
        try {
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
            System.out.println("Despues de new scene");
            stage.setTitle("Cafe UCA");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e){
            System.out.println("Exception en start");
            System.out.println(e.getMessage());
            return;
        }

    }

    public static void main(String[] args) {
        launch(CafeteriaUca.class);
    }
}