package ca.uvic.seng330.assn3;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.matcher.control.TextInputControlMatchers;

import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;



public class HubTest extends ApplicationTest {

  private Scene scene;

  @Override
  public void start(Stage primaryStage) throws IOException {
    Parent hubView;
    try {
       hubView = FXMLLoader.load(getClass().getResource("HubHomeView.fxml"));
    } catch (Exception e) {
      hubView = FXMLLoader.load(getClass().getResource("/HubHomeView.fxml"));
    }
    scene = new Scene(hubView);

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  @Test public void containsTitleLabel() {
    // expect:
    verifyThat("HUB HOME", LabeledMatchers.hasText("HUB HOME"));

  }

  @Test public void containsExitButton() {
    //expect:

    verifyThat("EXIT", LabeledMatchers.hasText("EXIT"));
  }

  @Test public void containsThermostatButton() {
    //expect:
    Button button = from(scene.getRoot()).lookup(".button").query();
    assertEquals("Thermostat", button.getText());
  }

  @Test public void containsCameraButton() {
    //expect:
    Button button = from(scene.getRoot()).lookup("Camera").query();
    assertEquals("Camera", button.getText());
  }

  @Test public void containsSmartplugButton() {
    //expect:
    Button button = from(scene.getRoot()).lookup("SmartPlug").query();
    assertEquals("SmartPlug", button.getText());
  }

  @Test public void containsLightButton() {
    //expect:
    Button button = from(scene.getRoot()).lookup("Lightbulb").query();
    assertEquals("Lightbulb", button.getText());
  }
}