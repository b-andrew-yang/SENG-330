package ca.uvic.seng330.assn3;

import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class UserModelTest {


  @Test
  public void testAddUser1() throws ParseException {
    UserModel um = new UserModel();
    assertEquals(um.addUser("Jack", "hello", "Admin"), true);
  }

  @Test
  public void testAddUser2() throws ParseException {
    UserModel um = new UserModel();
    um.addUser("Jack", "hello", "Admin");
    um.addUser("Jim", "goodbye", "Admin");
    assertEquals(um.getUserList().get("Jim"), "Admin");
  }

  @Test
  public void testRemoveUser1() throws ParseException, IOException {
    UserModel um = new UserModel();
    um.addUser("Jack", "hello", "Admin");
    um.addUser("Jimmy", "goodbye", "Admin");

    um.removeUser("Jack");
    System.out.println(um.getUserList().size());

    assertEquals(um.getUserList().size(), 1, 0);


  }

  @Test
  public void testRemoveUser2() throws ParseException, IOException {
    UserModel um = new UserModel();
    um.addUser("Jack", "hello", "Admin");
    um.addUser("Jimmy", "goodbye", "Admin");
    um.addUser("Jimbob", "hello", "Admin");
    um.addUser("Josiah", "goodbye", "Admin");

    um.removeUser("Josiah");

    assertEquals(um.getUserList().size(), 3, 0);


  }

  @Test
  public void testCheckAdmin1() throws ParseException {

    UserModel um = new UserModel();
    um.addUser("Jack", "hello", "Admin");

    assertEquals(um.checkAdmin("Jack", "hello"), true);

  }

  @Test
  public void testCheckAdmin2() throws ParseException {

    UserModel um = new UserModel();
    um.addUser("Jack", "hello", "User");

    assertEquals(um.checkAdmin("Jack", "goodbye"), false);

  }

  @Test
  public void testCheckUserAndPassword1() throws ParseException {

    UserModel um = new UserModel();

    um.addUser("Johnny", "rdr2", "User");

    assertEquals(um.checkUserAndPassword("Johnny", "rdr2"), true);


  }


  @Test
  public void testCheckUserAndPassword12() throws ParseException {

    UserModel um = new UserModel();

    um.addUser("Johnny", "rdr2", "User");

    assertEquals(um.checkUserAndPassword("Johnny", ""), false);


  }
}