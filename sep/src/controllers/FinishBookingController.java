package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.Model;
import view.ViewHandler;

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

  public FinishBookingController(){}

  public void init(ViewHandler viewHandler, Model model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;

    format.setText(BookARoomController.getFormat());
    if(BookARoomController.endTime.getDay() > BookARoomController.startTime.getDay())
      date.setText(BookARoomController.daY + "/" + BookARoomController.montH + "/" + BookARoomController.yeaR + " -> "
      + BookARoomController.endTime.getDay() + "/" + BookARoomController.endTime.getMonth() + "/"
      + BookARoomController.endTime.getYear());
    else
      date.setText(BookARoomController.daY + "/" + BookARoomController.montH + "/" + BookARoomController.yeaR);
    time.setText(BookARoomController.houR + ":" + BookARoomController.miN + " -> " + BookARoomController.endTime.getHour() + ":" + BookARoomController.endTime.getMinute());
    if(format.getText().equals("campus"))
    {
      if (BookARoomController.unI)
      {
        int newCap = SelectRoomController.room.getCapacity();
        model.Room newRoom = model.getRoomByID(SelectRoomController.room.getUnitedWith());
        newCap += newRoom.getCapacity();
        capacity.setText("" +newCap);
      }
      else
        capacity.setText("" + SelectRoomController.room.getCapacity());
    }
    else
      capacity.setText("");

    if(BookARoomController.unI)
    {
      united.setText("Yes");
      room.setText("" + "+" + "");
    }
    else
    {
      united.setText("No");
      room.setText("");
    }

    course.setText(SelectCourseController.course.getCourseID());
    session.setText(SelectSessionController.session.getNumber() + ", " + SelectSessionController.session.getCourse().getCourseID());
  }

  @FXML public void finishPressed()
  {
    viewHandler.openView("Start");
  }
  @FXML public void backPressed()
  {
    if(format.getText().equals("campus"))
      viewHandler.openView("SelectRoom");
    else
      viewHandler.openView("BookARoom");
  }

  public void reset()
  {
    format.setText("");
    date.setText("");
    time.setText("");
    capacity.setText("");
    united.setText("");
    room.setText("");
    course.setText("");
    session.setText("");
  }

  public Region getRoot()
  {
    return root;
  }
}
