package com.example.ahorcado;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class InicioController implements Initializable {


    @FXML
    public void insertWord(Event event){
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("InsertPalabra.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Palabra a adivinar");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }   catch (Exception e){
            System.out.println(e);
        }
    }
    @FXML
    public void exit(Event event){
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}
}
