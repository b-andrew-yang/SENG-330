package ca.uvic.seng330.assn3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class DesktopApplication extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
    //Parent root = FXMLLoader.load(getClass().getResource("/HubHomeView.fxml"));
    primaryStage.setTitle("Login");

    primaryStage.setScene(new Scene(root, 488, 455));
    primaryStage.show();
  }
}
