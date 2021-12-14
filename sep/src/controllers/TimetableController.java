package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.Model;
import view.ViewHandler;

public class TimetableController
{
  @FXML TableView timetable;
  private Region root;
  private Model model;
  private ViewHandler viewHandler;

  public TimetableController(){}

  public void init(ViewHandler viewHandler, Model model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;


    TableColumn startTimeColumn = new TableColumn("Start Time");
    startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("dateAndStartTime"));
    TableColumn dateAndEndTimeColumn = new TableColumn("End Time");
    dateAndEndTimeColumn.setCellValueFactory(new PropertyValueFactory<>("dateAndEndTime"));
    TableColumn numbersOfLessonsForCourse = new TableColumn("No. of lessons for course");
    numbersOfLessonsForCourse.setCellValueFactory(new PropertyValueFactory<>("numberOfLessonsForCourse"));
    TableColumn getNumbersOfLessonsRemaining = new TableColumn("No. of lessons remaining");
    getNumbersOfLessonsRemaining.setCellValueFactory(new PropertyValueFactory<>("getNumberOfLessonsRemaining"));



    timetable.getColumns().setAll(startTimeColumn, dateAndEndTimeColumn, numbersOfLessonsForCourse, getNumbersOfLessonsRemaining);

    try
    {
      for (int i = 0; i < model.getBookedSessions().size(); i++)
      {
          timetable.getItems().add(model.getBookedSessions().get(i));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }


  }

  public void reset(){}

  public Region getRoot()
  {
    return root;
  }
}
