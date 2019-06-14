package ca.uvic.seng330.assn2.part1.devices;
import ca.uvic.seng330.assn2.part1.*;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class Lightbulb implements Device 
{
	private Status st;
	private final UUID id;
	private Hub hub;

	/**
	*	Constructor for Lightbulb.
	*/
	public Lightbulb()
	{
		st = Status.NORMAL;
		id = new UUID(4, 8);
	}

	public Lightbulb(Hub h){
		hub = h;
		st = Status.NORMAL;
		id = new UUID(4, 8);
	}

	public void toggle()
	{
		/*
			If off, on. If on, off. Normal is also on.
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

	public Status getStatus()
	{
		return st;
	}

	public boolean getCondition()
	{
		boolean condition = false;
		switch(st){
			case NORMAL:
				condition = true;
				break;

			case ON:
				condition = true;
				break;

			case OFF:
				condition = false;
				break;
		}
		return condition;
	}

	public UUID getIdentifier()
	{

		return id;
	}

}