package ca.uvic.seng330.assn3;

import ca.uvic.seng330.assn3.Temperature.TemperatureOutofBoundsException;
import ca.uvic.seng330.assn3.Temperature.Unit;
import java.util.UUID;

public class Thermostat implements Device {
  private Status currentStatus;
  private Temperature temperature;
  private UUID identifier;
  private Hub hub;

  public Thermostat(Hub h) {
    this.temperature = new Temperature(20);
    hub = h;
    try {
      hub.register(this);
    } catch (HubRegistrationException e) {

    }
  }

  public Thermostat(Temperature t) throws TemperatureOutofBoundsException{
    this.temperature = new Temperature(t.getTemperature(), t.getUnit());
    identifier = UUID.randomUUID();
  }

  public Thermostat() throws TemperatureOutofBoundsException {
    this.temperature = new Temperature(20, Unit.CELSIUS);
    identifier = UUID.randomUUID();
  }

  public void setStatus(Status s) {
    this.currentStatus = s;
  }
  /** @return The status of the thermostat */
  @Override
  public Status getStatus() {
    // TODO Auto-generated method stub
    return this.currentStatus;
  }

  /**
   * Gets the unique identifier of the thermostat
   *
   * @return The unique identifier of the thermostat
   */
  @Override
  public UUID getIdentifier() {
    return identifier;
  }

  /**
   * Sets the thermostats temperature.
   *
   * @param temperature The temperature you wish to set the thermostat
   */
  public void setTemp(Temperature temperature) throws TemperatureOutofBoundsException{
    Temperature t = new Temperature(temperature.getTemperature(), temperature.getUnit());
    this.temperature = t;
  }

  public Temperature  getTemperature() {
    return this.temperature;
  }
}
