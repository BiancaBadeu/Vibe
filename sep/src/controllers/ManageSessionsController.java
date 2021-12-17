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

/**
 * A class representing the controller of an FXML ManageSessions
 */
public class ManageSessionsController
{
  @FXML TableView tableView;
  private Region root;
  private Model model;
  private ViewHandler viewHandler;

  /**
   * Empty 0 argument constructor
   */
  public ManageSessionsController() {}
  /**
   * @param viewHandler a ViewHandler variable for control over the GUI
   * @param model a Model variable for the interface
   * @param root a Region variable for location within the GUI
   *
   * Initializer method for the GUI window.The parameters are initialized and the columns for the TableView
   * are created. Through a for loop the items for the columns are obtained from a sessionList
   */
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

  /**
   * Reset method for the Class. The contents of the table columns are cleared and replaced with the current
   * unbooked session from the listOfSessions
   */
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

  /**
   * @return root
   * Returns the current root
   */
  public Region getRoot()
  {
    return root;
  }

  /**
   * An FXML method called when the button named Add is pressed.  The current window is closed and a new one opens
   * @see AddSessionController
   */
  @FXML private void toAdd(){
    viewHandler.openView("AddSession");
  }

  /**
   * An FXML method called when the button named Remove is pressed. The current window is closed and a new one opens
   * @see RemoveSessionController
   */
  @FXML private void toRemove(){
    viewHandler.openView("RemoveSession");
  }

  /**
   * An FXML method called when the button named Edit is pressed. The current window is closed and a new one opens
   * @see EditSessionController
   */
  @FXML private void toEdit(){
    viewHandler.openView("EditSession");
  }

  /**
   * An FXML method called when the button named Cancel is pressed. The current window is closed and a new one opens
   */
  @FXML private void toCancel(){
    viewHandler.openView("Start");
  }
}
