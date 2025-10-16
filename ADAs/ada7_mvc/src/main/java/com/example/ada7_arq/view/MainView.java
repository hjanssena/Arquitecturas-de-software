package com.example.ada7_arq.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

import java.util.ArrayList;

import com.example.ada7_arq.controller.CandidateListController;
import com.example.ada7_arq.model.Candidate;
import com.example.ada7_arq.model.ExecutionLog;

public class MainView {

    private CandidateListController controller;

    public MainView(CandidateListController controller) {
        this.controller = controller;
    }

    public VBox generateView(ArrayList<Candidate> candidates) {
        // Logear ejecucion
        ExecutionLog.getInstance().log(this.getClass().getName(), "Invocación método generateView");

        VBox votingPane = createVotingPane(candidates);
        HBox navigationBox = createNavPane();

        // Armamos layout
        VBox root = new VBox(40);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(30, 30, 30, 30));
        Label titleLabel = new Label("Votación");
        titleLabel.getStyleClass().add("title-label");
        root.getChildren().addAll(titleLabel, votingPane, navigationBox);

        return root;
    }

    private VBox createVotingPane(ArrayList<Candidate> candidates) {
        // Logear ejecucion
        ExecutionLog.getInstance().log(this.getClass().getName(), "Invocación método createVotingPane");

        VBox votingVBox = new VBox(15);
        for (Candidate candidate : candidates) {
            VotingPanel candidateView = new VotingPanel(candidate.getName(), controller);
            candidateView.subscribe(controller);
            votingVBox.getChildren().add(candidateView.getComponentRow());
        }

        return votingVBox;
    }

    private HBox createNavPane() {
        // Logear ejecucion
        ExecutionLog.getInstance().log(this.getClass().getName(), "Invocación método createNavPane");

        GraphButton circleGraphButton = new GraphButton("Grafica de pastel", 200);
        GraphButton barGraphButton = new GraphButton("Grafica de barras", 200);

        circleGraphButton.setOnAction(e -> {
            CircleGraphView circleGraphView = new CircleGraphView();
            circleGraphView.subscribe(controller);
            ;
            circleGraphView.update(controller.getCandidates());
            circleGraphView.show();
        });
        barGraphButton.setOnAction(e -> {
            BarGraphView barGraphView = new BarGraphView();
            barGraphView.subscribe(controller);
            barGraphView.update(controller.getCandidates());
            barGraphView.show();
        });

        HBox navigationBox = new HBox(20);
        navigationBox.setAlignment(Pos.CENTER);
        navigationBox.getChildren().addAll(circleGraphButton, barGraphButton);

        return navigationBox;
    }
}
