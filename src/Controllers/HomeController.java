package Controllers;

import Resources.thisThingsData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.util.Random;

public class HomeController {
    //Scene switching
    public Button button;
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

    //Data processing
    public TextArea textArea;

    private ObservableList<thisThingsData> generateData() {
        ObservableList<thisThingsData> data = FXCollections.observableArrayList();
        data.clear();

        for (int i = 1; i < 7; i++) {
            thisThingsData temp = new thisThingsData(String.valueOf(i));
            data.add(temp);
        }
        for (int i = 0; i < 25; i++) {
            Random rn = new Random();
            int number = rn.nextInt(6)+1;
            System.out.println(number);
            for (thisThingsData x : data) {
                if (x.getValue().equals(String.valueOf(number))) x.appendAmount();
            }
        }

        return data;
    }
}
