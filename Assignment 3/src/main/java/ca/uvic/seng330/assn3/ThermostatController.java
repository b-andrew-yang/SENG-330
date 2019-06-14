package ca.uvic.seng330.assn3;

import ca.uvic.seng330.assn3.Temperature.TemperatureOutofBoundsException;
import ca.uvic.seng330.assn3.Temperature.Unit;
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

public class ThermostatController {

    private Thermostat model;
    @FXML
    private Button backButton;

    @FXML
    private Button startButton;

    @FXML
    private Label statusLabel;

    @FXML
    private Label temperatureLabel;

    @FXML
    private Button toggleUnitButton;

    @FXML
    private Button stopButton;

    private UserModel userModel;

    private DeviceModel devModel;


    public ThermostatController(Thermostat m) {
        model = m;
        userModel = new UserModel();
        devModel = new DeviceModel();

        model.setStatus(Status.ON);
        //statusLabel.setText("ON");
        if(model.getTemperature().getUnit() == Unit.CELSIUS) {
            //temperatureLabel.setText(""+ model.getTemperature().getTemperature() + " C");

        } else {
            //temperatureLabel.setText(""+ model.getTemperature().getTemperature() + " F");

        }
    }

    public ThermostatController() throws TemperatureOutofBoundsException{
        model = new Thermostat(new Temperature(20.0, Unit.CELSIUS));
        userModel = new UserModel();
        devModel = new DeviceModel();
        model.setStatus(Status.NORMAL);
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
    void onStartClick(ActionEvent event) throws ParseException{
        model.setStatus(Status.ON);
        statusLabel.setText("ON");
        devModel.updateStatus(devModel.getDeviceName(), "On");
        if (model.getTemperature().getUnit() == Unit.CELSIUS) {
            temperatureLabel.setText(model.getTemperature().getTemperature() + " C");
            System.out.println("Celcius set");
        } else {
            temperatureLabel.setText(model.getTemperature().getTemperature() + " F");
            System.out.println("Farenheit set");
        }
    }

    @FXML
    void onStopClick(ActionEvent event) throws ParseException{
        model.setStatus(Status.OFF);
        statusLabel.setText("OFF");
        devModel.updateStatus(devModel.getDeviceName(), "Off");

        if (model.getTemperature().getUnit() == Unit.CELSIUS) {
            temperatureLabel.setText("- C");
        } else {
            temperatureLabel.setText("- F");
        }
    }

    @FXML
    void onToggleClick(ActionEvent event) throws TemperatureOutofBoundsException{

        Temperature newTemp = convertTemp(model.getTemperature());

        model.setTemp(newTemp);

        if(model.getTemperature().getUnit() == Unit.CELSIUS) {
            temperatureLabel.setText(""+ model.getTemperature().getTemperature() + " C");

        } else {
            temperatureLabel.setText(""+ model.getTemperature().getTemperature() + " F");
        }
    }

    /**
     *
     * @param convertFrom The temperature to convert from
     * @return The new temperature with a different unit
     * @throws TemperatureOutofBoundsException If the temperature is too high
     */
    public Temperature convertTemp(Temperature convertFrom) throws TemperatureOutofBoundsException {
        double newTemp;
        Unit newUnit;
        if (convertFrom.getUnit() == Unit.CELSIUS) {
            newUnit = Unit.FARENHEIT;
            newTemp = (convertFrom.getTemperature() * 9/5) + 32;

            return new Temperature(newTemp, newUnit);

        } else {

            newUnit = Unit.CELSIUS;
            newTemp = (convertFrom.getTemperature() - 32) * 5/9;

            return new Temperature(newTemp, newUnit);
        }
    }
}
