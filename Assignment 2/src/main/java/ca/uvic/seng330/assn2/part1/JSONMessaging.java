package ca.uvic.seng330.assn2.part1;
import ca.uvic.seng330.assn2.part1.devices.*;

import org.json.JSONObject;
import java.util.Date;
import java.text.SimpleDateFormat;

public class JSONMessaging{
	private static int msg_id;
	private final Device device;
	private String message;

	public JSONMessaging(Device d, String message){
		device = d;
		this.message = message;
	}

	public JSONObject invoke(){
		JSONObject jsonObj = new JSONObject();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-ddTHH:mm:ssZ");

		jsonObj.put("msg_id", msg_id);
		jsonObj.put("node_id", device.getIdentifier());
		jsonObj.put("status", device.getStatus());
		jsonObj.put("payload", message);
		jsonObj.put("created at", format.format(new Date()));

		msg_id++;
		return jsonObj;
	}
}