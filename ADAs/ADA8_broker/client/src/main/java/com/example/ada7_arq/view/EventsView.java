package com.example.ada7_arq.view;

import java.util.ArrayList;

import com.example.ada7_arq.controller.CandidateController;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class EventsView extends Application {

    private CandidateController controller;

    public EventsView(CandidateController controller) {
        this.controller = controller;
    }

    @Override
    public void start(Stage primaryStage) {
        // Create the main layout
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));

        HBox searchBox = new HBox(10);
        searchBox.setPadding(new Insets(10));
        Button refreshButton = new Button("Refrescar");

        searchBox.getChildren().addAll(refreshButton);

        // Create the text area for displaying results
        TextArea resultArea = new TextArea();
        resultArea.setPromptText("Resultados de busqueda...");
        resultArea.setWrapText(true);
        resultArea.setEditable(false);

        // Set up the layout
        root.getChildren().addAll(searchBox, resultArea);

        // Set minimum size for the text area
        resultArea.setMinHeight(400);

        // Create the scene
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Servicios");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Add event handler for search button
        refreshButton.setOnAction(event -> {
            ArrayList<String> events = controller.getEvents();

            StringBuilder result = new StringBuilder();
            for (String e : events) {
                result.append(e + "\n");
            }
            resultArea.setText(result.toString());
        });
    }
}