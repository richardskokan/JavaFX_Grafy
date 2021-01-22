package Controllers;

import Resources.thisThingsData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class GraphController {
    //Scene switching
    public Button backButton;
    private Scene homeScene;
    private HomeController homeController;

    public void setHomeScene(Scene homeScene, HomeController homeController) {
        this.homeScene = homeScene;
        this.homeController = homeController;
    }

    public void openHomeScene(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(homeScene);
    }

    //Data processing
    public ObservableList<thisThingsData> data;
    public TableView contentTable;
    public BarChart barChart;

    public void setData(ObservableList<thisThingsData> data) {
        this.data = data;
    }

    public void run() {
        //Allowing usage of Return button
        homeController.allowReturn();

        //Setting table data
        contentTable.setItems(data);

        TableColumn<thisThingsData, String> values = new TableColumn<>("Value");
        values.setCellValueFactory(new PropertyValueFactory<>(data.get(0).valueProperty().getName()));
        TableColumn<thisThingsData, String> amounts = new TableColumn<>("Amount");
        amounts.setCellValueFactory(new PropertyValueFactory<>(String.valueOf(data.get(0).amountProperty().getName())));

        contentTable.getColumns().setAll(values, amounts);

        //Setting chart data
        ObservableList<XYChart.Series<String, Integer>> chartData = FXCollections.observableArrayList();
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName("Amount");
        for (thisThingsData x : data) {
            series.getData().add(new XYChart.Data(x.getValue(), x.getAmount()));
        }
        chartData.add(series);
        barChart.setData(chartData);
    }
}
