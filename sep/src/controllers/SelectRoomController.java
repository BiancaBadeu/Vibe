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

/**
 * A class representing the controller for an FXML SelectRoom
 */
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

  /**
   * An Empty 0 argument constructor
   */
  public SelectRoomController(){}
  /**
   * @param viewHandler a ViewHandler variable for control over the GUI
   * @param model a Model variable for the interface
   * @param root a Region variable for location within the GUI
   *
   * An intializer method for the class. The parameters are initialized and table columns are created
   * for the TableView. The contents of the columns are set by a loop that obtains the details of rooms within
   * a roomList
   */
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

  /**
   * An FXMl method called when a button Continue is pressed. The hovered room is obtained and checks are performed to see
   * if its capacity matches the number of student for the course and if the room is available within the specified
   * period. The current window is closed and a new one is opened
   * @see FinishBookingController
   */
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

  /**
   * An FXML method called when a button Go Back is pressed. The current window is closed and a new one opens
   * @see BookARoomController
   */
  @FXML public void roomGoBackPressed()
  {
    viewHandler.openView("BookARoom");
  }

  /**
   * Reset method for the class. The table columns and filters are reset and the columns are updated with the current
   * information in the roomList
   */
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