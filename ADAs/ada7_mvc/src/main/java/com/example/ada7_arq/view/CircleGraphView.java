package com.example.ada7_arq.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

import java.util.ArrayList;

import com.example.ada7_arq.model.Candidate;
import com.example.ada7_arq.model.Publisher;

public class CircleGraphView implements Subscriber {
    private final ObservableList<PieChart.Data> pieChartData;
    private final PieChart pieChart;
    private Stage stage;

    public CircleGraphView() {
        this.stage = new Stage();
        this.stage.setTitle("Circle Graph Results");
        this.pieChartData = FXCollections.observableArrayList();

        // Crear grafica de pastel
        this.pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Voting Results (Circle Graph)");
        pieChart.setLabelsVisible(true);
        pieChart.setStartAngle(90);

        // Escena y estilos
        Scene scene = new Scene(pieChart, 600, 400);
        try {
            String cssPath = getClass().getResource("/com/example/ada7_arq/styles.css").toExternalForm();
            scene.getStylesheets().add(cssPath);
        } catch (Exception e) {
            System.err.println("Could not load styles.css in CircleGraphView: " + e.getMessage());
        }

        this.stage.setScene(scene);
    }

    public void show() {
        if (stage != null) {
            stage.show();
        }
    }

    @Override
    public void update(ArrayList<Candidate> candidateList) {
        pieChartData.clear(); // Limpiamos porque si no se duplican las entradas
        for (Candidate candidate : candidateList) {
            PieChart.Data data = new PieChart.Data(candidate.getName() + " (" + candidate.getVotes() + ")",
                    candidate.getVotes());
            pieChartData.add(data);
        }
    }

    @Override
    public void subscribe(Publisher publisher) {
        publisher.addSubscriber(this);
    }
}