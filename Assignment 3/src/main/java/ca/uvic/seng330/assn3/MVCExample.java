package ca.uvic.seng330.assn3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
/*
 * Code sample from https://stackoverflow.com/questions/36868391/using-javafx-controller-without-fxml/36873768
 */
public class MVCExample extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception{
    AdditionModel model = new AdditionModel();
    AdditionController controller = new AdditionController(model);
    AdditionView view = new AdditionView(controller, model);

    Parent root = FXMLLoader.load(getClass().getResource("Hub.fxml"));
    primaryStage.setTitle("Hub");

    Scene scene = new Scene(view.asParent(), 800, 400);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
