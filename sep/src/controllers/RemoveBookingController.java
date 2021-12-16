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

public class RemoveBookingController
{

  @FXML TableView bookTableView;
  @FXML ChoiceBox filterChoiceBox;

  ObservableList<String> filterChoiceBoxList= FXCollections.observableArrayList("TeacherID", "Class", "Day", "Week");

  Model model;
  ViewHandler viewHandler;
  Region root;

  public RemoveBookingController(){}

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

  @FXML public void goBackButtonRB()
  {
    viewHandler.openView("BookingSystem");
  }

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

  public Region getRoot(){
    return root;
  }
}