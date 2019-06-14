package ca.uvic.seng330.assn3;

import ca.uvic.seng330.assn3.Temperature.TemperatureOutofBoundsException;
import java.util.HashMap;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class DeviceModelTest {


  @Test
  public void testAddDevice1() throws ParseException, TemperatureOutofBoundsException {
    DeviceModel dm = new DeviceModel();
    dm.addDevice(new Thermostat(), "Thermo", "Thermostat");
    assertEquals(dm.getDevList().size(), 1);
  }

  @Test
  public void testAddDevice2() throws ParseException, TemperatureOutofBoundsException {
    DeviceModel dm = new DeviceModel();
    dm.addDevice(new Thermostat(), "Thermo", "Thermostat");
    dm.addDevice(new SmartPlug(), "Smart", "Smartplug");
    dm.addDevice(new Camera(), "Cam", "Camera");

    assertEquals(dm.getDevList().size(), 3);
  }

  @Test
  public void testRemoveDevice1() throws ParseException, TemperatureOutofBoundsException {
    DeviceModel dm = new DeviceModel();
    dm.addDevice(new Thermostat(), "Thermo", "Thermostat");
    dm.addDevice(new SmartPlug(), "Smart", "Smartplug");
    dm.addDevice(new Camera(), "Cam", "Camera");

    dm.removeDevice("Cam");

    assertEquals(dm.getDevList().size(), 2);


  }

  @Test
  public void testRemoveDevice2() throws ParseException, TemperatureOutofBoundsException{
    DeviceModel dm = new DeviceModel();
    dm.addDevice(new Thermostat(), "Thermo", "Thermostat");
    dm.addDevice(new SmartPlug(), "Smart", "Smartplug");
    dm.addDevice(new Camera(), "Cam", "Camera");

    dm.removeDevice("Thermo");
    dm.removeDevice("Smart");

    assertEquals(dm.getDevList().get("Smart"), null);


  }

  @Test
  public void testGetDeviceList() throws ParseException {

    DeviceModel dm = new DeviceModel();
    dm.addDevice(new Camera(), "Cam", "Camera");

    HashMap<String, String> newHM = dm.getDevList();

    System.out.println(newHM.size());

    assertEquals(newHM.size(), 1);

  }
}