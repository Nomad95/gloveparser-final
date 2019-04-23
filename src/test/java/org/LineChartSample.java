package org;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class LineChartSample extends Application {

    @Override public void start(Stage stage) {
        stage.setTitle("Line Chart Sample");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Month");
        final LineChart<Number,Number> lineChart =
                new LineChart<>(xAxis,yAxis);

        lineChart.setTitle("Stock Monitoring, 2010");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Portfolio 1");

        series1.getData().add(new XYChart.Data(1, 23));
        series1.getData().add(new XYChart.Data(2, 14));
        series1.getData().add(new XYChart.Data(3, 15));
        series1.getData().add(new XYChart.Data(4, 24));
        series1.getData().add(new XYChart.Data(5, 34));
        series1.getData().add(new XYChart.Data(6, 36));
        series1.getData().add(new XYChart.Data(7, 22));
        series1.getData().add(new XYChart.Data(8, 45));
        series1.getData().add(new XYChart.Data(10, 43));
        series1.getData().add(new XYChart.Data(20, 17));
        series1.getData().add(new XYChart.Data(32, 29));
        series1.getData().add(new XYChart.Data(40, 25));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Portfolio 2");
        series2.getData().add(new XYChart.Data(1, 33));
        series2.getData().add(new XYChart.Data(2, 34));
        series2.getData().add(new XYChart.Data(3, 25));
        series2.getData().add(new XYChart.Data(4, 44));
        series2.getData().add(new XYChart.Data(5, 39));
        series2.getData().add(new XYChart.Data(6, 16));
        series2.getData().add(new XYChart.Data(7, 55));
        series2.getData().add(new XYChart.Data(8, 54));
        series2.getData().add(new XYChart.Data(10, 48));
        series2.getData().add(new XYChart.Data(20, 27));
        series2.getData().add(new XYChart.Data(35, 37));
        series2.getData().add(new XYChart.Data(40, 29));

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Portfolio 3");
        series3.getData().add(new XYChart.Data(1, 44));
        series3.getData().add(new XYChart.Data(2, 35));
        series3.getData().add(new XYChart.Data(3, 36));
        series3.getData().add(new XYChart.Data(4, 33));
        series3.getData().add(new XYChart.Data(5, 31));
        series3.getData().add(new XYChart.Data(6, 26));
        series3.getData().add(new XYChart.Data(7, 22));
        series3.getData().add(new XYChart.Data(8, 25));
        series3.getData().add(new XYChart.Data(10, 43));
        series3.getData().add(new XYChart.Data(20, 44));
        series3.getData().add(new XYChart.Data(30, 45));
        series3.getData().add(new XYChart.Data(40, 44));

        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().addAll(series1, series2, series3);

        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
