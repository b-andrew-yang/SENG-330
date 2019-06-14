package ca.uvic.seng330.assn3;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.json.simple.parser.ParseException;


public class RegisterController {

  @FXML
  private TextField userID;

  @FXML
  private PasswordField selectPassword;

  @FXML
  private PasswordField re_enterPassword;

  @FXML
  private RadioButton adminRadioButton;

  @FXML
  private ToggleGroup Userlevel;

  @FXML
  private RadioButton userRadioButton;

  @FXML
  private Button registerSubmitButton;

  private final UserModel model;

  public RegisterController() {
    model = new UserModel();
  }

  @FXML
  void onCancelClick(ActionEvent event)  throws IOException{
    Parent hubView = FXMLLoader.load(getClass().getResource("/Login.fxml"));
    Scene hubViewScene = new Scene(hubView);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(hubViewScene);

    window.show();
  }

  @FXML
  void registerSubmitClick(ActionEvent event) throws IOException, ParseException {
    Window owner = registerSubmitButton.getScene().getWindow();

    if (userID.getText().isEmpty()) {
      Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a User ID", ButtonType.OK);
      alert.showAndWait();
      return;
    }
    if (selectPassword.getText().isEmpty()) {
      Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a password", ButtonType.OK);
      alert.showAndWait();
      return;
    }
    if (re_enterPassword.getText().isEmpty()) {
      Alert alert = new Alert(Alert.AlertType.ERROR, "Please re-enter your password",
          ButtonType.OK);
      alert.showAndWait();
      return;
    }
    if (!selectPassword.getText().equals(re_enterPassword.getText())) {
      Alert alert = new Alert(Alert.AlertType.ERROR, "Your passwords must match", ButtonType.OK);
      alert.showAndWait();
      return;
    }
    
    RadioButton selectedButton = (RadioButton) Userlevel.getSelectedToggle();
    String accessChoice = selectedButton.getText();

    model.addUser(userID.getText(), selectPassword.getText(), accessChoice);

    Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Thank you for registering", ButtonType.OK);
    confirm.showAndWait();

    Parent hubView = FXMLLoader.load(getClass().getResource("/Login.fxml"));
    Scene hubViewScene = new Scene(hubView);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(hubViewScene);

    window.show();

  }

}
