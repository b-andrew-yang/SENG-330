package ca.uvic.seng330.assn3;

import ca.uvic.seng330.assn3.Temperature.TemperatureOutofBoundsException;
import ca.uvic.seng330.assn3.Temperature.Unit;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;


public class MediaStreamTest extends ApplicationTest {

  private Scene scene;

  @Override
  public void start(Stage primaryStage) throws IOException {
    Parent hubView;
    try {
      hubView = FXMLLoader.load(getClass().getResource("CameraStreamingView.fxml"));
    } catch (Exception e) {
      hubView = FXMLLoader.load(getClass().getResource("/CameraStreamingView.fxml"));
    }
    scene = new Scene(hubView);

    primaryStage.setScene(scene);
    primaryStage.show();
  }



 /* @Test
  public void playButtonWorks() {
    //expect:
    clickOn("Play");

   
  }       */

  public void containsPlayButton() {
    verifyThat("Play", LabeledMatchers.hasText("Play"));
  }

  public void containsPauseButton() {
    verifyThat("Pause", LabeledMatchers.hasText("Pause"));
  }

  public void containsBackButton() {
    verifyThat("BACK", LabeledMatchers.hasText("BACK"));
  }

}