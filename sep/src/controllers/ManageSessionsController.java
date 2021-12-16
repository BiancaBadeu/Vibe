package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.*;
import view.ViewHandler;


public class ManageSessionsController
{
  @FXML TableView tableView;
  private Region root;
  private Model model;
  private ViewHandler viewHandler;

  public ManageSessionsController() {}
  public void init(ViewHandler viewHandler, Model model, Region root){
    this.viewHandler= viewHandler;
    this.model= model;
    this.root= root;

    TableColumn numbers = new TableColumn("Number");
    numbers.setCellValueFactory(new PropertyValueFactory<>("number"));
    TableColumn course = new TableColumn("Course");
    course.setCellValueFactory(new PropertyValueFactory<>("course"));
    TableColumn numbersOfLessons = new TableColumn("No. of lessons");
    numbersOfLessons.setCellValueFactory(new PropertyValueFactory<>("numberOfLessonsInSession"));
    TableColumn numbersOfLessonsForCourse = new TableColumn("No. of lessons for course");
    numbersOfLessonsForCourse.setCellValueFactory(new PropertyValueFactory<>("numberOfLessonsForCourse"));
    TableColumn getNumbersOfLessonsRemaining = new TableColumn("No. of lessons remaining");
    getNumbersOfLessonsRemaining.setCellValueFactory(new PropertyValueFactory<>("getNumberOfLessonsRemaining"));

    tableView.getColumns().setAll(numbers, course, numbersOfLessons, numbersOfLessonsForCourse, getNumbersOfLessonsRemaining);
    try
    {
      for (int i = 0; i < model.getUnbookedSessions().size(); i++)
      {
          tableView.getItems().add(model.getUnbookedSessions().get(i));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  public void reset()
  {
    tableView.getItems().clear();
    TableColumn numbers = new TableColumn("Number");
    numbers.setCellValueFactory(new PropertyValueFactory<>("number"));
    TableColumn course = new TableColumn("Course");
    course.setCellValueFactory(new PropertyValueFactory<>("course"));
    TableColumn numbersOfLessons = new TableColumn("No. of lessons");
    numbersOfLessons.setCellValueFactory(new PropertyValueFactory<>("numberOfLessonsInSession"));
    TableColumn numbersOfLessonsForCourse = new TableColumn("No. of lessons for course");
    numbersOfLessonsForCourse.setCellValueFactory(new PropertyValueFactory<>("numberOfLessonsForCourse"));
    TableColumn getNumbersOfLessonsRemaining = new TableColumn("No. of lessons remaining");
    getNumbersOfLessonsRemaining.setCellValueFactory(new PropertyValueFactory<>("getNumberOfLessonsRemaining"));

    tableView.getColumns().setAll(numbers, course, numbersOfLessons, numbersOfLessonsForCourse, getNumbersOfLessonsRemaining);
    try
    {
      for (int i = 0; i < model.getUnbookedSessions().size(); i++)
      {
        tableView.getItems().add(model.getUnbookedSessions().get(i));
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
  @FXML private void toAdd(){
    viewHandler.openView("AddSession");
  }
  @FXML private void toRemove(){
    viewHandler.openView("RemoveSession");
  }
  @FXML private void toEdit(){
    viewHandler.openView("EditSession");
  }
  @FXML private void toCancel(){
    viewHandler.openView("Start");
  }
}
