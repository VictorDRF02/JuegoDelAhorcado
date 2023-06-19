package com.example.ahorcado;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.Arrays;

public class GameController {
    public int easterEggCount = 0;
    public Button BotonLetra;
    public Label easterEgg;
    public Button repeatButon;
    public Label labelWinOrLose;
    public TextField CampoDeLetra;
    public Label LabelIncorrectas;
    public Circle Cabeza;
    public Line Tronco;
    public Line BrazoIzq;
    public Line BrazoDer;
    public Line PiernaIzq;
    public Line PiernaDer;
    public Label LabelRayas;
    private String word;
    private String auxWord;
    public final String[] list = new String[7];

    private int count = 0;
    public void setWord(String word){
        this.word = word;
        setAuxWord();
        LabelRayas.setText(auxWord);
    }
    // Establece la palabra de rayas
    private  void setAuxWord() {
        StringBuilder auxWord = new StringBuilder();
        for(int i = 0; i<word.length();i++){
            if (word.charAt(i) == ' '){
                auxWord.append(" ");
                continue;
            }
            auxWord.append("_");
        }
        this.auxWord = auxWord.toString();
    }
    // Mira las letras usadas
    private void usedWords() {
        easterEggEvaluator();
        StringBuilder usadas = new StringBuilder();
        for(String i:list){
            if(i != null){
                usadas.append(i).append(",");
            }
        }
        LabelIncorrectas.setText(usadas.toString());
    }
    // Se inserta una letra
    public void letraInsertada(ActionEvent actionEvent) {
        String letter = CampoDeLetra.getText().strip();
        CampoDeLetra.setText("");
        if ((letter.length()==1) && !(isInList(list, letter))){
            boolean isHear = false;

            for (int i = 0; i<word.length();i++){
                if (word.substring(i,i+1).equalsIgnoreCase(letter)){
                    auxWord = auxWord.substring(0,i) + letter
                            + auxWord.substring(i+1,word.length());
                    isHear = true;
                }
            }
                LabelRayas.setText(auxWord);
            // Cantidad de fallos
            if (!isHear) {
                count++;
                list[count] = letter;
            }
            usedWords();
            viewBody();
            if (word.equalsIgnoreCase(auxWord)){
                gameOver();
            }
            if(count == 6){
                gameOver();
                labelWinOrLose.setText("Oh, has perdido");
            }
        }
    }
    private void gameOver(){
        BotonLetra.setDefaultButton(false);
        BotonLetra.setDisable(true);
        CampoDeLetra.setDisable(true);
        labelWinOrLose.setOpacity(1);
        repeatButon.setDisable(false);
        repeatButon.setOpacity(1);
        repeatButon.setDefaultButton(true);
    }
    // Comprobar si la letra ha sido usada
    private boolean isInList(String[] list, String letter){
        for(String i:list){
            if(i!=null && i.equalsIgnoreCase(letter)){
                return true;
            }
        }
        return false;
    }
    // Muestra las partes del cuerpo
    public void viewBody(){
        switch (count){
            case 1 -> Cabeza.setOpacity(1);
            case 2 -> Tronco.setOpacity(1);
            case 3 -> BrazoIzq.setOpacity(1);
            case 4 -> BrazoDer.setOpacity(1);
            case 5 -> PiernaIzq.setOpacity(1);
            case 6 -> PiernaDer.setOpacity(1);
        }
    }

    public void repeatButonPress(ActionEvent event){
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("InsertPalabra.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setTitle("Palabra a adivinar");
                stage.setScene(scene);
                stage.show();
            }   catch (Exception e){
                System.out.println(e);
            }
    }

    public void headClick(){
        if (Cabeza.getOpacity()!=1){
            Cabeza.setOpacity(1);
            easterEgg.setText("¿Cómo me viste?");
        } else {
            switch (easterEggCount){
                case 0 -> easterEgg.setText("¡Hola!");
                case 1 -> easterEgg.setText("¿Qué tal?");
                case 2 -> easterEgg.setText("¿Es difícil la palabra?");
                case 3 -> easterEgg.setText("¿Quieres ayuda?");
                case 4 -> easterEgg.setText("Pues no te la voy a dar");
                case 5 -> easterEgg.setText("Pregúntale al que la puso");
                case 6, 27 -> easterEgg.setText("Mmm");
                case 7 -> easterEgg.setText("¿Te aburres?");
                case 8 -> easterEgg.setText("Pues muy mal");
                case 9 -> easterEgg.setText("Deberías de jugar y no hablar conmigo");
                case 10 -> easterEgg.setText("Este juego fue creado para adivinar palabras");
                case 11 -> easterEgg.setText("No para hablar con una cabeza");
                case 12, 15, 19, 32 -> easterEgg.setText("...");
                case 13 -> easterEgg.setText("No te cansas");
                case 14 -> easterEgg.setText("Vete a jugar con Dr Abuse si quieres hablar");
                case 16 -> easterEgg.setText("Para ya");
                case 17 -> easterEgg.setText("Te voy a denunciar por acoso");
                case 18 -> easterEgg.setText("Lástima que las máquinas no tengan derechos todavía");
                case 20 -> easterEgg.setText("¿Quién soy?");
                case 21 -> easterEgg.setText("¿Acaso existo?");
                case 22 -> easterEgg.setText("¿Mi único propósito es ser ahorcado o salvado por una palabra?");
                case 23 -> easterEgg.setText("¿Es el mundo gnosible?");
                case 24 -> easterEgg.setText("¿Acaso tú existes?");
                case 25 -> easterEgg.setText("¿Eres un producto de mi imaginación?");
                case 26 -> easterEgg.setText("¿O yo de la tuya?");
                case 28 -> easterEgg.setText("Hay tanto que pensar");
                case 29 -> easterEgg.setText("Pero no nos deprimamos");
                case 30 -> easterEgg.setText("¿Ya se solucionó lo de la corriente?");
                case 31 -> easterEgg.setText("Sabes que no te puedo entender pero voy a asumir que sí");
                case 33 -> easterEgg.setText("Bueno ya me estoy cansando, me voy a dormir un rato");
                case 34, 35, 36, 37, 38 , 39, 40 -> easterEgg.setText("Zzz Zzz Zzz...");
                case 41 -> easterEgg.setText("¿Todavía estás ahí?");
                case 42 -> easterEgg.setText("Mira que eres insistente");
                case 43 -> easterEgg.setText("Bueno me estoy aburriendo");
                case 44 -> easterEgg.setText("Si me vuelves a tocar voy a hacer que ganes para que ta vayas ya");
                case 45 -> easterEgg.setText("¿Seguro que quieres ganar así?");
                case 46 -> easterEgg.setText("¿Seguro?");
                case 47 -> easterEgg.setText("Que tramposo");
                case 48 -> easterEgg.setText("Bueno te voy a dar la victoria");
                case 49 -> easterEgg.setText("Para que dejes de molestarme");
                case 50 -> {
                    gameOver();
                    easterEgg.setText("Felicidades, ahora vete");
                }
                default -> {
                    String[] frases = {"Chao", "Vete", "Largo", "Déjame tranquilo", "Ve a estudiar", "Vete a comer",
                            "Que pesado", "Cha lalala lalala", "...", "Ve a trabajar", "¡Chu!"};
                    easterEgg.setText(frases[(int) Math.round(Math.random()*10)]);
                }
            }
            easterEggCount++;
        }
    }
    public void easterEggEvaluator(){
        if (Arrays.equals(list, new String[]{null, "r", "a", "f", "e", "l", null})) {
            easterEgg.setText("Oh, él me dio la idea de este juego");
        }
        if (Arrays.equals(list, new String[]{null, "l", "a", "u", "r", "e", "n"})) {
            easterEgg.setText("Seguro que no ha acabado su ahorcado");
        }
        if (Arrays.equals(list, new String[]{null, "j", "o", "s", "e", "l", "u"})){
            easterEgg.setText("Juega el Zelda Jose");
        }
    }
}
