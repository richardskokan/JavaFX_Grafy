import Controllers.GraphController;
import Controllers.HomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        //Loading home scene
        FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("Resources/home.fxml"));
        Parent home = homeLoader.load();
        Scene homeScene = new Scene(home);

        //Loading graph scene
        FXMLLoader graphLoader = new FXMLLoader(getClass().getResource("Resources/graph.fxml"));
        Parent graph = graphLoader.load();
        Scene graphScene = new Scene(graph);

        //Parsing graph scene to home scene so they can switch
        HomeController homeController = homeLoader.getController();
        homeController.setGraphScene(graphScene, graphLoader.getController());

        //Parsing home scene to graph scene so they can switch
        GraphController graphController = graphLoader.getController();
        graphController.setHomeScene(homeScene, homeController);
        
        //Setting basic stage settings
        primaryStage.setScene(homeScene);
        primaryStage.setTitle("JavaFX - Grafy");
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(500);
        primaryStage.setWidth(800);
        primaryStage.setHeight(500);
        primaryStage.getIcons().add(new Image("Resources/icon.png"));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
