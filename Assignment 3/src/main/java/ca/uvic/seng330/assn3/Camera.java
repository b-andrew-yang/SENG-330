package ca.uvic.seng330.assn3;

import java.util.UUID;


public class Camera implements Device {

  private final int MAX_DISK_SIZE = 100;
  private boolean isRecording;
  private boolean isStreaming;
  private int diskSize;
  private int memoryUsed;
  private UUID identifier;
  private Hub hub;
  private Status currentStatus;

  /**
   * Camera constructor
   *
   * @param h The hub the camera is connecting to.
   */
  public Camera(Hub h) {
    this.isRecording = false;
    this.isStreaming = false;
    this.diskSize = 100;
    this.memoryUsed = 0;
    identifier = UUID.randomUUID();
    hub = h;
    try {
      hub.register(this);
    } catch (HubRegistrationException e) {
      System.out.println("This camera is likely already registered on the specified hub.");
    }
    this.currentStatus = Status.NORMAL;
  }

  public Camera() {
    this.isRecording = false;
    identifier = UUID.randomUUID();
  }

  /**
   * Begin recording on the camera
   *
   * @throws CameraFullException If the disk size exceeds Max disk size.
   */
  public void record() throws CameraFullException {
    if (this.diskSize >= MAX_DISK_SIZE) {
      throw new CameraFullException();
    } else {
      if (isRecording) {
        this.isRecording = false;
      } else {
        this.isRecording = true;
      }
    }
  }

  public boolean getIsRecording() {
    return this.isRecording;
  }

  public Status getStatus() {
    return this.currentStatus;
  }

  public void startStream() {
    this.isStreaming = true;
  }

  public void stopStream() {
    this.isStreaming = false;
  }

  public boolean isStreaming() {
    return this.isStreaming;
  }
  public void setStatus(Status s) {
    this.currentStatus = s;
  }

  @Override
  public UUID getIdentifier() {
    return this.identifier;
  }
}
