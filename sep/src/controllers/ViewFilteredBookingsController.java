package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.Model;
import view.ViewHandler;

public class ViewFilteredBookingsController
{
  private Region root;
  private Model model;
  private ViewHandler viewHandler;
  @FXML TableView tableMyBookings;
  @FXML ChoiceBox filterChoiceFilteredBox;

  ObservableList<String> filterChoiceFilteredBoxList= FXCollections
      .observableArrayList("TeacherID", "Class", "Day", "Week");



  public ViewFilteredBookingsController(){}

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

    tableMyBookings.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    tableMyBookings.getColumns().setAll(startTime, endTime, room, sessionNumberCol, course, numberOfLessons, numberOfLessonsForCourse, getNumberOfLessonsRemaining);

    try{
      for(int i=0; i<model.getBookedSessions().size(); i++){

        tableMyBookings.getItems().add(model.getBookedSessions().get(i));
      }
    }
    catch (Exception e){
      e.printStackTrace();
    }
    //filterChoiceFilteredBox.setItems(filterChoiceFilteredBoxList);
  }

@FXML public void backPressed()
{
  viewHandler.openView("BookingSystem");
}

  public void reset()
  {
    tableMyBookings.getItems().clear();
    tableMyBookings.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    try{
      for(int i=0; i<model.getBookedSessions().size(); i++){

        tableMyBookings.getItems().add(model.getBookedSessions().get(i));
      }
    }
    catch (Exception e){
      e.printStackTrace();
    }
    //filterChoiceFilteredBox.setItems(filterChoiceFilteredBoxList);
  }
  public Region getRoot()
  {
    return root;
  }
}
