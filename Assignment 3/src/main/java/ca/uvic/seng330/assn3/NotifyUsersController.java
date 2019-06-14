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
import javafx.collections.ObservableList;
import javafx.scene.control.MultipleSelectionModel;
import java.util.Iterator;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.*;
import javafx.collections.FXCollections;

public class NotifyUsersController {

    @FXML
    private ListView<String> notifyUsersList;

    private UserModel model;

    public NotifyUsersController(){
        model = new UserModel();
    }

    @FXML
    private void onCancelClick(ActionEvent event) throws IOException{
    	Parent hubView = FXMLLoader.load(getClass().getResource("/AdminPanelView.fxml"));
        Scene hubViewScene = new Scene(hubView);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(hubViewScene);
        window.show();    
    }

    @FXML
    private void onNotifyAllUsersClick(ActionEvent event) throws IOException{
    	Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Alert sent to all Users", ButtonType.OK);
        confirm.showAndWait();

        Parent hubView = FXMLLoader.load(getClass().getResource("/AdminPanelView.fxml"));
        Scene hubViewScene = new Scene(hubView);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(hubViewScene);
        window.show();        	
    }

    @FXML
    private void onSubmitClick(ActionEvent event) throws IOException{
    	Parent hubView = FXMLLoader.load(getClass().getResource("/AdminPanelView.fxml"));
        Scene hubViewScene = new Scene(hubView);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(hubViewScene);
        window.show();     
    }

    @FXML
    private void initialize(){
        ObservableList<String> selections = FXCollections.observableArrayList();
        notifyUsersList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        HashMap<String, String> userList = new HashMap<String, String>();
        userList = model.getUserList();
        notifyUsersList.setItems(selections);

        Iterator it = userList.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry user = (Map.Entry)it.next();
            String userString = user.getKey() + " : " + user.getValue();
            selections.add(userString);
            System.out.println(userString);
        }
        notifyUsersList.setItems(selections);    
    }
}

