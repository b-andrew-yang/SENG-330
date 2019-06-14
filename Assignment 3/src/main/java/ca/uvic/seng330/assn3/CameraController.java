package ca.uvic.seng330.assn3;


import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

public class CameraController {

    private Camera model;
    @FXML
    private Button backButton;

    @FXML
    private Button stopRecordingButton;

    @FXML
    private Label statusLabel;

    @FXML
    private Button recordButton;

    @FXML
    private Label spaceRemainingLabel;

    @FXML
    private Label isRecordingLabel;

    @FXML
    private Button streamButton;

    private UserModel userModel;

    private DeviceModel devModel;


    public CameraController() {
        this.model = new Camera();
        this.model.setStatus(Status.NORMAL);
        //statusLabel.setText("On");
        userModel = new UserModel();
        devModel = new DeviceModel();
    }

    public CameraController(Camera c) {
        this.model = c;
        this.model.setStatus(Status.NORMAL);
        //statusLabel.setText("On");
        userModel = new UserModel();
        devModel = new DeviceModel();
    }

    @FXML
    private void onBackClick(ActionEvent event) throws IOException {
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
    private void onRecordClick(ActionEvent event) throws ParseException{
        try {
            model.record();
        } catch (CameraFullException e) {
            model.setStatus(Status.ALERT);
            spaceRemainingLabel.setText("Error: No space remaining");
            isRecordingLabel.setText("Off");
        }

        devModel.updateStatus(devModel.getDeviceName(), "Recording");
        isRecordingLabel.setText("On");
    }

    @FXML
    private void onStopRecordClick(ActionEvent event) throws CameraFullException, ParseException{
        if(model.getIsRecording()) {
            model.record();
        }

        model.setStatus(Status.NORMAL);
        statusLabel.setText("On");
        isRecordingLabel.setText("Off");
        devModel.updateStatus(devModel.getDeviceName(), "Off");
    }

    @FXML
    private void streamButton(ActionEvent event) throws IOException, ParseException{
        devModel.updateStatus(devModel.getDeviceName(), "Streaming");

        Parent hubView = FXMLLoader.load(getClass().getResource("/CameraStreamingView.fxml"));
        Scene hubViewScene = new Scene(hubView);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(hubViewScene);

        window.show();
    }

}
