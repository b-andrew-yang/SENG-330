package ca.uvic.seng330.assn3;

import java.util.HashMap;
import java.util.UUID;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONObject;
*/

/**
 * Acts as the hub for all device and client interaction
 */
public class Hub {

  //private Logger logger = LoggerFactory.getLogger(Hub.class);
  private HashMap<UUID, Device> deviceList;
  private HashMap<UUID, Client> clientList;
  private boolean isOn;

  public Hub() {
    deviceList = new HashMap<UUID, Device>(0);
    clientList = new HashMap<UUID, Client>(0);
    startup();
  }

  /*public Logger log() {
    return this.logger;
  }*/

  /**
   * Stores a particular message from a device in the log
   *
   * @param alertText Message from device
   * @param d The device.
   */
  /*public void alert(String alertText, Device d) {
    // Log with slf4j
    if (d.getStatus() == Status.NORMAL) {
      logger.info(alertText, d);
    } else if (d.getStatus() == Status.ALERT) {
      logger.warn(alertText, d);
    } else if (d.getStatus() == Status.ERROR) {
      logger.error(alertText, d);
    }

    // Send the notification to all the clients.
    for (Client c : clientList.values()) {
      JSONObject messageToSend = new JSONMessaging(d, alertText).invoke();
      c.notify(messageToSend);
    }
  }*/

  /**
   * Registers a new device with the hub.
   *
   * @param newDevice THe new device to add
   * @throws HubRegistrationException If the device is already on the list.
   */
  public void register(Device newDevice) throws HubRegistrationException {
    if (deviceList.containsKey(newDevice.getIdentifier()) || newDevice == null) {
      throw new HubRegistrationException();
    } else {
      deviceList.put(newDevice.getIdentifier(), newDevice);
    }
  }

  /**
   * Registers a new client with the hub
   *
   * @param newClient The new client to add
   * @throws HubRegistrationException If the client is already on the list
   */
  public void register(Client newClient) throws HubRegistrationException {
    if (clientList.containsKey(newClient.getIdentifier()) || newClient == null) {
      throw new HubRegistrationException();
    } else {
      clientList.put(newClient.getIdentifier(), newClient);
    }
  }

  public void unregister(Device removeDevice) throws HubRegistrationException {
    if (deviceList.containsKey(removeDevice.getIdentifier())) {
      deviceList.remove(removeDevice.getIdentifier());
    } else {
      throw new HubRegistrationException();
    }
  }

  public void unregister(Client removeClient) throws HubRegistrationException {
    if (clientList.containsKey(removeClient.getIdentifier())) {
      clientList.remove(removeClient.getIdentifier());
    } else {
      throw new HubRegistrationException();
    }
  }

  /**
   * Gets the currently connected devices;
   *
   * @return The currently connected devices and their corresponding UUIDs.
   */
  public HashMap<UUID, Device> getDevices() {

    HashMap<UUID, Device> newList;

    newList = deviceList;

    return newList;
  }

  public void startup() {
    this.isOn = true;
  }

  public void shutdown() {
    this.isOn = false;
  }
}
