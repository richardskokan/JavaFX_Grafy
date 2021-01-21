package Controllers;

import Resources.thisThingsData;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;

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
    public ObservableList<thisThingsData> data;
    public TableView contentTable;

    public void setData(ObservableList<thisThingsData> data) {
        this.data = data;
    }

    public void run() {
        contentTable.setItems(data);

        TableColumn<thisThingsData, String> values = new TableColumn<>("Value");
        values.setCellValueFactory(new PropertyValueFactory<>(data.get(0).valueProperty().getName()));
        TableColumn<thisThingsData, String> amounts = new TableColumn<>("Amount");
        amounts.setCellValueFactory(new PropertyValueFactory<>(String.valueOf(data.get(0).amountProperty().getName())));

        contentTable.getColumns().setAll(values, amounts);
    }
}
