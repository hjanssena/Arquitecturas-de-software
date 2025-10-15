package com.example.ada7_arq.view;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.ArrayList;

import com.example.ada7_arq.model.Candidate;
import com.example.ada7_arq.model.Publisher;

public class BarGraphView implements Subscriber {
    private final XYChart.Series<String, Number> series;
    private Stage stage;

    public BarGraphView() {
        this.stage = new Stage();
        this.stage.setTitle("Bar Graph Results");

        // Crear ejes
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Candidatos");
        yAxis.setLabel("Votos");

        // Crear grafica de barras
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Voting Results (Bar Chart)");
        barChart.setLegendVisible(false);
        barChart.setAnimated(false);

        // Series de datos
        series = new XYChart.Series<>();
        barChart.getData().add(series);

        // Escena y estilos
        Scene scene = new Scene(barChart, 600, 400);
        try {
            String cssPath = getClass().getResource("/com/example/ada7_arq/styles.css").toExternalForm();
            scene.getStylesheets().add(cssPath);
        } catch (Exception e) {
            System.err.println("Could not load styles.css in BarGraphView.");
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
        for (Candidate candidate : candidateList) {
            XYChart.Data<String, Number> data = new XYChart.Data<>(candidate.getName(), candidate.getVotes());
            series.getData().add(data);
        }
    }

    @Override
    public void subscribe(Publisher publisher) {
        publisher.addSubscriber(this);
    }
}
