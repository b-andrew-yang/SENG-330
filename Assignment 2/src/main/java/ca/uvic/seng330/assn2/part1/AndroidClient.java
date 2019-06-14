package ca.uvic.seng330.assn2.part1;
import ca.uvic.seng330.assn2.part1.devices.*;

import org.json.JSONObject;
import java.util.UUID;

public class AndroidClient implements Client{
	private Device monitor;
	private final UUID id;
	private Status st;
	private Mediator mediator;

	public AndroidClient(Mediator m){
		id = new UUID(4, 8);
		st = Status.NORMAL;
		mediator = m;
	}

	public AndroidClient(Device d, Mediator m){
		id = new UUID(4, 8);
		st = Status.NORMAL;
		monitor = d;
		mediator = m;
	}

	public void setMonitor(Device d){
		monitor = d;
	}

	public void notify(org.json.JSONObject pMsg){
		System.out.println(pMsg.toString());
	}

	public UUID getIdentifier(){
		return id;
	}

	public Status getStatus(){
		return st;
	}
}