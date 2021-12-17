package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.*;
import view.ViewHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Watchable;
import java.util.Optional;
import java.util.Scanner;

/**
 * A class representing the controller of an FXML file ListOfAllCourses
 */
public class ListOfAllCoursesController
{
  private Region root;
  private Model model;
  private ViewHandler viewHandler;

  @FXML TableView courseTable;

  /**
   * An empty 0 argument constructor
   */
  public ListOfAllCoursesController()
  {
  }

  /**
   * @throws FileNotFoundException java.Util.Scanner requires an exception to be thrown in order to perform operations
   * @param viewHandler a ViewHandler variable for control over the GUI
   * @param model a Model variable for the interface
   * @param root a Region variable for location within the GUI
   *
   * The parameters are initialized and table columns are created for the TableView
   * Through a for loop, items from the courseList are added to the columns
   */
  public void init(ViewHandler viewHandler, Model model, Region root)
      throws FileNotFoundException
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;

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
   * @return the check if the ok button in the Alert has been pressed and if the Alert is no longer present
   *
   * A boolean confirmation for an Alert. If the mentioned check is correct, it is then used in the addStudentsPressed()
   * {@link #addStudentPressed()}
   */
  private boolean booleanconfirmation () {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("The student has been added to the selected course.");
    Optional<ButtonType> result = alert.showAndWait();
    return (result.isPresent()) && (result.get() == ButtonType.OK);
  }

  /**
   * @return the check if the ok button in the Alert has been pressed and if the Alert is no longer present
   *
   * A boolean confirmation in the case that the student was already in the course when being added
   */
  private boolean booleanconfirmationfalse () {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("The student was already in the selected course.");
    Optional<ButtonType> result = alert.showAndWait();
    return (result.isPresent()) && (result.get() == ButtonType.OK);
  }

  /**
   * An FXML method called when the button named Add Student is pressed. A check is performed regarding the if the student
   * is already in the courseList. Boolean confirmations are called in the case to add the student to confirm the action.
   * {@link #booleanconfirmation()}
   */
  @FXML public void addStudentPressed ()
  {
    int index = courseTable.getSelectionModel().getFocusedIndex();
    if (index > -1)
    {
      Course course = model.getAllCoursesAsArrayList().get(index);
      if (!(course.getStudentList().getAllStudentsAsArrayList().contains(AddStudentController.student)))
      {
        if(booleanconfirmation())
          course.addStudent(AddStudentController.student);
      }
      else
        booleanconfirmationfalse();
    }
  }

  /**
   * @throws Exception java.io.PrintWriter requires an exception to be thrown in order to perform operations
   *
   * An FXML method called when the button Done is pressed. What follows is the student being added to the studentList
   * of said course and files being written accordingly with these changes. The current window is closed and a new one
   * opens
   * @see ManageStudentsAndTeachersController
   */
  @FXML public void donePressed () throws Exception
  {
    model.addStudent(AddStudentController.student);
    model.writeFiles();
    viewHandler.openView("ManageStudentsAndTeachers");
  }

  /**
   * An FXML method that would perform a filter action when pressed
   * The filter would be by name or by number of ECTS by checking the courses properties in the list
   */
  @FXML public void filterPressed () {
  }

  /**
   * Empty reset method for the class
   */
  public void reset () {
  }

  /**
   * @return root
   *
   * Returns the current root
   */
  public Region getRoot () {
    return root;
  }
}


