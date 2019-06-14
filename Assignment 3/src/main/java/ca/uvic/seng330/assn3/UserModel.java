package ca.uvic.seng330.assn3;

import java.util.HashMap;
import java.util.Set;
import java.util.*;
import java.io.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileReader;
import com.google.gson.Gson;
import org.json.simple.parser.ParseException;


public class UserModel {

  private HashMap<String, String> userID;
  private HashMap<String, String> accessLevel;

  public UserModel() {
    userID = new HashMap<String, String>();
    accessLevel = new HashMap<String, String>();
    updateMap();
  }

  /*
    userID stores, <userID, password>
    accessLevel stores, <userID, access level>
    returns true if successfully added, returns false if not.
  */
  public boolean addUser(String newUserID, String password, String access) throws ParseException {

    if (userIDValid(newUserID)) {
      // Update HashMaps with our .txt file containing usernames, passwords and access levels.
      updateMap();

      // Add newly registered data to the hashmap
      userID.put(newUserID, password);
      accessLevel.put(newUserID, access);

      writeToJSON();

      return true;
    } else {
      return false;
    }
  }

  public boolean removeUser(String removeID) throws IOException{

    if(!userIDValid(removeID)){
      updateMap();

      userID.remove(removeID);
      accessLevel.remove(removeID);

      writeToJSON();

      return true;
    }else{
      return false;
    }
  }

  private void writeToJSON(){
      JSONObject userData = new JSONObject(userID);
      JSONObject accessData = new JSONObject(accessLevel);

      try{
        FileWriter userFile = new FileWriter("UserData.txt");
        FileWriter accessFile = new FileWriter("AccessData.txt");

        userFile.write(userData.toJSONString());
        accessFile.write(accessData.toJSONString());

        userFile.flush();
        accessFile.flush();
        userFile.close();
        accessFile.close();
      }catch(IOException e){
        e.printStackTrace();
      }
  }

  public boolean checkAdminAccess(){
    boolean adminAccess = false;;

    try{
      File file = new File("CurrentAccessLevel.txt");

      Scanner sc = new Scanner(file);
      String currAccess = sc.nextLine();
      if(currAccess.equals("Admin")){
        adminAccess = true;
      }
    }catch(IOException e){
      System.out.println("checkCurrentAccess error");
    }

    return adminAccess;
  }

  public void logCurrentAccess(String currAccess){
    try{
      FileWriter userFile = new FileWriter("CurrentAccessLevel.txt");

      userFile.write(currAccess);
      userFile.flush();
      userFile.close();
    }catch(IOException e){
      System.out.println("LogAccessLevel error");
    }
  }

  private void updateMap(){
    /*
      Reads usernames, password and access level from UserData.txt and
      AccessData.txt and the model's hashmap with current values.
    */

    try{
      userID = JSONtoMap("UserData.txt");
      accessLevel = JSONtoMap("AccessData.txt");

    }catch(ParseException e){
      e.printStackTrace();
    }
  }

  public HashMap<String, String> getUserList(){
    /*
      Returns a copy of HashMap accessLevel.
    */

    HashMap<String, String> copyAccessLevel = new HashMap<String, String>();

    copyAccessLevel.putAll(accessLevel);
    return copyAccessLevel;
  }

  private boolean userIDValid(String newUserID) {
    return !userID.containsKey(newUserID);
  }

  public boolean checkUserAndPassword(String checkUser, String password) throws ParseException {
        /*
            returns true if userID and password match.
         */

    updateMap();

    if (userID.containsKey(checkUser)) {
      String setPassword = userID.get(checkUser);
      if (setPassword.equals(password)) {
        return true;
      }
    }
    return false;
  }

  public boolean checkAdmin(String checkUser, String password) throws ParseException {
        /*
            Returns true if Admin and false if not.
         */

    if (checkUserAndPassword(checkUser, password)) {
      if (accessLevel.get(checkUser).equals("Admin")) {
        return true;
      }
    }
    return false;
  }

  private HashMap<String, String> JSONtoMap(String file) throws ParseException {
    JSONParser parser = new JSONParser();

    try {
      FileReader fileReader = new FileReader(file);
      JSONObject json = (JSONObject) parser.parse(fileReader);

      Gson gson = new Gson();
      HashMap<String, String> map = new HashMap<String, String>();
      map = (HashMap<String, String>) gson.fromJson(json.toString(), map.getClass());
      return map;

    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }
}
