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

/**
 * A class representing a controller for an FXML file BookARoom
 */
public class BookARoomController
{
  @FXML TextField day;
  @FXML TextField month;
  @FXML TextField year;
  @FXML TextField hour;
  @FXML TextField minute;
  @FXML ChoiceBox capacity;
  @FXML CheckBox unitable;

  ObservableList<String> displayChoice = FXCollections.observableArrayList("20","30","45");

  private Region root;
  private Model model;
  private ViewHandler viewHandler;

  static String format;
  static int daY;
  static int montH;
  static int yeaR;
  static int houR;
  static int miN;
  static String caP;
  static boolean unI;
  static DateTime startTime;
  static DateTime endTime;

  /**
   * A 0 argument empty constructor
   */
  public BookARoomController(){}
  /**
   * @param viewHandler a ViewHandler variable for control over the GUI
   * @param model a Model variable for the interface
   * @param root a Region variable for location within the GUI
   *
   * A method to initialize the GUI window and initializing the parameters previously mentioned as well as ChoiceBox.
   */
  public void init(ViewHandler viewHandler, Model model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;

    capacity.setItems(displayChoice);
    capacity.setValue("-");
  }

  /**
   * A method to obtain the information from the day, month, year, hour and minute to create a DateTime object.
   * Such object is then store for a session as their respective startTime and checks for the capacity and
   * unitability of a room are performed.
   */
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
    caP = (String) capacity.getValue();
    if(unitable.isSelected())
      unI = true;
    else
      unI = false;
  }

  /**
   * An FXML method that when a button named Show Available Rooms is pressed a window is opened.
   * @see SelectRoomController
   */
  @FXML public void showAvailableRoomsPressed()
  {
    setInfo();
    this.format = "campus";
    viewHandler.openView("SelectRoom");
  }

  /**
   * An FXML method called whe a button named Book Online Session is pressed the window is closed and a setInfo() is called
   * to obtain the TextFields.
   * @see FinishBookingController
   */
  @FXML public void bookOnlineSessionPressed()
  {
    setInfo();
    this.format = "online";
    viewHandler.openView("FinishBooking");
  }

  /**
   * An FXML method called when a button named Go Back is called the window is closed and new one is opened.
   * @see SelectSessionController
   */
  @FXML private void bookGoBackPressed()
  {
    viewHandler.openView("SelectSession");
  }

  /**
   * A method that when called restarts the values of the TextFields and ChoiceBox to make them empty
   */
  public void reset()
  {
    capacity.setValue("-");
    day.setText("");
    month.setText("");
    year.setText("");
    hour.setText("");
    minute.setText("");
  }

  /**
   * @return root the root
   *
   * A getter to return the current root
   */
  public Region getRoot()
  {
    return root;
  }
}