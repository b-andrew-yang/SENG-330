package ca.uvic.seng330.assn2.part1;
import ca.uvic.seng330.assn2.part1.devices.*;

import java.util.UUID;
import java.util.HashMap;

public interface Mediator{
	public void log(String message);
	public void alert(String message);
	public void alert(Device d, String message);
	public HashMap<UUID, Client> getClients();
	public HashMap<UUID, Device> getDevices();
	public Status getStatus();
	public UUID getIdentifier();
}