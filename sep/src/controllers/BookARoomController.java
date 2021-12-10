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
  @FXML private TextField day;
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

    DateTime startTime = new DateTime(d, m, y, h, min);

  }


  @FXML public void showAvailableRoomsPressed()
  {
    viewHandler.openView("SelectRoom");
  }

  @FXML public void bookOnlineSessionPressed()
  {
    viewHandler.openView("FinishBooking");
  }

  @FXML private void bookGoBackPressed()
  {
    viewHandler.openView("SelectSession");
  }
  public void reset()
  {
      capacity.setValue("-");
      hour.setText("");
      minute.setText("");
  }
  public Region getRoot()
  {
    return root;
  }
}
