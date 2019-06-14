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


public class CameraTest extends ApplicationTest {

  private Scene scene;

  @Override
  public void start(Stage primaryStage) throws IOException {
    Parent hubView;
    try {
      hubView = FXMLLoader.load(getClass().getResource("CameraView.fxml"));
    } catch (Exception e) {
      hubView = FXMLLoader.load(getClass().getResource("/CameraView.fxml"));
    }
    scene = new Scene(hubView);

    primaryStage.setScene(scene);
    primaryStage.show();
  }



  @Test
  public void startStopButtonsWork() {
    //expect:
    clickOn("Record");
    clickOn("Stop Recording");

    WaitForAsyncUtils.waitForFxEvents();
    verifyThat("#isRecordingLabel", LabeledMatchers.hasText("No"));
  }

  @Test
  public void containsStartRecordButton() {
    verifyThat("Record", LabeledMatchers.hasText("Record"));
  }

  @Test
  public void containsStreamingButton() {
    verifyThat("Streaming", LabeledMatchers.hasText("Streaming"));
  }

  @Test
  public void streamCameraTest1() {
    Camera c = new Camera();

    c.startStream();

    assertEquals(c.isStreaming(), true);
  }

  @Test
  public void streamCameraTest2() {
    Camera c = new Camera();

    c.startStream();

    c.stopStream();
    assertEquals(c.isStreaming(), false);
  }


}