package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.Model;
import view.ViewHandler;

/**
 * A class representing the controller for an FXML Timetable
 */
public class TimetableController
{
  @FXML TableView timetable;
  private Region root;
  private Model model;
  private ViewHandler viewHandler;

  /**
   * Empty 0 argument constructor
   */
  public TimetableController(){}

  /**
   * @param viewHandler a ViewHandler variable for control over the GUI
   * @param model a Model variable for the interface
   * @param root a Region variable for location within the GUI
   *
   * An initializer method for the class. The parameters are initialized and the columns for the TableView are
   * created. Through a for loop the contents of the sessionList that are booked information is obtained and placed
   * in the columns
   */
  public void init(ViewHandler viewHandler, Model model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;


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

    timetable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    timetable.getColumns().setAll(startTime, endTime, room, sessionNumberCol, course, numberOfLessons, numberOfLessonsForCourse, getNumberOfLessonsRemaining);

    try{
      for(int i=0; i<model.getBookedSessions().size(); i++){

        timetable.getItems().add(model.getBookedSessions().get(i));
      }
    }
    catch (Exception e){
      e.printStackTrace();
    }
  }

  /**
   * An FXML method called when the Go Back button is pressed. The current window is closed and a new one opens
   * @see StartController
   */
  @FXML public void backPressed()
  {
    viewHandler.openView("Start");
  }

  /**
   * Reset method for the class. Booked sessions are removed from the column and through a loop the
   * current ones are placed
   */
  public void reset()
  {
    timetable.getItems().clear();
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

    timetable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    timetable.getColumns().setAll(startTime, endTime, room, sessionNumberCol, course, numberOfLessons, numberOfLessonsForCourse, getNumberOfLessonsRemaining);

    try{
      for(int i=0; i<model.getBookedSessions().size(); i++){

        timetable.getItems().add(model.getBookedSessions().get(i));
      }
    }
    catch (Exception e){
      e.printStackTrace();
    }
  }

  /**
   * @return root
   * Returns the current root
   */
  public Region getRoot()
  {
    return root;
  }
}
