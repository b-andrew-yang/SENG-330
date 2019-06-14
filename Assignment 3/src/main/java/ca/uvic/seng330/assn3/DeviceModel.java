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
import java.util.UUID;

public class DeviceModel{

	private HashMap<String, String> deviceList;
	private HashMap<String, String> typeList;
	private HashMap<String, String> deviceStatus;

	public DeviceModel(){
		deviceList = new HashMap<String, String>();
		typeList = new HashMap<String, String>();
		deviceStatus = new HashMap<String, String>();
		updateMap();
	}

	public void addDevice(Device d, String nickName, String deviceType) throws ParseException{
		updateMap();

		if(checkNickname(nickName)){
			String devID = d.getIdentifier().toString();
			deviceList.put(nickName, devID);
			//deviceList.put(nickName, deviceType);
			typeList.put(devID, deviceType);
			deviceStatus.put(nickName, "Off");

			JSONObject devListJSON = new JSONObject(deviceList);
			JSONObject typeListJSON = new JSONObject(typeList);
			JSONObject deviceStatusJSON = new JSONObject(deviceStatus);

			try{
				FileWriter devWriter = new FileWriter("DeviceList.txt");
				FileWriter typeWriter = new FileWriter("TypeList.txt");
				FileWriter statusWriter = new FileWriter("DeviceStatus.txt");

				devWriter.write(devListJSON.toJSONString());
				typeWriter.write(typeListJSON.toJSONString());
				statusWriter.write(deviceStatusJSON.toJSONString());
				
				devWriter.flush();
				typeWriter.flush();
				statusWriter.flush();
				devWriter.close();
				typeWriter.close();
				statusWriter.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}

	public void removeDevice(String nickName) throws ParseException{
		updateMap();

		if(deviceList.containsKey(nickName)){
			typeList.remove(deviceList.get(nickName));
			deviceStatus.remove(nickName);
			deviceList.remove(nickName);

			JSONObject devListJSON = new JSONObject(deviceList);
			JSONObject typeListJSON = new JSONObject(typeList);
			JSONObject deviceStatusJSON = new JSONObject(deviceStatus);

			try{
				FileWriter devWriter = new FileWriter("DeviceList.txt");
				FileWriter typeWriter = new FileWriter("TypeList.txt");
				FileWriter statusWriter = new FileWriter("DeviceStatus.txt");

				devWriter.write(devListJSON.toJSONString());
				typeWriter.write(typeListJSON.toJSONString());
				statusWriter.write(deviceStatusJSON.toJSONString());

				devWriter.flush();
				typeWriter.flush();
				statusWriter.flush();
				devWriter.close();
				typeWriter.close();
				statusWriter.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}

	public boolean checkNickname(String nickName){
		
	/*
		Returns true if nickname is valid to be used, or doesn't
		currently exist in the device list. Returns false if there
		is already a device that uses this nickname.
	*/	
		updateMap();
		return !deviceList.containsValue(nickName);
	}

	public void updateMap(){
		try{
			deviceList = JSONtoMap("DeviceList.txt");
			typeList = JSONtoMap("TypeList.txt");
			deviceStatus = JSONtoMap("DeviceStatus.txt");
		}catch(ParseException e){
			System.out.println("checkNickname error");

			//e.printStackTrace();
		}
	}

	public void updateStatus(String nickName, String newStatus) throws ParseException{
		updateMap();

		if(deviceList.containsKey(nickName)){
			deviceStatus.put(nickName, newStatus);
			JSONObject deviceStatusJSON = new JSONObject(deviceStatus);

			try{
				FileWriter statusWriter = new FileWriter("DeviceStatus.txt");

				statusWriter.write(deviceStatusJSON.toJSONString());

				statusWriter.flush();
				statusWriter.close();
			}catch(IOException e){
				System.out.println("Update status failed");
			}
		}
	}

	public HashMap<String, String> getDevList(){
		updateMap();

		HashMap<String, String> devListCopy = new HashMap<String, String>();

		devListCopy.putAll(deviceList);
		return devListCopy;
	}

	public HashMap<String, String> getTypeList(){
		updateMap();

		HashMap<String, String> typeListCopy = new HashMap<String, String>();

		typeListCopy.putAll(typeList);
		return typeListCopy;
	}

	public HashMap<String, String> getStatusList(){
		updateMap();

		HashMap<String, String> statusListCopy = new HashMap<String, String>();
		statusListCopy.putAll(deviceStatus);
		return statusListCopy;
	}

	public void shutDown() throws ParseException{
		Iterator it = deviceStatus.entrySet().iterator();

		while(it.hasNext()){
			Map.Entry pair = (Map.Entry)it.next();
			updateStatus((String) pair.getKey(), "Off");
			it.remove();
		}
	}

	public String getDeviceName(){
		String devName = "";

		try{
			File file = new File("SelectName.txt");
			Scanner sc = new Scanner(file);
			devName = sc.nextLine();
			sc.close();
		}catch(IOException e){
			System.out.println("Something failed getting device name");
		}

		return devName;
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
      System.out.println("JSONtoMap error");
      //e.printStackTrace();
    }

    return null;
  }
}