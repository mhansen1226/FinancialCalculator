package main;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.text.DecimalFormat;


public class FinancialCalculator extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        final String stylesheet = getClass().getResource("style.css").toExternalForm();

        VBox root = new VBox();

        Label principalLabel = new Label("Principal Amount: ");

        HBox principalInput = new HBox();
        TextArea principalTextArea = new TextArea("1000");
        Label principalUnits = new Label("$");
        principalInput.getChildren().addAll(principalTextArea, principalUnits);

        Label timeLabel = new Label("Time: ");

        HBox timeInput = new HBox();
        TextArea timeTextArea = new TextArea("10");
        Label timeUnits = new Label("Years");
        timeInput.getChildren().addAll(timeTextArea, timeUnits);

        HBox rateInput = new HBox();
        Slider rateSlider = new Slider(0, 100, 5);
        Label rateLabel = new Label("Rate: " + rateSlider.getValue() + "%");
        rateInput.getChildren().addAll(rateSlider, rateLabel);

        HBox buttons = new HBox();
        Button simpleInterestButton = new Button("Simple Interest");
        Button compundInterestButton = new Button("Compund Interest");
        buttons.getChildren().addAll(simpleInterestButton, compundInterestButton);

        HBox output = new HBox();
        TextArea amountTextArea = new TextArea();
        amountTextArea.setPromptText("After interest");
        Label amountUnits = new Label("$");
        output.getChildren().addAll(amountTextArea,amountUnits);

        rateSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            DecimalFormat df = new DecimalFormat("#.#");
            rateLabel.setText("Rate: " + df.format(newValue) + "%");
        });

        simpleInterestButton.setOnAction(event -> {
            double principal = Float.parseFloat(principalTextArea.getText());
            double time = Float.parseFloat(timeTextArea.getText());
            double interestRate = rateSlider.getValue()/100;
            double amount = principal * (1 + interestRate * time);
            DecimalFormat df = new DecimalFormat("#.##");
            amountTextArea.setText(df.format(amount));
        });

        compundInterestButton.setOnAction(event -> {
            double principal = Float.parseFloat(principalTextArea.getText());
            double time = Float.parseFloat(timeTextArea.getText());
            double interestRate = rateSlider.getValue()/100;
            double amount = principal * Math.pow(1 + interestRate, time);
            DecimalFormat df = new DecimalFormat("#.##");
            amountTextArea.setText(df.format(amount));
        });

        root.getChildren().addAll(principalLabel, principalInput, timeLabel, timeInput, rateLabel, rateInput, buttons, output);

        Scene scene = new Scene(root, 400, 400);
        scene.getStylesheets().add(stylesheet);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Financial Calculator");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
