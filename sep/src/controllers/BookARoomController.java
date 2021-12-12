package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.DateTime;
import model.Model;
import view.ViewHandler;

public class BookARoomController
{
  @FXML TextField day;
  @FXML TextField month;
  @FXML TextField year;
  @FXML TextField hour;
  @FXML TextField minute;
  @FXML ChoiceBox capacity;
  @FXML CheckBox unitable;

  ObservableList<String> displayChoice = FXCollections.observableArrayList("30","45");

  private Region root;
  private Model model;
  private ViewHandler viewHandler;

  static String format;
  static int daY;
  static int montH;
  static int yeaR;
  static int houR;
  static int miN;
  static int caP;
  static boolean unI;
  static DateTime startTime;
  static DateTime endTime;

  public BookARoomController(){}

  public void init(ViewHandler viewHandler, Model model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;

    capacity.setItems(displayChoice);
    capacity.setValue("-");
  }

  public void setInfo()
  {
    int d, m, y, h, min;
    d = Integer.parseInt(day.getText());
    m = Integer.parseInt(month.getText());
    y = Integer.parseInt(year.getText());
    h = Integer.parseInt(hour.getText());
    min = Integer.parseInt(minute.getText());

    startTime = new DateTime(d, m, y, h, min);
    SelectSessionController.session.setDateAndStartTime(startTime);
    endTime = SelectSessionController.session.calculateEndTimeForSession(SelectSessionController.session.getNumberOfLessonsInSession());

    daY = d;
    montH = m;
    yeaR = y;
    houR = h;
    miN = min;
    String value = (String) capacity.getValue();
    if(!(value.equals("-")))
      caP = Integer.parseInt(value);
    else
      caP = 0;
    if(unitable.isSelected())
      unI = true;
    else
      unI = false;
  }
  public static String getFormat()
  {
    return format;
  }


  @FXML public void showAvailableRoomsPressed()
  {
    setInfo();
    this.format = "campus";
    System.out.println(getFormat());
    viewHandler.openView("SelectRoom");
  }

  @FXML public void bookOnlineSessionPressed()
  {
    setInfo();
    this.format = "online";
    viewHandler.openView("FinishBooking");
  }

  @FXML private void bookGoBackPressed()
  {
    viewHandler.openView("SelectSession");
  }
  public void reset()
  {
      capacity.setValue("-");
      day.setText("");
      month.setText("");
      year.setText("");
      hour.setText("");
      minute.setText("");
  }
  public Region getRoot()
  {
    return root;
  }
}
