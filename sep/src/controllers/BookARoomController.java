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
import model.Session;
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

  ObservableList<String> displayChoice = FXCollections.observableArrayList("0","20","30","45");

  private Region root;
  private Model model;
  private ViewHandler viewHandler;

  static String format;
  static int dayY;
  static int daY;
  static int montH;
  static int yeaR;
  static int houR;
  static int miN;
  static String caP;
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
    capacity.setValue("0");
    unitable.setSelected(false);
  }

  public void setInfo()
  {
    int d, m, y, h, min, dayy;
    d = Integer.parseInt(day.getText());
    m = Integer.parseInt(month.getText());
    y = Integer.parseInt(year.getText());
    h = Integer.parseInt(hour.getText());
    min = Integer.parseInt(minute.getText());

    startTime = new DateTime(d, m, y, h, min);
    SelectSessionController.session.setDateAndStartTime(startTime);
    endTime = SelectSessionController.session.calculateEndTimeForSession(SelectSessionController.session.getNumberOfLessonsInSession());
    dayy = Session.getDayNumber(startTime);
    SelectSessionController.session.setDayNumber(dayy);

    dayY = dayy;
    daY = d;
    montH = m;
    yeaR = y;
    houR = h;
    miN = min;
    caP = (String) capacity.getValue();
    if(unitable.isSelected())
      unI = true;
    else
      unI = false;
  }


  @FXML public void showAvailableRoomsPressed()
  {
    setInfo();
    this.format = "campus";
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
    capacity.setItems(displayChoice);
    capacity.setValue("0");
    day.setText("");
    month.setText("");
    year.setText("");
    hour.setText("");
    minute.setText("");
    unitable.setSelected(false);

  }
  public Region getRoot()
  {
    return root;
  }
}