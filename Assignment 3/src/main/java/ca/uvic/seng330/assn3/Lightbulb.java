package ca.uvic.seng330.assn3;

import java.util.UUID;

public class Lightbulb implements Device {

  private boolean isOn;
  private Status currentStatus;
  private UUID identifier;
  private Hub hub;


  public Lightbulb(Hub h) {
    this.isOn = false;
    hub = h;
    try {
      hub.register(this);
    } catch (HubRegistrationException e) {

    }
    this.identifier = UUID.randomUUID();
  }

  public Lightbulb() {
    this.isOn = false;
    this.identifier = UUID.randomUUID();
  }

  public void setStatus(Status s) {
    this.currentStatus = s;
  }

  @Override
  public Status getStatus() {
    return this.currentStatus;
  }

  public boolean getCondition() {
    return this.isOn;
  }

  @Override
  public UUID getIdentifier() {
    return this.identifier;
  }

  /**
   * Toggle the lightswitch on or off.
   */
  public void toggle() {
    if (isOn) {
      this.isOn = false;
    } else {
      this.isOn = true;
    }
  }
}
