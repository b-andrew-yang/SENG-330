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

public class LightbulbController {

  Lightbulb model;
  UserModel userModel;
  DeviceModel devModel;
  @FXML
  private Button backButton;

  @FXML
  private Button toggleButton;

  @FXML
  private Label statusLabel;

  public LightbulbController() {
    this.model = new Lightbulb();
    userModel = new UserModel();
    devModel = new DeviceModel();
  }

  public LightbulbController(Lightbulb m) {
    this.model = m;
    userModel = new UserModel();
    devModel = new DeviceModel();

    if (model.getCondition() == true) {
      // statusLabel.setText("On");
    } else {
      // statusLabel.setText("Off");
    }
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
    if (model.getCondition() == true) {
      model.toggle();
      statusLabel.setText("Off");
      devModel.updateStatus(devModel.getDeviceName(), "Off");
    } else {
      model.toggle();
      statusLabel.setText("On");
      devModel.updateStatus(devModel.getDeviceName(), "On");
    }
  }
}
