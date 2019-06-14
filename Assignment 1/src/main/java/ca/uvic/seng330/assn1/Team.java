package ca.uvic.seng330.assn1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * a Team holds a List of Players and Managers.
 * @author Karan Tongay (karantongay@uvic.ca)
 * @author Andreas Koenzen 
 *
 */
public final class Team implements Comparable<Team> {

	List<Player> Team;
	private final String name;

	public Team(String name){
		this.name = name;
		Team = new ArrayList<Player>();
	}

	public void addPlayer(Player p){
		Team.add(p);
	}

	public List<Player> sort(){
		Collections.sort(Team, new PlayerComparator());
		return Team;
	}

	public int getPoints(){
		if(Team.isEmpty()){
			return 0;
		}
		int teamCounter = 0;
		for(Player p: Team){
			teamCounter += p.getPoints();
		}
		return teamCounter;
	}

	public Boolean removePlayer(String name){
		assert !Team.isEmpty();

		for(Player p: Team){
			if(p.getName() == name){
				Team.remove(p);
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append(name);
		s.append(" have ");
		String points = Integer.toString(getPoints());
		s.append(points);
		s.append(" points");
		return s.toString();
	}

	@Override
	public int compareTo(Team o) {
		// TODO Auto-generated method stub
		return (this.getPoints() - o.getPoints());
	}



	
}
