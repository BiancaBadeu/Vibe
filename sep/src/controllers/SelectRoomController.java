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

  private String capacity = BookARoomController.caP;
  int cap;
  private DateTime startTime = BookARoomController.startTime;
  private Session session = SelectSessionController.session;
  private DateTime endTime = session.calculateEndTimeForSession(session.getNumberOfLessonsInSession());

  public SelectRoomController(){}

  public void init(ViewHandler viewHandler, Model model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;


    TableColumn roomId = new TableColumn("ID");
    roomId.setCellValueFactory(new PropertyValueFactory<>("id"));
    TableColumn capacitY= new TableColumn("Capacity");
    capacitY.setCellValueFactory(new PropertyValueFactory<>("capacity"));
    TableColumn uniteWitH= new TableColumn("UnitedWith");
    uniteWitH.setCellValueFactory(new PropertyValueFactory<>("unitedWith"));

    roomTable.getColumns().setAll(roomId, capacitY, uniteWitH);
    if(capacity.equals("-"))
      cap=0;
    else
      cap = Integer.parseInt(capacity);
    try
    {
      for (int i = 0; i < model.getRoomsAvailableInPeriodBySizeBiggerThan(cap, startTime, endTime).size(); i++)
      {
        if(BookARoomController.unI)
        {
          if (model.getRoomsAvailableInPeriodBySizeBiggerThan(cap, startTime, endTime).get(i).getUnitedWith() != null)
            roomTable.getItems().add(model.getRoomsAvailableInPeriodBySizeBiggerThan(cap, startTime, endTime).get(i));
        }
        else
          if(model.getRoomsAvailableInPeriodBySizeBiggerThan(cap, startTime, endTime).get(i).getUnitedWith() == null)
            roomTable.getItems().add(model.getRoomsAvailableInPeriodBySizeBiggerThan(cap, startTime, endTime).get(i));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  @FXML public void roomContinuePressed()
  {
    int index = roomTable.getSelectionModel().getFocusedIndex();
    if(index > -1)
    {
      if(capacity.equals("-"))
        cap=0;
      else
        cap = Integer.parseInt(capacity);
      for (int i = 0; i < model.getRoomsAvailableInPeriodBySizeBiggerThan(cap, startTime, endTime).size() && index>=0; i++)
      {
        if(BookARoomController.unI)
        {
          if (model.getRoomsAvailableInPeriodBySizeBiggerThan(cap, startTime, endTime).get(i).getUnitedWith() != null)
            if(index!=0)
              index--;
            else
              room = model.getRoomsAvailableInPeriodBySizeBiggerThan(cap, startTime, endTime).get(i);
        }
        else
        if(model.getRoomsAvailableInPeriodBySizeBiggerThan(cap, startTime, endTime).get(i).getUnitedWith() == null)
          if(index!=0)
            index--;
          else
            room = model.getRoomsAvailableInPeriodBySizeBiggerThan(cap, startTime, endTime).get(i);
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
    roomTable.getItems().clear();
    if(capacity.equals("-"))
      cap=0;
    else
      cap = Integer.parseInt(capacity);
    try
    {
      for (int i = 0; i < model.getRoomsAvailableInPeriodBySizeBiggerThan(cap, startTime, endTime).size(); i++)
      {
        if(BookARoomController.unI)
        {
          if (model.getRoomsAvailableInPeriodBySizeBiggerThan(cap, startTime, endTime).get(i).getUnitedWith() != null)
            roomTable.getItems().add(model.getRoomsAvailableInPeriodBySizeBiggerThan(cap, startTime, endTime).get(i));
        }
        else
        if(model.getRoomsAvailableInPeriodBySizeBiggerThan(cap, startTime, endTime).get(i).getUnitedWith() == null)
          roomTable.getItems().add(model.getRoomsAvailableInPeriodBySizeBiggerThan(cap, startTime, endTime).get(i));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  public Region getRoot()
  {
    return root;
  }
}