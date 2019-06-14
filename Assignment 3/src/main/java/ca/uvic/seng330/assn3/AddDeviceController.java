package ca.uvic.seng330.assn3;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;
import javafx.scene.control.*;

public class AddDeviceController {
    private DeviceModel model;

    public AddDeviceController(){
        model = new DeviceModel();
    }

    @FXML
    private TextField deviceNickname;

    @FXML
    private ChoiceBox<String> deviceType;

    @FXML
    void onAddDevSubmitClick(ActionEvent event) throws ParseException, Temperature.TemperatureOutofBoundsException, IOException{
    	String nickName = deviceNickname.getText();
    	String typeDevice = deviceType.getSelectionModel().getSelectedItem();

        if(model.checkNickname(nickName)){
            if(typeDevice.equals("Thermostat")){
                Thermostat therm = new Thermostat();
                model.addDevice(therm, nickName, typeDevice);            
            }else if(typeDevice.equals("Camera")){
                Camera cam = new Camera();
                model.addDevice(cam, nickName, typeDevice);
            }else if(typeDevice.equals("Lightbulb")){
                Lightbulb bulb = new Lightbulb();
                model.addDevice(bulb, nickName, typeDevice);
            }else if(typeDevice.equals("SmartPlug")){
                SmartPlug plug = new SmartPlug();
                model.addDevice(plug, nickName, typeDevice);
            }
        }else{
            Alert nickNameError = new Alert(Alert.AlertType.ERROR, "Nickname already used, pick a new one", ButtonType.OK);
            nickNameError.showAndWait();
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, nickName + " successfully added.", ButtonType.OK);
        confirm.showAndWait();

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

}
