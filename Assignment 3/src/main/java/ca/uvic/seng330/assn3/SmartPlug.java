package ca.uvic.seng330.assn3;

import java.util.UUID;

public class SmartPlug implements Device {

  private boolean isOn;
  private Status currentStatus;
  private UUID identifier;
  private Hub hub;

  public SmartPlug(Hub h) {
    this.isOn = false;
    identifier = UUID.randomUUID();
    hub = h;
    try {
      hub.register(this);
    } catch (HubRegistrationException e) {

    }
  }

  public SmartPlug() {
    this.isOn = false;
    identifier = UUID.randomUUID();
  }

  public void setStatus(Status s) {
    this.currentStatus = s;
  }

  @Override
  public Status getStatus() {
    return currentStatus;
  }

  @Override
  public UUID getIdentifier() {
    return identifier;
  }

  public void toggle() {
    if (isOn) {
      isOn = false;
    } else {
      isOn = true;
    }
  }

  public boolean getIsOn() {
    return isOn;
  }
}
