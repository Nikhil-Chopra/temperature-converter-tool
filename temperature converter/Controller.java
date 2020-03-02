package com.internshala.javafxapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    public Label welcomeLabel;

    @FXML
    public ChoiceBox<String> choiceBox;

    @FXML
    public TextField userInputField;

    @FXML
    public Button convertButton;

    private static final String x="Celsius to Fahrenheit";
    private static final String y="Fahrenheit to Celsius";

    private boolean isx=true;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        choiceBox.getItems().add(x);
        choiceBox.getItems().add(y);

        choiceBox.setValue(x);

        choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> isx= newValue.equals(x));

        convertButton.setOnAction(event -> convert());

    }

    private void convert() {
        String input=userInputField.getText();
            float enteredTemperature=0.0f;
        try {
            enteredTemperature = Float.parseFloat(input);
        }catch (Exception ex){
            warnUser();
            return;
        }

        float newTemperature;

        if(isx)
        {
            newTemperature=enteredTemperature*9/5 +32;
        }
        else{
            newTemperature=(enteredTemperature-32)*5/9;
        }
        display(newTemperature);
    }

    private void warnUser() {
        Alert alert= new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Occured");
        alert.setHeaderText("Invalid Temperature Entered");
        alert.setContentText("Please enter the valid temperature");
        alert.show();

    }

    private void display(float newTemperature) {
        String unit= isx?"F":"C";

        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Result");
        alert.setContentText("The new temperature is:" + newTemperature+unit);
        alert.show();

    }
}
