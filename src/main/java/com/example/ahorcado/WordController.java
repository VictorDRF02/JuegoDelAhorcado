package com.example.ahorcado;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class WordController implements Initializable {
    public String word;
    @FXML
    private Label errorLabel;
    @FXML
    TextField wordSpace;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {    }

    @FXML
    public void click(Event event){
        word = wordSpace.getText().strip();
        if (validateWord()){
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Juego.fxml"));
                GameController gameController = new GameController();
                fxmlLoader.setController(gameController);

                Scene scene = new Scene(fxmlLoader.load());
                stage.setTitle("Ahorcado ver 0.04");
                stage.setScene(scene);
                gameController.setWord(word);
                stage.setResizable(false);
                stage.show();
            }   catch (Exception e){
                System.out.println(e);
            }
        } else {
            wordSpace.setText("");
            errorLabel.setOpacity(1);
        }
    }
    private boolean validateWord(){
        if (word.length()==0 || word.length()>25){
            return false;
        }

        char[] listDigits = {'0','1','2','3','4','5','6','7','8','9'};

        for (char i: word.toCharArray()){
            for (char j: listDigits){
                if (i==j){
                    return false;
                }
            }
        }
        return true;
    }
}
