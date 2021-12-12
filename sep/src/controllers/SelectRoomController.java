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

  private int capacity = BookARoomController.caP;
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
    try
    {
      for (int i = 0; i < model.getRoomsAvailableInPeriodBySizeBiggerThan(capacity, startTime, endTime).size(); i++)
      {
       roomTable.getItems().add(model.getRoomsAvailableInPeriodBySizeBiggerThan(capacity, startTime, endTime).get(i));
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
      room = model.getRoomsAvailableInPeriodBySizeBiggerThan(capacity, startTime, endTime).get(index);
      viewHandler.openView("BookARoom");
    }

  }

  @FXML public void roomGoBackPressed()
  {
    viewHandler.openView("BookARoom");
  }
  public void reset() {}
  public Region getRoot()
  {
    return root;
  }
}
