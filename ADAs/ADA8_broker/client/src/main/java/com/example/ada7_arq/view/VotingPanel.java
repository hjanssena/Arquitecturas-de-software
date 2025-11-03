package com.example.ada7_arq.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

import com.example.ada7_arq.controller.CandidateController;
import com.example.ada7_arq.model.Candidate;

public class VotingPanel implements Subscriber {
    private String name;
    private int votes;
    private Label nameLabel;
    private Label countLabel;
    private Button voteButton;

    private CandidateController controller;

    public VotingPanel(String name, CandidateController controller) {
        this.controller = controller;

        this.name = name;
        this.votes = 0;

        this.nameLabel = new Label(name);
        this.nameLabel.getStyleClass().add("candidate-name");

        this.countLabel = new Label("0 votos");
        this.countLabel.getStyleClass().add("vote-count");

        this.voteButton = new Button("Votar por " + name);
        this.voteButton.getStyleClass().add("vote-button");

        this.voteButton.setOnAction(e -> handleVote());
    }

    private void handleVote() {
        controller.submitVote(name);
    }

    public HBox getComponentRow() {
        nameLabel.setPrefWidth(150);
        countLabel.setPrefWidth(100);

        HBox row = new HBox(40);
        row.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        row.getChildren().addAll(nameLabel, countLabel, voteButton);

        return row;
    }

    @Override
    public void update(ArrayList<Candidate> candidateList) {
        for (Candidate candidate : candidateList) {
            if (candidate.getName().equals(name)) {
                votes = candidate.getVotes();
                countLabel.setText(votes + " votos");
            }
        }
    }

    @Override
    public void subscribe(CandidateController controller) {
        controller.subscribeToCandidateList(this);
    }
}
