package ca.uvic.seng330.assn3;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

public class SmartPlugController {

  private SmartPlug model;
  @FXML
  private Button backButton;

  @FXML
  private Button toggleButton;

  @FXML
  private Label statusLabel;

  private UserModel userModel;

  private DeviceModel devModel;

  public SmartPlugController(SmartPlug m) {
    model = m;
    userModel = new UserModel();
    devModel = new DeviceModel();

    //statusLabel.setText("Off");
  }

  public SmartPlugController() {
    model = new SmartPlug();
    userModel = new UserModel();
    devModel = new DeviceModel();
  }

  @FXML
  void onBackClick(ActionEvent event) throws IOException {
    if(!userModel.checkAdminAccess()){  
      Parent hubView = FXMLLoader.load(getClass().getResource("/HubHomeView.fxml"));
      Scene hubViewScene = new Scene(hubView);

      Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
      window.setScene(hubViewScene);

      window.show();
    }else if(userModel.checkAdminAccess()){
      Parent hubView = FXMLLoader.load(getClass().getResource("/AdminHomeView.fxml"));
      Scene hubViewScene = new Scene(hubView);

      Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
      window.setScene(hubViewScene);

      window.show();
    }
  }

  @FXML
  void onToggleClick(ActionEvent event) throws ParseException{
    model.toggle();
    if (model.getIsOn()) {
      statusLabel.setText("On");
      devModel.updateStatus(devModel.getDeviceName(), "On");
    } else {
      statusLabel.setText("Off");
      devModel.updateStatus(devModel.getDeviceName(), "Off");
    }
  }
}
