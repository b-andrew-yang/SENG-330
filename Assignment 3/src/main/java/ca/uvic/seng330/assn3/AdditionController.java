package ca.uvic.seng330.assn3;
/*
 * Code sample from https://stackoverflow.com/questions/36868391/using-javafx-controller-without-fxml/36873768
 */
public class AdditionController {

  private final AdditionModel model ;

  public AdditionController(AdditionModel model) {
    this.model = model ;
  }

  public void updateX(String x) {
    model.setX(convertStringToInt(x));
  }

  public void updateY(String y) {
    model.setY(convertStringToInt(y));
  }

  private int convertStringToInt(String s) {
    if (s == null || s.isEmpty()) {
      return 0 ;
    }
    if ("-".equals(s)) {
      return 0 ;
    }
    return Integer.parseInt(s);
  }
}