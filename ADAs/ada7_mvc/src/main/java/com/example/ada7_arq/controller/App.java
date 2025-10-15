package com.example.ada7_arq.controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.example.ada7_arq.model.CandidateList;
import com.example.ada7_arq.model.Publisher;
import com.example.ada7_arq.view.MainView;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Iniciamos modelo y observer
        Publisher publisher = new Publisher();
        CandidateList candidateList = new CandidateList(publisher);

        // Iniciamos controlador
        CandidateListController controller = new CandidateListController(candidateList);

        // Iniciamos vista y ligamos a publisher
        stage.setTitle("Votaciones importantes");
        MainView mainView = new MainView(controller, publisher);
        Scene scene = new Scene(mainView.generateView(candidateList.getCandidates()), 600, 450);
        scene.getStylesheets().add(getClass().getResource("/com/example/ada7_arq/styles.css").toExternalForm());
        stage.setScene(scene);
        publisher.update(candidateList.getCandidates());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}