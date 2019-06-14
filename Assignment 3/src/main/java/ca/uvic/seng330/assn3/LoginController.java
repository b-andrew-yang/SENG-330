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

public class LoginController {

  @FXML
  private TextField loginID;

  @FXML
  private PasswordField loginPassword;

  @FXML
  private Button loginRegisterButton;

  @FXML
  private Button loginSubmitButton;

  private final UserModel model;

  public LoginController() {
    model = new UserModel();
  }

  @FXML
  void loginSubmitClick(ActionEvent event) throws IOException, ParseException {
        /*
            Check if User ID matches. Check if password matches.
            Check User ID access level if these all work.
         */
    Window owner = loginSubmitButton.getScene().getWindow();

    if (loginID.getText().isEmpty()) {
      Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a User ID", ButtonType.OK);
      alert.showAndWait();
      return;
    }
    if (loginPassword.getText().isEmpty()) {
      Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a password", ButtonType.OK);
      alert.showAndWait();
      return;
    }

    // Check if User ID and Password Match
    if (model.checkUserAndPassword(loginID.getText(), loginPassword.getText())) {
      if (model.checkAdmin(loginID.getText(), loginPassword.getText())) {
        model.logCurrentAccess("Admin");

        Parent hubView = FXMLLoader.load(getClass().getResource("/AdminHomeView.fxml"));
        Scene hubViewScene = new Scene(hubView);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(hubViewScene);

        window.show();
      }else{
        // Go to main hub
        model.logCurrentAccess("User");
        Parent hubView = FXMLLoader.load(getClass().getResource("/HubHomeView.fxml"));
        Scene hubViewScene = new Scene(hubView);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(hubViewScene);

        window.show();        
      }
    }else{
      Alert alert = new Alert(Alert.AlertType.ERROR, "ID or password is wrong", ButtonType.OK);
      alert.showAndWait();
      return;
    }
  }

  @FXML
  void registerClick(ActionEvent event) throws IOException{
        /*
            Send to register screen.
         */

    Window owner = loginRegisterButton.getScene().getWindow();

    Parent hubView = FXMLLoader.load(getClass().getResource("/Register.fxml"));
    Scene hubViewScene = new Scene(hubView);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(hubViewScene);

    window.show();
  }

}
