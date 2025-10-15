package com.example.ada7_arq.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

import java.util.ArrayList;

import com.example.ada7_arq.controller.CandidateListController;
import com.example.ada7_arq.model.Candidate;
import com.example.ada7_arq.model.Publisher;

public class MainView {

    private Publisher publisher;
    private CandidateListController controller;

    public MainView(CandidateListController controller, Publisher publisher) {
        this.controller = controller;
        this.publisher = publisher;
    }

    public VBox generateView(ArrayList<Candidate> candidates) {
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
        VBox votingVBox = new VBox(15);
        for (Candidate candidate : candidates) {
            CandidateView candidateView = new CandidateView(candidate.getName(), controller);
            candidateView.subscribe(publisher);
            votingVBox.getChildren().add(candidateView.getComponentRow());
        }

        return votingVBox;
    }

    private HBox createNavPane() {
        GraphButton circleGraphButton = new GraphButton("Grafica de pastel", 200);
        GraphButton barGraphButton = new GraphButton("Grafica de barras", 200);

        circleGraphButton.setOnAction(e -> {
            CircleGraphView circleGraphView = new CircleGraphView();
            publisher.addSubscriber(circleGraphView);
            circleGraphView.update(controller.getCandidates());
            circleGraphView.show();
        });
        barGraphButton.setOnAction(e -> {
            BarGraphView barGraphView = new BarGraphView();
            publisher.addSubscriber(barGraphView);
            barGraphView.update(controller.getCandidates());
            barGraphView.show();
        });

        HBox navigationBox = new HBox(20);
        navigationBox.setAlignment(Pos.CENTER);
        navigationBox.getChildren().addAll(circleGraphButton, barGraphButton);

        return navigationBox;
    }
}
