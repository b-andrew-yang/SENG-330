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

public class AdminPanelController {

    @FXML
    void onAddDeviceClick(ActionEvent event) throws IOException{
    	Parent hubView = FXMLLoader.load(getClass().getResource("/AddDevice.fxml"));
    	Scene hubViewScene = new Scene(hubView);

    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	window.setScene(hubViewScene);
    	window.show();

    }

    @FXML
    void onAddUserClick(ActionEvent event) throws IOException{
        Parent hubView = FXMLLoader.load(getClass().getResource("/Register.fxml"));
        Scene hubViewScene = new Scene(hubView);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(hubViewScene);
        window.show();        
    }

    @FXML
    void onNotifyDevicesClick(ActionEvent event) throws IOException{
        Parent hubView = FXMLLoader.load(getClass().getResource("/NotifyDevicesView.fxml"));
        Scene hubViewScene = new Scene(hubView);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(hubViewScene);
        window.show();        
    }

    @FXML
    void onNotifyUsersClick(ActionEvent event) throws IOException{
        Parent hubView = FXMLLoader.load(getClass().getResource("/NotifyUsersView.fxml"));
        Scene hubViewScene = new Scene(hubView);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(hubViewScene);
        window.show();        
    }

    @FXML
    void onRemoveDeviceClick(ActionEvent event) throws IOException{
        Parent hubView = FXMLLoader.load(getClass().getResource("/RemoveDeviceView.fxml"));
        Scene hubViewScene = new Scene(hubView);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(hubViewScene);
        window.show();
    }

    @FXML
    void onRemoveUserClick(ActionEvent event) throws IOException{
        Parent hubView = FXMLLoader.load(getClass().getResource("/RemoveUsersView.fxml"));
        Scene hubViewScene = new Scene(hubView);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(hubViewScene);
        window.show();    
    }

    @FXML
    void onGoToHubClick(ActionEvent event) throws IOException{
        Parent hubView = FXMLLoader.load(getClass().getResource("/AdminHomeView.fxml"));
        Scene hubViewScene = new Scene(hubView);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(hubViewScene);
        window.show();
    }

}