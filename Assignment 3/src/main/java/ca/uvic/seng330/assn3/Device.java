package ca.uvic.seng330.assn3;

import java.util.UUID;

public interface Device {

  public Status getStatus();

  public void setStatus(Status s);

  public UUID getIdentifier();
}
