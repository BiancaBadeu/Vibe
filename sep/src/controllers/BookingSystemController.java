package controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import model.Model;
import view.ViewHandler;

/**
 * A class representing a controller for a BookingSystem FXML file
 */
public class BookingSystemController
{
  private Region root;
  private Model model;
  private ViewHandler viewHandler;

  /**
   * An 0 argument empty constructor
   */
  public BookingSystemController(){}


  /**
   * @param viewHandler a ViewHandler variable for control over the GUI
   * @param model a Model variable for the interface
   * @param root a Region variable for location within the GUI
   *
   *  A method to initialize the GUI window by initializing the parameters previously mentioned
   */
  public void init(ViewHandler viewHandler, Model model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
  }

  /**
   * An FXML method that when a button named Book A Room is pressed the window is closed and a new one is opened
   * @see SelectCourseController
   */
  @FXML public void bookARoomPressed()
  {
    viewHandler.openView("SelectCourse");
  }

  /**
   * An FXML method that when a button named View Your Bookings is pressed the window is closed and a new one is opened
   * @see ViewFilteredBookingsController
   */
  @FXML public void viewYourBookingsPressed()
  {
    viewHandler.openView("ViewFilteredBookings");
  }

  /**
   * An FXML method that when a button named Remove a Booking is pressed the window is closed and a new one is opened.
   * @see RemoveBookingController
   */
  @FXML public void removeABookingPressed()
  {
    viewHandler.openView("RemoveBooking");
  }

  /**
   * An FXML method that when a button named Back is pressed the window is closed and a new one is opened
   * @see StartController
   */
  @FXML public void backPressed()
  {
    viewHandler.openView("Start");
  }

  /**
   * A method to reset the GUI window when called
   */
  public void reset(){}

  /**
   * @return root the root
   *
   * Method to return the current root
   */
  public Region getRoot()
  {
    return root;
  }
}
