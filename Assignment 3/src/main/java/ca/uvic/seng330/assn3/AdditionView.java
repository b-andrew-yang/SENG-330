package ca.uvic.seng330.assn3;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/*
 * Code sample from https://stackoverflow.com/questions/36868391/using-javafx-controller-without-fxml/36873768
 */
public class AdditionView implements EventHandler<ActionEvent>{
  private GridPane view ;
  private TextField xField;
  private TextField yField;
  private Label sumLabel;
  private Button aButton;

  private AdditionController controller ;
  private AdditionModel model ;

  public AdditionView(AdditionController controller, AdditionModel model) {

    this.controller = controller ;
    this.model = model ;

    createAndConfigurePane();

    createAndLayoutControls();

    updateControllerFromListeners();

    observeModelAndUpdateControls();

  }

  public Parent asParent() {
    return view ;
  }

  private void observeModelAndUpdateControls() {
    model.xProperty().addListener((obs, oldX, newX) ->
        updateIfNeeded(newX, xField));

    model.yProperty().addListener((obs, oldY, newY) ->
        updateIfNeeded(newY, yField));

    sumLabel.textProperty().bind(model.sumProperty().asString());
  }

  private void updateIfNeeded(Number value, TextField field) {
    String s = value.toString() ;
    if (! field.getText().equals(s)) {
      field.setText(s);
    }
  }

  private void updateControllerFromListeners() {
    xField.textProperty().addListener((obs, oldText, newText) -> controller.updateX(newText));
    yField.textProperty().addListener((obs, oldText, newText) -> controller.updateY(newText));
  }

  private void createAndLayoutControls() {

    //NB: this code has VERY limited UI design. You should do more.
    /*xField = new TextField();
    configTextFieldForInts(xField);
    xField.setMaxWidth(40); */

    /*yField = new TextField();
    //Setting "ID" allows us to lookup the control by ID in DesktopPaneTest
    yField.setId("yField");
    configTextFieldForInts(yField);
    yField.setMaxWidth(40);*/

    //sumLabel = new Label();

    aButton = new Button("Camera");
    aButton.setOnAction(this);

    view.addRow(0, new Label("X:"), xField);
    view.addRow(1, new Label("Y:"), yField);
    view.addRow(2, new Label("Sum:"), sumLabel);
    view.addRow(3, new Label("button"), aButton);
    //view.addRow(4, new Label("Camera"), aButton);
    //view.addRow(5, new Label("SmartBulb"), aButton);
  }

  @Override
  public void handle(ActionEvent event){
    if(event.getSource() == aButton){
      System.out.println("Entered Camera");
    }
  }

  private void createAndConfigurePane() {
    view = new GridPane();

    ColumnConstraints leftCol = new ColumnConstraints();
    leftCol.setHalignment(HPos.RIGHT);
    leftCol.setHgrow(Priority.NEVER);

    ColumnConstraints rightCol = new ColumnConstraints();
    rightCol.setHgrow(Priority.SOMETIMES);

    view.getColumnConstraints().addAll(leftCol, rightCol);

    view.setAlignment(Pos.CENTER);
    view.setHgap(5);
    view.setVgap(10);
  }

  private void configTextFieldForInts(TextField field) {
    field.setTextFormatter(new TextFormatter<Integer>((Change c) -> {
      if (c.getControlNewText().matches("-?\\d*")) {
        return c ;
      }
      return null ;
    }));
  }
}
