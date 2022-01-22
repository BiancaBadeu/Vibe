package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.DateTime;
import model.Model;
import model.Room;
import model.Session;
import view.ViewHandler;

public class SelectRoomController
{
  @FXML TableView roomTable;

  private Region root;
  private Model model;
  private ViewHandler viewHandler;

  static Room room;

  private String capacity;
  private int cap;
  private DateTime startTime = BookARoomController.startTime;
  private Session session = SelectSessionController.session;
  private DateTime endTime = session.calculateEndTimeForSession(session.getNumberOfLessonsInSession());

  public SelectRoomController(){}

  public void init(ViewHandler viewHandler, Model model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    capacity = BookARoomController.caP;

    TableColumn roomId = new TableColumn("ID");
    roomId.setCellValueFactory(new PropertyValueFactory<>("id"));
    TableColumn capacitY= new TableColumn("Capacity");
    capacitY.setCellValueFactory(new PropertyValueFactory<>("capacity"));
    TableColumn uniteWitH= new TableColumn("UnitedWith");
    uniteWitH.setCellValueFactory(new PropertyValueFactory<>("unitedWith"));

    roomTable.getColumns().setAll(roomId, capacitY, uniteWitH);
    cap = Integer.parseInt(capacity);
    System.out.println(cap);
    if(BookARoomController.unI)
    {
      System.out.println(":)");
      for (int i = 0; i < model.getRoomsAvailableInPeriodBySizeBiggerThanUnitable(cap, startTime, endTime).size(); i++)
      {
        roomTable.getItems().add(model.getRoomsAvailableInPeriodBySizeBiggerThanUnitable(cap, startTime, endTime).get(i));
        System.out.println("room" + i);
      }
    }
    else
    {
      for (int i = 0; i < model.getRoomsAvailableInPeriodBySizeBiggerThanNotUnitable(cap, startTime, endTime).size(); i++)
        roomTable.getItems().add(model.getRoomsAvailableInPeriodBySizeBiggerThanNotUnitable(cap, startTime, endTime).get(i));
    }
  }

  @FXML public void roomContinuePressed()
  {
    capacity = BookARoomController.caP;
    int index = roomTable.getSelectionModel().getFocusedIndex();
    if(index > -1)
    {
      cap = Integer.parseInt(capacity);
      if(BookARoomController.unI)
      {
          room = model.getRoomsAvailableInPeriodBySizeBiggerThanUnitable(cap, startTime, endTime).get(index);
      }
      else
      {
        room = model.getRoomsAvailableInPeriodBySizeBiggerThanNotUnitable(cap, startTime, endTime).get(index);
      }
      viewHandler.openView("FinishBooking");
    }

  }

  @FXML public void roomGoBackPressed()
  {
    viewHandler.openView("BookARoom");
  }
  public void reset()
  {
    capacity = BookARoomController.caP;
    roomTable.getItems().clear();
    cap = Integer.parseInt(capacity);
    System.out.println(cap);
    if(BookARoomController.unI)
    {
      for (int i = 0; i < model.getRoomsAvailableInPeriodBySizeBiggerThanUnitable(cap, startTime, endTime).size(); i++)
        roomTable.getItems().add(model.getRoomsAvailableInPeriodBySizeBiggerThanUnitable(cap, startTime, endTime).get(i));
    }
    else
    {
      for (int i = 0; i < model.getRoomsAvailableInPeriodBySizeBiggerThanNotUnitable(cap, startTime, endTime).size(); i++)
        roomTable.getItems().add(model.getRoomsAvailableInPeriodBySizeBiggerThanNotUnitable(cap, startTime, endTime).get(i));
    }
  }
  public Region getRoot()
  {
    return root;
  }
}