package ca.uvic.seng330.assn2.part1.devices;
import ca.uvic.seng330.assn2.part1.*;

import java.util.UUID;

public interface Device{
	Status getStatus();
	UUID getIdentifier();
}