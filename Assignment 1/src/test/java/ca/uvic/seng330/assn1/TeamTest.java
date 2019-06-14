package ca.uvic.seng330.assn1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

/**
 * Team testing. Part 1.
 * 
 * @author Andreas Koenzen <akoenzen@uvic.ca>
 */
public class TeamTest {
  
   /**
   * Test #01. Test for presence of class Player.
   */
  @Test
  public void testPlayerExists() {
    Player p = new Player("José", 100, Position.GOALTENDER);
    assertNotNull(p);
  }

  /**
   * Test #02. Test correctness of Player's getName() method.
   */
  @Test
  public void testPlayerName()   {
    Player player = new Player("José", 100, Position.GOALTENDER);

    // Check the name.
    String name = player.getName();
    assertTrue("The name field is empty!", name.length() > 0);
  }

  /**
   * Test #03. Test correctness of Player's getPoints() method.
   */
  @Test
  public void testPlayerPoints() {
    Player player = new Player("José", 100, Position.GOALTENDER);

    // Check the points.
    assertNotNull("The method returns NULL!", player.getPoints());
  }
  
  /** 
   * Test 4. Test League 
   */
  @Test
  public void testLeagueExists() {
	  League l = new League("NHL");
	  assertNotNull(l);
  }
  
  /** 
   * Test 4. Test League 
   */
  @Test
  public void testLeagueHoldsTeams() {
	  League l = new League("NHL");
	  Team team = new Team("Canucks");
	  l.addTeam(team);
	  assertEquals(l.iterator().next().getPoints(), 0);
  }
  
  /** 
   * Test 4. Test League 
   */
  @Test
  public void testLeagueSorts() {
	  League l = new League("NHL");
	  Team team = new Team("Canucks");
	  l.addTeam(team);
	  Team team2 = new Team("Penguins");
	  l.addTeam(team2);
	  team.addPlayer(new Player("Bure",44,Position.WINGER));
	  team2.addPlayer(new Player("Crosby GOAT", 87, Position.CENTRE));
	  List<Team> sortedTeams = l.sort();
	  assertEquals(sortedTeams.get(0).getPoints(), 44);
  }
  /**  Insert more tests here as needed
   * 
   */
}
