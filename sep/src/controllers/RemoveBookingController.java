package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Model;
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

  public RemoveBookingController(){
  }

  public Region getRoot(){
    return root;
  }
  public void reset(){

  }
  public void init(ViewHandler viewHandler, Model model, Region root){
    this.root=root;
    this.viewHandler=viewHandler;
    this.model=model;

    filterChoiceBox.setItems(filterChoiceBoxList);

    TableColumn sessionNumberCol = new TableColumn("Session");
    sessionNumberCol.setCellValueFactory(new PropertyValueFactory<>("number"));
    TableColumn course = new TableColumn("Course");
    course.setCellValueFactory(new PropertyValueFactory<>("course"));
    TableColumn numberOfLessons = new TableColumn("No. of lessons");
    numberOfLessons.setCellValueFactory(new PropertyValueFactory<>("numberOfLessonsInSession"));
    TableColumn room = new TableColumn("Room");
    room.setCellValueFactory(new PropertyValueFactory<>("room"));
    TableColumn numberOfLessonsForCourse = new TableColumn("No. of lessons for course");
    numberOfLessonsForCourse.setCellValueFactory(new PropertyValueFactory<>("numberOfLessonsForCourse"));
    TableColumn getNumberOfLessonsRemaining = new TableColumn("No. of lessons remaining");
    getNumberOfLessonsRemaining.setCellValueFactory(new PropertyValueFactory<>("getNumberOfLessonsRemaining"));
    TableColumn startTime = new TableColumn("Start time");
    startTime.setCellValueFactory(new PropertyValueFactory<>("dateAndStartTime"));
    TableColumn endTime = new TableColumn("End time");
    endTime.setCellValueFactory(new PropertyValueFactory<>("dateAndEndTime"));


    bookTableView.getColumns().setAll(sessionNumberCol, course, numberOfLessons, room, numberOfLessonsForCourse, getNumberOfLessonsRemaining, startTime, endTime);

    try{

      for(int i=0; i<model.getAllSessions().getBookedSessions().size(); i++){

        bookTableView.getItems().add(model.getAllSessions().getBookedSessions());
      }
    }
    catch (Exception e){
      e.printStackTrace();
    }
  }


}
