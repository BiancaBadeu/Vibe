package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Model;
import model.Session;
import view.ViewHandler;
import javafx.scene.layout.Region;

/**
 * A class representing the controller for an FXML file RemoveBooking
 */
public class RemoveBookingController
{

  @FXML TableView bookTableView;
  @FXML ChoiceBox filterChoiceBox;

  ObservableList<String> filterChoiceBoxList= FXCollections.observableArrayList("TeacherID", "Class", "Day", "Week");

  Model model;
  ViewHandler viewHandler;
  Region root;

  /**
   * Empty 0 argument constructor
   */
  public RemoveBookingController(){}

  /**
   * @param viewHandler a ViewHandler variable for control over the GUI
   * @param model a Model variable for the interface
   * @param root a Region variable for location within the GUI
   *
   * Initializer for the class. The parameters are initialized and table columns are created for the TableView and
   * their contents are obtained through a for loop where the sessions with bookings from the listOfSessions
   * are returned with their details.
   */
  public void init(ViewHandler viewHandler, Model model, Region root){
    this.root=root;
    this.viewHandler=viewHandler;
    this.model=model;

    TableColumn startTime = new TableColumn("Start time");
    startTime.setCellValueFactory(new PropertyValueFactory<>("dateAndStartTime"));
    TableColumn endTime = new TableColumn("End time");
    endTime.setCellValueFactory(new PropertyValueFactory<>("dateAndEndTime"));
    TableColumn room = new TableColumn("Room");
    room.setCellValueFactory(new PropertyValueFactory<>("room"));
    TableColumn sessionNumberCol = new TableColumn("Session");
    sessionNumberCol.setCellValueFactory(new PropertyValueFactory<>("number"));
    TableColumn course = new TableColumn("Course");
    course.setCellValueFactory(new PropertyValueFactory<>("course"));
    TableColumn numberOfLessons = new TableColumn("No. of lessons");
    numberOfLessons.setCellValueFactory(new PropertyValueFactory<>("numberOfLessonsInSession"));
    TableColumn numberOfLessonsForCourse = new TableColumn("No. of lessons for course");
    numberOfLessonsForCourse.setCellValueFactory(new PropertyValueFactory<>("numberOfLessonsForCourse"));
    TableColumn getNumberOfLessonsRemaining = new TableColumn("No. of lessons remaining");
    getNumberOfLessonsRemaining.setCellValueFactory(new PropertyValueFactory<>("getNumberOfLessonsRemaining"));

    bookTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    bookTableView.getColumns().setAll(startTime, endTime, room, sessionNumberCol, course, numberOfLessons, numberOfLessonsForCourse, getNumberOfLessonsRemaining);

    try{
      for(int i=0; i<model.getBookedSessions().size(); i++){

        bookTableView.getItems().add(model.getBookedSessions().get(i));
      }
    }
    catch (Exception e){
      e.printStackTrace();
    }
    filterChoiceBox.setItems(filterChoiceBoxList);
  }

  /**
   * An FXML method called when the button Go Back is pressed. The current window is closed and a new one opens
   * @see BookingSystemController
   */
  @FXML public void goBackButtonRB()
  {
    viewHandler.openView("BookingSystem");
  }

  /**
   * @throws Exception the java.io.PrintWriter requires an exception to be thrown in order to perform operations
   *
   * An FXML method called when the Remove button is pressed. A check is performed regarding if something is being
   * hovered in the GUI. The session selected is then removed and the files are updated
   */
  @FXML public void removePressed() throws Exception
  {
    int index = bookTableView.getSelectionModel().getFocusedIndex();
    if(index > -1)
    {
      Session session= model.getBookedSessions().get(index);
      model.removeSession(session);
      model.writeFiles();
    }
    reset();
  }

  /**
   * Reset method for the class. The columns contents are cleared and updated with the current listOfBookings
   */
  public void reset()
  {
    bookTableView.getItems().clear();
    TableColumn startTime = new TableColumn("Start time");
    startTime.setCellValueFactory(new PropertyValueFactory<>("dateAndStartTime"));
    TableColumn endTime = new TableColumn("End time");
    endTime.setCellValueFactory(new PropertyValueFactory<>("dateAndEndTime"));
    TableColumn room = new TableColumn("Room");
    room.setCellValueFactory(new PropertyValueFactory<>("room"));
    TableColumn sessionNumberCol = new TableColumn("Session");
    sessionNumberCol.setCellValueFactory(new PropertyValueFactory<>("number"));
    TableColumn course = new TableColumn("Course");
    course.setCellValueFactory(new PropertyValueFactory<>("course"));
    TableColumn numberOfLessons = new TableColumn("No. of lessons");
    numberOfLessons.setCellValueFactory(new PropertyValueFactory<>("numberOfLessonsInSession"));
    TableColumn numberOfLessonsForCourse = new TableColumn("No. of lessons for course");
    numberOfLessonsForCourse.setCellValueFactory(new PropertyValueFactory<>("numberOfLessonsForCourse"));
    TableColumn getNumberOfLessonsRemaining = new TableColumn("No. of lessons remaining");
    getNumberOfLessonsRemaining.setCellValueFactory(new PropertyValueFactory<>("getNumberOfLessonsRemaining"));

    bookTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    bookTableView.getColumns().setAll(startTime, endTime, room, sessionNumberCol, course, numberOfLessons, numberOfLessonsForCourse, getNumberOfLessonsRemaining);

    try{
      for(int i=0; i<model.getBookedSessions().size(); i++){

        bookTableView.getItems().add(model.getBookedSessions().get(i));
      }
    }
    catch (Exception e){
      e.printStackTrace();
    }
    filterChoiceBox.setItems(filterChoiceBoxList);
  }

  /**
   * @return root
   *
   * The current root is returned
   */
  public Region getRoot(){
    return root;
  }
}