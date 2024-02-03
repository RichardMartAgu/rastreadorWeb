package com.svalero.search;

import com.svalero.search.controller.AppController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void init() throws Exception {
        System.out.println("Hello, Starting Application");
        super.init();
    }

    @Override
    public void start(Stage stage) throws IOException {

        Stage mainStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.svalero.search/mainWindow.fxml"));
        loader.setController(new AppController());
        Scene scene = new Scene(loader.load());
        mainStage.setScene(scene);
        mainStage.setTitle("Search Application");
        mainStage.show();

    }

    public void stop() throws Exception {
        System.out.println("Goodbye!");
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }
}
