package com.example.ada7_arq.controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.example.ada7_arq.model.CandidateList;
import com.example.ada7_arq.model.ExecutionLog;
import com.example.ada7_arq.view.MainView;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Iniciamos MVC
        CandidateList candidateList = new CandidateList();
        CandidateListController controller = new CandidateListController(candidateList);
        MainView mainView = new MainView(controller);

        // Stage y escena
        stage.setTitle("Votaciones importantes");
        Scene scene = new Scene(mainView.generateView(candidateList.getCandidates()), 600, 450);
        scene.getStylesheets().add(getClass().getResource("/com/example/ada7_arq/styles.css").toExternalForm());
        stage.setScene(scene);
        candidateList.triggerUpdate();
        stage.show();

        // Logear ejecucion
        ExecutionLog.getInstance().log(this.getClass().getName(), "Invocación método start");
    }

    public static void main(String[] args) {
        launch();
    }
}