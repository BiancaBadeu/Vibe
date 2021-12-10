package controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import model.Model;
import view.ViewHandler;

public class BookingSystemController
{
  private Region root;
  private Model model;
  private ViewHandler viewHandler;

  public BookingSystemController(){}

  public void init(ViewHandler viewHandler, Model model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
  }

  @FXML public void bookARoomPressed()
  {
    viewHandler.openView("SelectCourse");
  }

  @FXML public void viewYourBookingsPressed()
  {
    viewHandler.openView("ViewBookingsBy");
  }

  @FXML public void removeABookingPressed()
  {
    viewHandler.openView("RemoveBooking");
  }

  public void reset(){}
  public Region getRoot()
  {
    return root;
  }
}
