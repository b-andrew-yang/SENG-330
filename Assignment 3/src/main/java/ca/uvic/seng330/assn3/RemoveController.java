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

public class RemoveController {

    @FXML
    private ListView removeDeviceSel;

    private DeviceModel model;

    public RemoveController(){
    	model = new DeviceModel();
    }

    @FXML
    private void onSubmitClick(ActionEvent event) throws IOException, ParseException {
    	ObservableList removeDevices = removeDeviceSel.getSelectionModel().getSelectedItems();

    	String removeAlert = "";
    	for(Object item: removeDevices){
    		// Format for each item is UUID : nickName.
    		// This line below just grabs User.
    		String tempDev = (String) item;
    		tempDev = tempDev.substring(0, tempDev.indexOf(":")-1);
    		model.removeDevice(tempDev);
    		removeAlert += tempDev + " ";
    	}

    	Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Successful removal of: " + removeAlert, ButtonType.OK);
    	confirm.showAndWait();

    	Parent hubView = FXMLLoader.load(getClass().getResource("/AdminPanelView.fxml"));
        Scene hubViewScene = new Scene(hubView);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(hubViewScene);
        window.show();    
    }

    @FXML
    private void onCancelClick(ActionEvent event) throws IOException {
    	Parent hubView = FXMLLoader.load(getClass().getResource("/AdminPanelView.fxml"));
        Scene hubViewScene = new Scene(hubView);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(hubViewScene);
        window.show();    
    }

    @FXML
    private void initialize(){
        ObservableList<String> selections = FXCollections.observableArrayList();
        removeDeviceSel.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        HashMap<String, String> devList = new HashMap<String, String>();
        devList = model.getDevList();
        HashMap<String, String> typeList = new HashMap<String, String>();
        typeList = model.getTypeList();
        HashMap<String, String> statusList = new HashMap<String, String>();
        statusList = model.getStatusList();
        removeDeviceSel.setItems(selections);

        Iterator it = devList.entrySet().iterator();
        while(it.hasNext()){
          Map.Entry devicePair = (Map.Entry)it.next();
          String devString = devicePair.getKey() + " : " + typeList.get(devicePair.getValue());
            devString += " | Status : ";
            devString += statusList.get(devicePair.getKey());
          selections.add(devString);
        }
        removeDeviceSel.setItems(selections); 
    }
}
