package Controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class GraphController {
    //Scene switching
    public Button backButton;
    private Scene homeScene;

    public void setHomeScene(Scene homeScene) {
        this.homeScene = homeScene;
    }

    public void openHomeScene(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(homeScene);
    }

    //Data processing
    //ARRAY/OBJECT PLACEHOLDER
    public int [] data;
    public TableView contentTable;

    public void setData(int [] data) {
        this.data = data;
    }

    public void run() {
        //FULL METHOD PLACEHOLDER
        if (data != null) {
            for (int i = 0; i < data.length; i++) {
                System.out.println(data [i]);
            }
        } else System.out.println("Data is empty");
    }
}
