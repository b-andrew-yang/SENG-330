/*
 * Test driver. Add more tests here as needed.
 */

package ca.uvic.seng330.assn1;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.Before;

/**
 * main tests for part 3.
 *
 * @author Karan Tongay (karantongay@uvic.ca)
 */
public class CompareTest {
	private Team team, team1, team2;
	private Player p1,p2;

	/**
	 * The @before annotation sets up some test scaffolding for *all* tests.
	 */
	@Before
	public void setUpData() {
		team = new Team("Los Tequileros Mexicanos");
		team2 = new Team("Python");
		team1 = new Team("Java");

		// Add players.
		p1 = new Player("José", 100, Position.GOALTENDER);
		p2 = new Player("Pedrito", 101, Position.DEFENDER);
		team.addPlayer(p1);
		team.addPlayer(p2);
		team.addPlayer(new Player("Juanito", 102, Position.DEFENDER));
		team.addPlayer(new Player("Pepe", 103, Position.CENTRE));
		team.addPlayer(new Player("Ricardo", 104, Position.WINGER));
	}

	/**
	 * Test for validating the overridden toString() method.
	 */
	@Test 
	public void validatesOutputOfToString() {

		String s = team.toString();
		assertTrue("Success",s.equals("Los Tequileros Mexicanos have 510 points"));
	}

	/**
	 * Test to check the behavior of Comparable class when the player scores are equal
	 */
	@Test 
	public void testsComparableWhenEqual() {

		int result = team1.compareTo(team2);
		assertTrue("expected to be equal", result == 0);
	}

	/**
	 * Test to check the behavior of Comparable class when the player scores are greater
	 */
	@Test 
	public void testsComparableWhenGreater() {

		Player p3 = new Player("John",93, Position.WINGER);
		Player p4 = new Player("Josiah",94, Position.DEFENDER);
		team1.addPlayer(p3); //100 + 93
		team2.addPlayer(p4); //101+94
		int result = team2.compareTo(team1);
		assertTrue("expected to be greater than", result == 1);
	}	
	
	/**
	 * Test to check sorting with comparator
	 */
	@Test
	public void testPlayerComparator() {
		Team compare = new Team("compare");
		Player c1 = new Player("Pedro", 50, Position.DEFENDER);
		Player c3 = new Player("Dilip", 15, Position.CENTRE);		
		Player c2 = new Player("Priya", 100, Position.GOALTENDER);
		compare.addPlayer(c1);
		compare.addPlayer(c2);
		compare.addPlayer(c3);
		List<Player> sortedPlayers = compare.sort();
		assertEquals(sortedPlayers.get(0).getPoints(), 15);
	}
}
