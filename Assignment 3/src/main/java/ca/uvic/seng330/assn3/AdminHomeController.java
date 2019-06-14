package ca.uvic.seng330.assn3;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.MultipleSelectionModel;
import java.util.Iterator;
import org.json.simple.parser.ParseException;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import java.io.FileWriter;

public class AdminHomeController {

    @FXML
    private ListView registeredDeviceList;

    private DeviceModel model;

    public AdminHomeController(){
      model = new DeviceModel();
    }

    @FXML
    void onAdminClick(ActionEvent event) throws IOException{
        Parent hubView = FXMLLoader.load(getClass().getResource("/AdminPanelView.fxml"));
        Scene hubViewScene = new Scene(hubView);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(hubViewScene);
        window.show();    
    }

    @FXML
    void onSelectClick(ActionEvent event) throws IOException{
      //ObservableList selectDevice = registeredDeviceList.getSelectionModel().getSelectedItems();
      String selectedDevice = (String) registeredDeviceList.getSelectionModel().getSelectedItem();

      String nickName = selectedDevice.substring(0, selectedDevice.indexOf(" "));
      selectedDevice = selectedDevice.substring(selectedDevice.indexOf(":")+2, selectedDevice.indexOf("|")-1);
      System.out.println(selectedDevice);
      String selectView = "/";
      if(selectedDevice.equals("Thermostat")){
        selectView += "ThermostatControlView.fxml";
      }else if(selectedDevice.equals("Lightbulb")){
        selectView += "LightbulbControlView.fxml";
      }else if(selectedDevice.equals("SmartPlug")){
        selectView += "SmartPlugControlView.fxml";
      }else if(selectedDevice.equals("Camera")){
        selectView += "CameraView.fxml";
      }

      try{
        FileWriter selectName = new FileWriter("SelectName.txt");

        selectName.write(nickName);
        selectName.flush();
        selectName.close();
      }catch(IOException e){
        System.out.println("Write Nickname Fail");
      }

      System.out.println(selectView);
      Parent hubView = FXMLLoader.load(getClass().getResource(selectView));
      Scene hubViewScene = new Scene(hubView);

      Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
      window.setScene(hubViewScene);
      window.show();    
    }

    @FXML
    void onShutdownClick(ActionEvent event) throws ParseException, IOException{
      model.shutDown();

      Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "System has shut down", ButtonType.OK);
      confirm.showAndWait();

      Parent hubView = FXMLLoader.load(getClass().getResource("/AdminHomeView.fxml"));
      Scene hubViewScene = new Scene(hubView);

      Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
      window.setScene(hubViewScene);
      window.show();    
    }

    @FXML
    private void initialize(){
        ObservableList<String> selections = FXCollections.observableArrayList();
        registeredDeviceList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        HashMap<String, String> devList = new HashMap<String, String>();
        devList = model.getDevList();
        HashMap<String, String> typeList = new HashMap<String, String>();
        typeList = model.getTypeList();
        HashMap<String, String> statusList = new HashMap<String, String>();
        statusList = model.getStatusList();
        registeredDeviceList.setItems(selections);

        Iterator it = devList.entrySet().iterator();
        while(it.hasNext()){
          Map.Entry devicePair = (Map.Entry)it.next();
          String devString = devicePair.getKey() + " : " + typeList.get(devicePair.getValue());
            devString += " | Status : ";
            devString += statusList.get(devicePair.getKey());
          selections.add(devString);
        }
        registeredDeviceList.setItems(selections); 
    }

}