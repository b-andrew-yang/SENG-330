package ca.uvic.seng330.assn2.part1.devices;
import ca.uvic.seng330.assn2.part1.*;

public class Temperature
{
	private double temperature;
	private Unit unit;

	public class TemperatureOutofBoundsException extends Exception{
		public TemperatureOutofBoundsException(String message){
			super(message);
		}
	}

	public enum Unit{
		CELSIUS,
		FAHRENHEIT
	}

	/**
	*	Constructor for Temperature class.
	*	@param s desired temperature setting.
	*/

	public Temperature(double temp, Unit unit) throws TemperatureOutofBoundsException{
		if(temp > 1000){
			throw new TemperatureOutofBoundsException("Absurd Temperature");
		}else{
			temperature = temp;
		}
		this.unit = unit;
	}

	public Temperature(int temp) throws TemperatureOutofBoundsException{
		if(temp > 1000){
			throw new TemperatureOutofBoundsException("Absurd Temperature");
		}else{
			temperature = (double)temp;
		}
	}

	public double getTemperature(){
		return temperature;
	}

	public void setTemperature(double temp, Unit unit) throws TemperatureOutofBoundsException{
		if(temp > 1000){
			throw new TemperatureOutofBoundsException("Absurd Temperature");
		}else{
			temperature = temp;
			this.unit = unit;
		}
	}
}