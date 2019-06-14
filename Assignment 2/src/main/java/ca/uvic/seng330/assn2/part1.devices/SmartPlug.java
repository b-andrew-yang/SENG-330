package ca.uvic.seng330.assn2.part1.devices;
import ca.uvic.seng330.assn2.part1.*;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class SmartPlug implements Device 
{
	private Status st;
	private final UUID id;
	private Hub hub;

	/**
	*	Constructor for SmartPlug.
	*/
	public SmartPlug()
	{
		st = Status.NORMAL;
		id = new UUID(4, 8);
	}

	public SmartPlug(Hub h){
		hub = h;
		st = Status.NORMAL;
		id = new UUID(4, 8);
	}

	/**
	*	Toggle toggles the SmartPlug on and off.
	*/
	public void toggle()
	{
		/*
			If off, on. If on, off. Normal is also on
		*/
		switch(st){
			case OFF:
				st = Status.ON;
				break;

			case ON:
				st = Status.OFF;
				break;
			case NORMAL:
				st = Status.OFF;
				break;
		}
	}

	/**
	*	getStatus returns the status of the SmartPlug.
	*	@return st which represents the current status.
	*/
	public Status getStatus()
	{
		return st;
	}

	public UUID getIdentifier()
	{
		return id;
	}
}