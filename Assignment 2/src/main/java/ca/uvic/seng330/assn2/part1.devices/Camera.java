package ca.uvic.seng330.assn2.part1.devices;
import ca.uvic.seng330.assn2.part1.*;


import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class Camera implements Device 
{
	private Status st;
	private final UUID id;
	private boolean isRecording;
	private int diskSize;
	private Hub hub;

	public Camera()
	{
		st = Status.NORMAL;
		id = new UUID(4, 8);
		diskSize = 0;
		isRecording = false;
	}

	public Camera(Hub h){
		hub = h;
		st = Status.NORMAL;
		id = new UUID(4, 8);
		diskSize = 0;
		isRecording = false;
	}

	public void record() throws CameraFullException
	{
		/*
			If recording, stop. If not recording, start.
			Throw exception if camera is full.
		*/
		if(isRecording){
			isRecording = false;
		}else if(diskSize >= 100){
			throw new CameraFullException("No disk space on Camera");
		}else{
			diskSize++;
			isRecording = true;
		}
	}

	public boolean isRecording(){
		return isRecording;
	}

	public void setDiskSize(int size)
	{
		diskSize = size;
	}

	public Status getStatus()
	{
		return st;
	}

	public void setStatus(Status s){
		st = s;
	}

	public UUID getIdentifier()
	{
		return id;
	}
}
