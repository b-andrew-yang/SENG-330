package ca.uvic.seng330.assn2.part1;
import ca.uvic.seng330.assn2.part1.devices.*;

import org.json.JSONObject;
import java.util.UUID;

public interface Client{
	public void notify(org.json.JSONObject pMsg);
	// Camera will alert the hub and
	// the hub will notify all clients
	public UUID getIdentifier();
	public Status getStatus();
}