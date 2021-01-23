package Controllers;

import Resources.thisThingsData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    //Scene switching
    public Button simButton;
    public Button retButton;
    private Scene graphScene;
    private GraphController graphController;

    public void setGraphScene(Scene graphScene, GraphController graphController) {
        this.graphScene = graphScene;
        this.graphController = graphController;
    }

    public void openGraphScene(ActionEvent actionEvent) {
        ObservableList<thisThingsData> data = generateData();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(graphScene);

        graphController.setData(data);
        graphController.run();
    }

    public void returnToGraphScene(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(graphScene);
    }

    //Data processing
    public Button mode;
    public TextField numberOfDice;
    public TextField numberOfDiceSides;
    public TextField numberOfThrows;
    public TextField numberOfBalls;
    public TextField numberOfCollumns;

    private Random rn = new Random();

    private ObservableList<thisThingsData> generateData() {
        ObservableList<thisThingsData> data = FXCollections.observableArrayList();

        switch (mode.getText()) {
            case "Dice":
                data = diceSimulation(Integer.parseInt(numberOfDice.getText()), Integer.parseInt(numberOfDiceSides.getText()), Integer.parseInt(numberOfThrows.getText()));
                break;
            case "Galton Board":
                data = galtonBoardSimulation(Integer.parseInt(numberOfBalls.getText()), Integer.parseInt(numberOfCollumns.getText()));
                break;
        }

        return data;
    }

    public ObservableList<thisThingsData> diceSimulation(int numberOfDice, int numberOfDiceSides, int numberOfTries) {
        ObservableList<thisThingsData> data = FXCollections.observableArrayList();
        data.clear();

        for (int i = numberOfDice; i < numberOfDiceSides * numberOfDice + 1; i++) {
            thisThingsData temp = new thisThingsData(String.valueOf(i));
            data.add(temp);
        }

        for (int i = 0; i < numberOfTries; i++) {

            int number = 0;

            for (int j = 0; j < numberOfDice; j++) {
                number += rn.nextInt(numberOfDiceSides)+1;
            }

            for (thisThingsData x : data) {
                if (x.getValue().equals(String.valueOf(number))) x.appendAmount();
            }
        }

        return data;
    }

    public ObservableList<thisThingsData> galtonBoardSimulation(int numberOfBalls, int numberOfColumns) {
        ObservableList<thisThingsData> data = FXCollections.observableArrayList();
        data.clear();

        for (int i = 1; i < numberOfColumns + 1; i++) {
            thisThingsData temp = new thisThingsData(String.valueOf(i));
            data.add(temp);
        }

        for (int i = 0; i < numberOfBalls; i++) {
            int finalPosition = 1;
            for (int j = 0; j < numberOfColumns - 1; j++) {
                if (rn.nextBoolean()) finalPosition++;
            }

            for (thisThingsData x : data) {
                if (x.getValue().equals(String.valueOf(finalPosition))) x.appendAmount();
            }
        }

        return data;
    }

    public void changeMode() {
        if (mode.getText().equals("Dice")) {
            mode.setText("Galton Board");

            numberOfDice.setDisable(true);
            numberOfDiceSides.setDisable(true);
            numberOfThrows.setDisable(true);

            numberOfBalls.setDisable(false);
            numberOfCollumns.setDisable(false);
        } else {
            mode.setText("Dice");

            numberOfDice.setDisable(false);
            numberOfDiceSides.setDisable(false);
            numberOfThrows.setDisable(false);

            numberOfBalls.setDisable(true);
            numberOfCollumns.setDisable(true);
        }

        checkRunnable();
    }

    public void checkRunnable() {
        if (mode.getText().equals("Dice")) {
            if (numberOfDice.getText().matches("\\d\\d*") && numberOfDiceSides.getText().matches("\\d\\d*") && numberOfThrows.getText().matches("\\d\\d*")
                    && Integer.parseInt(numberOfDice.getText()) > 0 && Integer.parseInt(numberOfDiceSides.getText()) > 0 && Integer.parseInt(numberOfThrows.getText()) > 0) {
                simButton.setDisable(false);
            } else {
                simButton.setDisable(true);
            }
        } else {
            if (numberOfBalls.getText().matches("\\d\\d*") && numberOfCollumns.getText().matches("\\d\\d*")
                    && Integer.parseInt(numberOfBalls.getText()) > 0 && Integer.parseInt(numberOfCollumns.getText()) > 1) {
                simButton.setDisable(false);
            } else {
                simButton.setDisable(true);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        simButton.setDisable(true);
        retButton.setDisable(true);

        numberOfBalls.setDisable(true);
        numberOfCollumns.setDisable(true);
    }

    public void allowReturn() {
        retButton.setDisable(false);
    }
}
