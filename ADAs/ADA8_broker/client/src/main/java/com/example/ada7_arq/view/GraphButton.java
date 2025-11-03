package com.example.ada7_arq.view;

import javafx.scene.control.Button;
import javafx.scene.layout.Priority;
import javafx.scene.layout.HBox;

public class GraphButton extends Button {

    public GraphButton(String text, double buttonWidth) {
        super(text);

        this.setPrefWidth(buttonWidth);
        this.setMinWidth(buttonWidth);
        this.setMaxWidth(buttonWidth);

        this.getStyleClass().add("nav-button");

        HBox.setHgrow(this, Priority.ALWAYS);
    }
}