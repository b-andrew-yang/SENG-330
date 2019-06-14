package ca.uvic.seng330.assn2.part1;
import ca.uvic.seng330.assn2.part1.devices.*;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.LinkedList;

public class Hub implements Mediator {
	private Status st;
	private final UUID id;
	private HashMap<UUID, Device> devReg;
	private HashMap<UUID, Client> clientReg;
	private String alertMsg;
	private Logger logger = LoggerFactory.getLogger(Hub.class);

	public enum LogLevel{
		WARN,
		INFO,
		ERROR,
		DEBUG
	}

	public Hub(){
		st = Status.ON;
		id = new UUID(4, 8);
		devReg = new HashMap<UUID, Device>();
		clientReg = new HashMap<UUID, Client>();
	}

	@Override
	public void log(String message){
		logger.info(message);
	}

	@Override
	public void alert(String message){
		alertMsg = message;
		log(alertMsg);
	}

	@Override
	/*
	*	alert iterates through all the clients and sends messages
	*/
	public void alert(Device d, String message){
		JSONMessaging jsonMsg = new JSONMessaging(d, message);
		JSONObject alert = jsonMsg.invoke();

		for(Map.Entry<UUID, Client> entry: clientReg.entrySet()){
			Client c = entry.getValue();
			c.notify(alert);
		}
	}

	@Override
	public HashMap<UUID, Device> getDevices(){
		return devReg;
	}

	@Override
	public HashMap<UUID, Client> getClients(){
		return clientReg;
	}

	public void register(Device d) throws HubRegistrationException{
		if(devReg.get(d.getIdentifier()) != null){
			throw new HubRegistrationException("Device already registered");
		}else{
			devReg.put(d.getIdentifier(), d);
		}
	}

	public void unregister(Device d) throws HubRegistrationException{
		if(devReg.get(d.getIdentifier()) == null){
			throw new HubRegistrationException("Device not registered");
		}else{
			devReg.remove(d.getIdentifier());
		}
	}

	public void register(Client c) throws HubRegistrationException{
		if(clientReg.get(c.getIdentifier()) != null){
			throw new HubRegistrationException("Client already registered");
		}else{
			clientReg.put(c.getIdentifier(), c);
		}
	}

	public void unregister(Client c) throws HubRegistrationException{
		if(clientReg.get(c.getIdentifier()) == null){
			throw new HubRegistrationException("Client not registered");
		}else{
			clientReg.remove(c.getIdentifier());
		}
	}

	public void startup(){
		st = Status.ON;
	}

	public void shutdown(){
		try{		
			for(Map.Entry<UUID, Client> entry: clientReg.entrySet()){
				unregister(entry.getValue());
			}
			for(Map.Entry<UUID, Device> entry: devReg.entrySet()){
				unregister(entry.getValue());
			}
		}catch(HubRegistrationException e){
			System.out.println("Cannot unregister");
		}

		st = Status.OFF;
	}

	@Override
	public Status getStatus(){
		return st;
	}

	@Override
	public UUID getIdentifier(){
		return id;
	}
}
