package com.example.tmp_kursovaya;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("PersonOverview.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

//    @Override
//    public void init() throws Exception {
//
//    }

    public void showPersonOverview() throws Exception {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("PersonOverview.fxml"));
//        AnchorPane personOverview = (AnchorPane) loader.load();
//        rootLayout.setCenter(personOverview);
//
//        PersonOverview controller = loader.getController();
//        controller.setMainApp(this);


    }

    public static void main(String[] args) {
        launch();
    }
}