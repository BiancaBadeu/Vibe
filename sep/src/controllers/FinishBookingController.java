package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.Model;
import view.ViewHandler;

/**
 * A class representing the controller of an FXML FinishBooking
 */
public class FinishBookingController
{
  @FXML TextField format;
  @FXML TextField date;
  @FXML TextField time;
  @FXML TextField capacity;
  @FXML TextField united;
  @FXML TextField room;
  @FXML TextField course;
  @FXML TextField session;

  private Region root;
  private Model model;
  private ViewHandler viewHandler;

  /**
   * Empty 0 argument constructor
   */
  public FinishBookingController(){}


  /**
   * @param viewHandler a ViewHandler variable for control over the GUI
   * @param model a Model variable for the interface
   * @param root a Region variable for location within the GUI
   *
   * Initializer method for the GUI window. Checks are performed regarding the format of the date. Other checks are
   * performed regarding if the user selected the room to have a certain capacity or being united
   */
  public void init(ViewHandler viewHandler, Model model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;

    format.setText(BookARoomController.format);
    if(BookARoomController.endTime.getDay() > BookARoomController.startTime.getDay())
      date.setText(BookARoomController.daY + "/" + BookARoomController.montH + "/" + BookARoomController.yeaR + " -> "
          + BookARoomController.endTime.getDay() + "/" + BookARoomController.endTime.getMonth() + "/"
          + BookARoomController.endTime.getYear());
    else
      date.setText(BookARoomController.daY + "/" + BookARoomController.montH + "/" + BookARoomController.yeaR);
    time.setText(BookARoomController.houR + ":" + BookARoomController.miN + " -> " + BookARoomController.endTime.getHour() + ":" + BookARoomController.endTime.getMinute());
    if(BookARoomController.format.equals("campus"))
    {
      if (BookARoomController.unI)
      {
        united.setText("Yes");
        int newCap = SelectRoomController.room.getCapacity();
        model.Room newRoom = model.getRoomByID(SelectRoomController.room.getUnitedWith());
        newCap += newRoom.getCapacity();
        capacity.setText("" +newCap);
        room.setText(SelectRoomController.room.getId() + "+" + SelectRoomController.room.getUnitedWith());
      }
      else
      {
        united.setText("No");
        capacity.setText("" + SelectRoomController.room.getCapacity());
        room.setText(SelectRoomController.room.getId());
      }
      SelectSessionController.session.setRoom(SelectRoomController.room);
    }
    else
    {
      united.setText("");
      capacity.setText("");
      room.setText("");
    }
    course.setText(SelectCourseController.course.getCourseID());
    session.setText(SelectSessionController.session.getNumber() + ", " + SelectSessionController.session.getCourse().getCourseID());
  }

  /**
   * @throws Exception java.io.PrintWriter requires an exception to be thrown in order to perform operations
   *
   * An FXML method called when the button named Finish is pressed. This causes the files to be updated and the current window is closed and
   * replaced with a new one
   * @see StartController
   */
  @FXML public void finishPressed() throws Exception
  {
    model.writeFiles();
    viewHandler.openView("Start");
  }

  /**
   * An FXML method called when the button named Back is pressed. When this happens the format TextField is checked
   * if the format is on campus the current window closes and a new one opens.
   * @see SelectRoomController
   * Else the current window closes and a new one opens
   * @see BookARoomController
   */
  @FXML public void backPressed()
  {
    if(format.getText().equals("campus"))
      viewHandler.openView("SelectRoom");
    else
      viewHandler.openView("BookARoom");
  }

  /**
   * Reset method for the controller. Makes the TextFields to be empty, checks if any other data was introduced
   * if so the other fields are also reset
   */
  public void reset()
  {
    format.setText(BookARoomController.format);
    if(BookARoomController.endTime.getDay() > BookARoomController.startTime.getDay())
      date.setText(BookARoomController.daY + "/" + BookARoomController.montH + "/" + BookARoomController.yeaR + " -> "
          + BookARoomController.endTime.getDay() + "/" + BookARoomController.endTime.getMonth() + "/"
          + BookARoomController.endTime.getYear());
    else
      date.setText(BookARoomController.daY + "/" + BookARoomController.montH + "/" + BookARoomController.yeaR);
    time.setText(BookARoomController.houR + ":" + BookARoomController.miN + " -> " + BookARoomController.endTime.getHour() + ":" + BookARoomController.endTime.getMinute());
    if(BookARoomController.format.equals("campus"))
    {
      if (BookARoomController.unI)
      {
        united.setText("Yes");
        int newCap = SelectRoomController.room.getCapacity();
        model.Room newRoom = model.getRoomByID(SelectRoomController.room.getUnitedWith());
        newCap += newRoom.getCapacity();
        capacity.setText("" +newCap);
        room.setText(SelectRoomController.room.getId() + "+" + SelectRoomController.room.getUnitedWith());
      }
      else
      {
        united.setText("No");
        capacity.setText("" + SelectRoomController.room.getCapacity());
        room.setText(SelectRoomController.room.getId());
      }
      SelectSessionController.session.setRoom(SelectRoomController.room);
    }
    else
    {
      united.setText("");
      capacity.setText("");
      room.setText("");
    }
    course.setText(SelectCourseController.course.getCourseID());
    session.setText(SelectSessionController.session.getNumber() + ", " + SelectSessionController.session.getCourse().getCourseID());

  }

  /**
   * @return root
   *
   * Returns the current root
   */
  public Region getRoot()
  {
    return root;
  }
}
