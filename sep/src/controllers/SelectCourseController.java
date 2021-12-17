package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import model.*;
import view.ViewHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A class representing an FXML file SelectCourse
 */
public class SelectCourseController
{
  @FXML TableView courseTable;
  @FXML ChoiceBox drop;
  @FXML TextField filterBy;
  @FXML Button filter;

  ObservableList<String> displayChoice = FXCollections.observableArrayList("Class", "Name");

  private Region root;
  private Model model;
  private ViewHandler viewHandler;

  static Course course;

  /**
   * An Empty 0 argument constructor
   */
  public SelectCourseController(){}

  /**
   * @throws FileNotFoundException import java.io.PrintWriter requires an exception to be thrown in order to perform operations
   * @param viewHandler a ViewHandler variable for control over the GUI
   * @param model a Model variable for the interface
   * @param root a Region variable for location within the GUI
   *
   * Initializer for the class. The parameters are initialized as well as the ChoiceBox items and the table columns for
   * the TableView. The contents for the columns are obtained from a loop that gets the data of a sessionList
   */
  public void init(ViewHandler viewHandler, Model model, Region root)
      throws FileNotFoundException
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;

    drop.setItems(displayChoice);
    drop.setValue("-");

    TableColumn course = new TableColumn("Course name");
    course.setCellValueFactory(new PropertyValueFactory<>("courseID"));
    TableColumn ectS= new TableColumn("ECTS");
    ectS.setCellValueFactory(new PropertyValueFactory<>("ects"));

    courseTable.getColumns().setAll(course, ectS);
    try
    {
      for (int i = 0; i < model.getAllCoursesAsArrayList().size(); i++)
      {
          courseTable.getItems().add(model.getAllCoursesAsArrayList().get(i));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  /**
   * An FXML method called when the button Continue is pressed. The course hovered when the button is pressed
   * is set to be the equal of a course variable. The current window is closed and a new one opens
   * @see SelectSessionController
   */
  @FXML public void courseContinuePressed()
  {
    int index = courseTable.getSelectionModel().getFocusedIndex();
    if(index > -1)
    {
      course = model.getAllCoursesAsArrayList().get(index);
      viewHandler.openView("SelectSession");
    }
  }

  /**
   * An FXML method called when the button Go Back is pressed. The current window is closed and a new one opens
   * @see BookingSystemController
   */
  @FXML public void courseGoBackPressed()
  {
      viewHandler.openView("BookingSystem");
  }

  /**
   * Reset method for the class. The values for the filters, table columns and ChoiceBox are reset. The columns are
   * updated with the current listOfCourses information
   */
  public void reset()
  {
      drop.setValue("-");
      filterBy.setText("");
    courseTable.getItems().clear();
    for(int i=0;i<model.getAllCoursesAsArrayList().size();i++)
    {
      courseTable.getItems().add(model.getAllCoursesAsArrayList().get(i));
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
