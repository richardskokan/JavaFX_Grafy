package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

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
        int [] data = generateData();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(graphScene);

        graphController.setData(data);
        graphController.run();
    }

    //Data processing
    public TextArea textArea;

    private int [] generateData() {
        int [] data = new int [1];
        data [0] = Integer.parseInt(textArea.getText());

        return data;
    }
}
