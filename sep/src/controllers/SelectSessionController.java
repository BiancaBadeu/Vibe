package controllers;


import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import model.*;
import view.ViewHandler;

public class SelectSessionController
{
  @FXML TableView SessionTable;

  private Region root;
  private Model model;
  private ViewHandler viewHandler;

  static Session session;

  public SelectSessionController(){}

  public void init(ViewHandler viewHandler, Model model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;


    TableColumn numbers = new TableColumn("Number");
    numbers.setCellValueFactory(new PropertyValueFactory<>("number"));
    TableColumn numbersOfLessons = new TableColumn("No. of lessons");
    numbersOfLessons.setCellValueFactory(new PropertyValueFactory<>("numberOfLessonsInSession"));
    TableColumn numbersOfLessonsForCourse = new TableColumn("No. of lessons for course");
    numbersOfLessonsForCourse.setCellValueFactory(new PropertyValueFactory<>("numberOfLessonsForCourse"));
    TableColumn getNumbersOfLessonsRemaining = new TableColumn("No. of lessons remaining");
    getNumbersOfLessonsRemaining.setCellValueFactory(new PropertyValueFactory<>("getNumberOfLessonsRemaining"));


    SessionTable.getColumns().setAll(numbers, numbersOfLessons, numbersOfLessonsForCourse, getNumbersOfLessonsRemaining);
    try
    {
      for (int i = 0; i < model.getUnbookedSessionsForCourse(SelectCourseController.course).size(); i++)
      {
          SessionTable.getItems().add(model.getUnbookedSessionsForCourse(SelectCourseController.course).get(i));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  @FXML public void sessionContinuePressed()
  {
    int index = SessionTable.getSelectionModel().getFocusedIndex();
    if(index > -1)
    {
      session = model.getUnbookedSessionsForCourse(SelectCourseController.course).get(index);
      viewHandler.openView("BookARoom");
    }
  }

  @FXML public void sessionGoBackPressed()
  {
    viewHandler.openView("SelectCourse");
  }
  public void reset() {

    SessionTable.getItems().clear();
    for (int i = 0; i < model.getUnbookedSessionsForCourse(SelectCourseController.course).size(); i++)
    {
        SessionTable.getItems().add(model.getUnbookedSessionsForCourse(SelectCourseController.course).get(i));
    }
  }
  public Region getRoot()
  {
    return root;
  }
}
