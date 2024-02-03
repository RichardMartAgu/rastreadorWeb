package com.svalero.search.controller;

import com.svalero.search.task.SearchTask;
import com.svalero.search.utils.ShowAlert;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.io.File;
import java.lang.reflect.MalformedParametersException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    @FXML
    private Label lbStatus;
    @FXML
    private ProgressBar pbProgress;
    private List<String> keyWords;
    private String urlWeb;

    private SearchTask searchTask;

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
                            ShowAlert.showInformationAlert("Información", "Información", "El filtro se ha realizado con éxito");
                        }
                    }
                    searchTask.messageProperty().addListener((observableValue, oldValue, newValue) -> {
                        lbStatus.setText(newValue);
                    });

            new Thread(searchTask).start();

        } catch (MalformedParametersException murle) {
            murle.printStackTrace();
        }
    }
