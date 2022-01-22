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

  @FXML public void finishPressed() throws Exception
  {
    if(!room.getText().equals(""))
      SelectRoomController.room.setBooked(true);
    model.writeFiles();
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

  public Region getRoot()
  {
    return root;
  }
}
