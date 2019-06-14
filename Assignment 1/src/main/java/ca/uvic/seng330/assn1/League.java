package ca.uvic.seng330.assn1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class League implements Iterable<Team> {

	private List<Team> League;
	private final String name;

	public League(String name){
		this.name = name;
		League = new ArrayList<Team>();
	}

	public void addTeam(Team a){
		League.add(a);
	}

	public List<Team> sort(){
		Collections.sort(League);
		return League;
	}

	public List<Team> getTeams(){
		List<Team> teamList = new ArrayList<Team>(League);
		return teamList;
	}

	@Override
	public Iterator<Team> iterator() {
		for( Team team : League ){
			System.out.print(team);
		}
		return League.iterator();
	}
	
}
