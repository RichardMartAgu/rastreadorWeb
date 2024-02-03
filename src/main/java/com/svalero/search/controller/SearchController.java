package com.svalero.search.controller;

import com.svalero.search.task.SearchTask;
import com.svalero.search.utils.ShowAlert;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;

import java.io.File;
import java.lang.reflect.MalformedParametersException;
import java.net.URL;
import java.util.*;

public class SearchController implements Initializable {
    @FXML
    private Label lbStatus;
    @FXML
    private ProgressBar pbProgress;
    @FXML
    private TextArea textArea;
    private List<String> keyWords;
    private String urlWeb;

    private List<HashMap<String,Integer>> hasList;

    private SearchTask searchTask;
    private Map<String, Integer> hashMap = new HashMap<>();

    public SearchController(String urlWeb, List<String> keyWords) {
        this.keyWords = keyWords;
        this.urlWeb = urlWeb;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        try {
            searchTask = new SearchTask(this.urlWeb, this.keyWords);

            pbProgress.progressProperty().unbind();
            pbProgress.progressProperty().bind(searchTask.progressProperty());

            searchTask.stateProperty().addListener((observableValue, oldState, newState) -> {
                if (newState == Worker.State.SUCCEEDED) {
                    ShowAlert.showInformationAlert("Información", "Información", "La busqueda se ha realizado con éxito");
                }
            });
            searchTask.setOnSucceeded(event -> {
                hashMap = searchTask.getValue();
                hasList = new ArrayList<HashMap<String, Integer>>();
                for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
                    String key = entry.getKey();
                    Integer value = entry.getValue();
                    System.out.println("Clave: " + key + ", Valor: " + value);
                    textArea.appendText("Clave: " + key + ", Valor: " + value + "\n");
                }
                    });
            searchTask.messageProperty().addListener((observableValue, oldValue, newValue) -> {
                lbStatus.setText(newValue);
            });

            new Thread(searchTask).start();

        } catch (MalformedParametersException murle) {
            murle.printStackTrace();
        }
    }
}
