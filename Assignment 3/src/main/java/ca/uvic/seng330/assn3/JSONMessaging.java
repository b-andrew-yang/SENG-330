package ca.uvic.seng330.assn3;

//import org.json.JSONObject;

import java.time.LocalDateTime;

public class JSONMessaging {

  private String message;
  //private JSONObject json;
  private Device device;
  private LocalDateTime t;
  private int message_id = 1;

  /**
   * @param d The device
   * @param s The string that needs to be sent
   */
  public JSONMessaging(Device d, String s) {
    //this.json = new JSONObject();
    this.device = d;
    this.message = s;
    this.t = LocalDateTime.now();
    message_id++;
  }

 /*
  public JSONObject invoke() {
    JSONObject j = new JSONObject();

    j.put("msg_id", message_id);
    j.put("node_id", device.getIdentifier());
    j.put("status", device.getStatus());
    j.put("payload", message);
    j.put("created_at", t.toString());

    return j;
  }*/
}
