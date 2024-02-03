package com.svalero.search.controller;

import com.svalero.search.utils.ShowAlert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class AppController implements Initializable {

    @FXML
    private Button buttonSearch;
    @FXML
    private Button buttonOpenKeyWords;

    @FXML
    private Button buttonOpenWebs;
    @FXML
    private TabPane tabSearch;
    @FXML
    private Label KeyPathLabel;
    @FXML
    private Label WebPathLabel;
    @FXML
    private ChoiceBox<Integer> maxTabsChoiceBox;
    private File fileKeyWords;
    private File fileWebs;
    private Scanner keyFileScanner;
    private Scanner webFileScanner;
    private List<String> keyWordList;
    private String urlText;

    public AppController() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<Integer> choices = FXCollections.observableArrayList(1, 2, 3, 4, 5);
        maxTabsChoiceBox.setItems(choices);
        maxTabsChoiceBox.setValue(5);
    }

    public void openKeyWords(ActionEvent event) {
        Stage stage = (Stage) this.buttonOpenKeyWords.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\Users\\richa\\OneDrive\\Escritorio\\Practicas Presenciales Segundo\\rastreadorWeb\\src\\main\\resources\\com.svalero.search\\target\\file"));
        File selectedKeyFile = fileChooser.showOpenDialog(stage);
        this.fileKeyWords = selectedKeyFile;
        this.KeyPathLabel.setText(this.fileKeyWords.getAbsolutePath());
        keyWordList = new ArrayList<String>();
        try {
            keyFileScanner = new Scanner(this.fileKeyWords);
            while (keyFileScanner.hasNextLine()) {
                String keyText = keyFileScanner.nextLine();
                System.out.println("Keys" + keyText);
                keyWordList.add(keyText);
                System.out.println("KeysLIst" + keyWordList);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void openWebs(ActionEvent event) {
        Stage stage = (Stage) this.buttonOpenWebs.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\Users\\richa\\OneDrive\\Escritorio\\Practicas Presenciales Segundo\\rastreadorWeb\\src\\main\\resources\\com.svalero.search\\target\\file"));
        File selectedWebFile = fileChooser.showOpenDialog(stage);
        this.fileWebs = selectedWebFile;
        this.WebPathLabel.setText(this.fileWebs.getAbsolutePath());
        try {
            webFileScanner = new Scanner(this.fileWebs);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void search(ActionEvent event) throws IOException {

        int maxTabs = maxTabsChoiceBox.getValue();

        if (tabSearch.getTabs().size() < maxTabs) {
            while (webFileScanner.hasNextLine()) {
                String urlText = webFileScanner.nextLine();
                System.out.println(urlText);

                createTask(urlText, keyWordList);
            }
        } else {
            ShowAlert.showErrorAlert("Information", "DEMASIADAS PESTAÑAS", "máximo de pestañas alcanzado");
        }
    }

    public void createTask(String urlText, List<String> keyWordList) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.svalero.search/searchPane.fxml"));
        System.out.println(keyWordList);
        SearchController searchController = new SearchController(urlText, keyWordList);
        loader.setController(searchController);
        AnchorPane anchorPane = loader.load();
        tabSearch.getTabs().add(new Tab(urlText, anchorPane));
    }

}
