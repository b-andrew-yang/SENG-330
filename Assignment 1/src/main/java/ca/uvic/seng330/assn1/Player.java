package ca.uvic.seng330.assn1;

/**
 * A Player belongs to a team.
 *
 */
public final class Player {
	private Position position;
	private int points;
	private final String name;

	public Player(String name, int points, Position assigned){
		assert points >= 0;
		this.name = name;
		this.points = points;
		Position position = assigned;
	}

	public String getName(){
		return name;
	}

	public int getPoints(){
		return points;
	}

	public void setPoints(int points){
		assert points >= 0;
		this.points = points;
	}
}