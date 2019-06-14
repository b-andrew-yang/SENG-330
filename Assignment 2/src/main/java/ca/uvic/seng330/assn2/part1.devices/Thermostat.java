package ca.uvic.seng330.assn2.part1.devices;
import ca.uvic.seng330.assn2.part1.*;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class Thermostat implements Device 
{
	private Status st;
	private final UUID id;
	private Temperature temp;
	private Hub hub;

	/**
	*	Constructor for Thermostat.
	*/
	public Thermostat(){
		st = Status.NORMAL;
		id = new UUID(4, 8);
		//temp = new Temperature(0);
	}

	public Thermostat(Hub h){
		hub = h;
		st = Status.NORMAL;
		id = new UUID(4, 8);
		//temp = new Temperature(0);
	}

	/*public Thermostat(Temperature temperature){
		st = Status.ON;
		id = new UUID(4, 8);
		temp = temperature;
	}*/

	/**
	*	setTemp sets the temperature.
	*	@param 	temp the temperature you want to set.
	*/
	public void setTemp(Temperature temperature)
	{

		temp = temperature;
	}

	/**
	*	getStatus returns status.
	*	@return returns the current status.
	*/
	public Status getStatus()
	{
		return st;
	}

	/**
	*	getIdentifier returns the Thermostat id.
	*	@return returns the id.
	*/
	public UUID getIdentifier()
	{
		return id;
	}
}