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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.*;

public class RemoveUsersController {

    @FXML
    private ListView removeUsersList;

    private UserModel model;

    public RemoveUsersController(){
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
    private void onSubmitClick(ActionEvent event) throws IOException{
    	ObservableList removeUsers = removeUsersList.getSelectionModel().getSelectedItems();

    	String removeAlert = "";
    	for(Object item: removeUsers){
    		// Format for each item is User : AccessLevel.
    		// This line below just grabs User.
    		String tempUser = (String) item;
    		tempUser = tempUser.substring(0, tempUser.indexOf(":") - 1);
    		model.removeUser(tempUser);
    		removeAlert += tempUser + " ";
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
    private void initialize(){
    	ObservableList<String> selections = FXCollections.observableArrayList();
    	removeUsersList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    	HashMap<String, String> userList = new HashMap<String, String>();
    	userList = model.getUserList();
    	removeUsersList.setItems(selections);

		Iterator it = userList.entrySet().iterator();
    	while(it.hasNext()){
    		Map.Entry user = (Map.Entry)it.next();
    		String userString = user.getKey() + " : " + user.getValue();
    		selections.add(userString);
    		System.out.println(userString);
    	}
    	removeUsersList.setItems(selections);    	

    }

}
