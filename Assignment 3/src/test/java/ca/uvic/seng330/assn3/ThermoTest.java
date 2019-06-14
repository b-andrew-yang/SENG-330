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


public class ThermoTest extends ApplicationTest {

  private Scene scene;

  @Override
  public void start(Stage primaryStage) throws IOException {
    Parent hubView;
    try {
      hubView = FXMLLoader.load(getClass().getResource("ThermostatControlView.fxml"));
    } catch (Exception e) {
      hubView = FXMLLoader.load(getClass().getResource("/ThermostatControlView.fxml"));
    }
    scene = new Scene(hubView);

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  @Test
  public void containsCurrentStatus() {
    // expect:
    verifyThat("Off", LabeledMatchers.hasText("Off"));

  }

  @Test
  public void containsBackButton() {
    //expect:

    verifyThat("BACK", LabeledMatchers.hasText("BACK"));
  }


  @Test
  public void temperatureConversionTest1() throws TemperatureOutofBoundsException {
    Thermostat t = new Thermostat(new Temperature(15, Unit.CELSIUS));
    ThermostatController tc = new ThermostatController(t);
    Temperature newTemp = tc.convertTemp(t.getTemperature());

    assertEquals(59, newTemp.getTemperature(), 2);

  }

  @Test
  public void temperatureConversionTest2() throws TemperatureOutofBoundsException {
    Thermostat t = new Thermostat(new Temperature(55, Unit.FARENHEIT));
    ThermostatController tc = new ThermostatController(t);
    Temperature newTemp = tc.convertTemp(t.getTemperature());

    assertEquals(12.7, newTemp.getTemperature(), 2);
  }

}