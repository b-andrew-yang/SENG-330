package ca.uvic.seng330.assn3;

public class Temperature {

  double temperature;
  private Unit unit;

  public enum Unit {
    CELSIUS,
    FARENHEIT
  }

  public class TemperatureOutofBoundsException extends Exception {

    public TemperatureOutofBoundsException() {
      System.out.println("Temperature is out of bounds!");
    }
  }

  /**
   * @param temperature The temperature
   * @param unit The unit to use Celsius or farenheit
   * @throws TemperatureOutofBoundsException If the temperature is greater than 1000.
   */
  public Temperature(double temperature, Unit unit) throws TemperatureOutofBoundsException {
    this.unit = unit;
    if (temperature > 1000) {
      throw new TemperatureOutofBoundsException();
    }
    this.temperature = temperature;
  }

  public Temperature(double temperature) {
    this.temperature = temperature;
  }

  public Temperature(int temperature) {
    this.temperature = (double) temperature;
  }

  public Unit getUnit() {
    return this.unit;
  }

  public double getTemperature() {
    return this.temperature;
  }

  /**
   * @param t The temperature object to set the temperature to.
   */
  public void setTemperature(Temperature t) {
    this.temperature = t.getTemperature();
    this.unit = t.getUnit();
  }
}
