package com.example.ada7_arq.controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

import com.example.ada7_arq.model.CandidateList;
import com.example.ada7_arq.view.MainView;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduzca la ip del broker");
        String ip = scanner.nextLine();
        System.out.println("Introduzca el puerto del broker");
        int port = Integer.parseInt(scanner.nextLine());
        scanner.close();

        // Iniciamos MVC
        CandidateList candidateList = new CandidateList(ip, port);
        CandidateController controller = new CandidateController(candidateList);
        MainView mainView = new MainView(controller);

        // Stage y escena
        stage.setTitle("Votaciones importantes");
        Scene scene = new Scene(mainView.generateView(candidateList.getCandidates()), 600, 450);
        scene.getStylesheets().add(getClass().getResource("/com/example/ada7_arq/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        candidateList.triggerUpdate();
    }

    public static void main(String[] args) {
        launch();
    }
}