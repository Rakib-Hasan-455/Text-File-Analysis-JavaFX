package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Locale;
import java.util.StringTokenizer;

import java.awt.event.ActionEvent;
import java.io.File;

public class Controller {
    File f;
    FileChooser fchooser;

    String filePathName = "";
    int totalLine = 0;
    int totalWord = 0;
    int totalSen = 0;
    int totalVowel = 0;

    @FXML
    public Button browseBtn;
    @FXML
    public TextField pathField;
    @FXML
    public Button analysisBtn;
    @FXML
    public TextArea resultField;
    @FXML
    public Label statusLebel;



    public void fileReader(javafx.event.ActionEvent actionEvent) {
        try {
            fchooser = new FileChooser();
            f = fchooser.showOpenDialog(new Stage());
            filePathName = f.getPath();
            pathField.setText(filePathName);
        } catch (NullPointerException e){
            statusLebel.setText(e+" in choose file");
        }
    }

    public void fileAnalyzer(javafx.event.ActionEvent actionEvent) {
        String fileText = "";
        String tempLine = "";
        try{
            FileReader fR = new FileReader(filePathName);
            BufferedReader bR = new BufferedReader(fR);
            while((tempLine = bR.readLine()) != null){
                totalLine++;
                fileText += tempLine;
                fileText += "\n";
                StringTokenizer st = new StringTokenizer(tempLine);
                while (st.hasMoreTokens()){
                    totalWord++;
                    String tempWord = st.nextToken();
                    tempWord = tempWord.toLowerCase(Locale.ROOT);
                    for (int i = 0; i < tempWord.length(); i++){
                        if(tempWord.charAt(i) == '.'){
                            totalSen++;
                        } else if(tempWord.charAt(i) == 'a' ||
                                tempWord.charAt(i) == 'e' ||
                                tempWord.charAt(i) == 'i' ||
                                tempWord.charAt(i) == 'o' ||
                                tempWord.charAt(i) == 'u') {
                            totalVowel++;
                        }
                    }
                }
            }
        } catch (Exception e){
            statusLebel.setText(""+e);
        }
        String msg = "Total line: "+totalLine;
        msg += "\nTotal Sentence: "+totalSen;
        msg += "\nTotal word: "+totalWord;
        msg += "\nTotal Vowel: "+totalVowel;

        resultField.setText(msg);
        statusLebel.setText("Successful...");
        totalLine = totalSen= totalWord = totalVowel = 0;
    }
}
