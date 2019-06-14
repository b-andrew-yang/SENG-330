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

public class NotifyDevicesController {

    @FXML
    private ListView notifyDevicesList;

    private DeviceModel model;

    public NotifyDevicesController(){
        model = new DeviceModel();
    }

    @FXML
    void onNotifyAllClick(ActionEvent event) throws IOException{
    	Parent hubView = FXMLLoader.load(getClass().getResource("/AdminPanelView.fxml"));
        Scene hubViewScene = new Scene(hubView);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(hubViewScene);
        window.show();    
    }

    @FXML
    void onSubmitClick(ActionEvent event) throws IOException{
    	Parent hubView = FXMLLoader.load(getClass().getResource("/AdminPanelView.fxml"));
        Scene hubViewScene = new Scene(hubView);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(hubViewScene);
        window.show();    
    }

    @FXML
    void onCancelClick(ActionEvent event) throws IOException{
        Parent hubView = FXMLLoader.load(getClass().getResource("/AdminPanelView.fxml"));
        Scene hubViewScene = new Scene(hubView);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(hubViewScene);
        window.show();           
    }

    @FXML
    private void initialize(){
        ObservableList<String> selections = FXCollections.observableArrayList();
        notifyDevicesList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        HashMap<String, String> devList = new HashMap<String, String>();
        devList = model.getDevList();
        HashMap<String, String> typeList = new HashMap<String, String>();
        typeList = model.getTypeList();
        notifyDevicesList.setItems(selections);

        Iterator it = devList.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry devicePair = (Map.Entry)it.next();
            String devString = devicePair.getKey() + " : " + typeList.get(devicePair.getValue());
            selections.add(devString);
        }
        notifyDevicesList.setItems(selections); 
    }

}
