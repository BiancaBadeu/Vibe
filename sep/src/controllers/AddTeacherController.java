package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;
import model.Student;
import model.StudentList;
import model.Teacher;
import model.TeacherList;
import view.ViewHandler;

import java.io.*;
import java.util.Optional;
import java.util.Scanner;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * A class representing a controller for an AddTeacher FXML file @see view.AddTeacher.fxml
 */
public class AddTeacherController
{
  private view.ViewHandler viewHandler;
  private model.Model model;
  private Region root;
  @FXML private TextField nameField;
  @FXML private TextField idField;
  @FXML private Label errorLabel;

  /**
   * A 0 argument empty constructor
   */
  public AddTeacherController()
  {
  }
  /**
   * @param viewHandler a ViewHandler variable for control over the GUI
   * @param model a Model variable for the interface
   * @param root a Region variable for location within the GUI
   *
   * A method to initialize the GUI window and initialize the parameters given.
   */
  public void init(ViewHandler viewHandler, model.Model model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
  }

  /**
   * @throws Exception the import java.io.PrintWriter requires an exception to be thrown in order to perform operation
   *
   * An FXML method that when the button with the action #addTeacherButtonPressed is pressed,
   * the information in the TextFields is stored.
   * This method calls the validateAddTeacher in the model. If the latter method throws an exception,
   * the FXML method catches it and set the error label's text to the exception's message.
   * If the error label's text is empty, and the confirmation method returns a true,
   * it creates a teacher, adds it to the system, the txt files are updated, and it opens another GUI window.
   * Disregarding the error label's text, it calls the reset method.
   */
  @FXML void addTeacherButtonPressed() throws Exception
  {
    String name = nameField.getText();
    String id = idField.getText();
    try
    {
      model.validateAddTeacher(name, id);
      errorLabel.setText("");
    }
    catch (Exception e)
    {
      errorLabel.setText(e.getMessage());
    }
    if (errorLabel.getText().equals(""))
      if (booleanconfirmation())
      {
        Teacher teacher = new Teacher(name, id);
        model.addTeacher(teacher);
        model.writeFiles();
        errorLabel.setText("");
        viewHandler.openView("ManageStudentsAndTeachers");
      }
    reset();

  }

  /**
   * @return the result of a check if the result is null and the result of the button ok being pressed is Ok
   *
   * A method to get the result of a confirmation from an Alert variable that i displayed in the GUI
   */
  private boolean booleanconfirmation()
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(
        "Adding teacher: "+ nameField.getText() + ", " + idField.getText() );
    Optional<ButtonType> result = alert.showAndWait();
    return (result.isPresent()) && (result.get() == ButtonType.OK);
  }

  /**
   * An FXML method that when a button named Cancel is pressed the current window is closed
   * and the view changes to the one in ManageStudentsAndTeachers
   * @see ManageStudentsAndTeachersController
   */
  @FXML void cancelButtonPressed()
  {
    viewHandler.openView("ManageStudentsAndTeachers");
  }

  /**
   * A method when used resets the TextFields.
   */
  public void reset()
  {
    nameField.setText("");
    idField.setText("");
  }

  /**
   * @return root the root
   *
   * A getter that returns the current root
   */
  public Region getRoot()
  {
    return root;
  }
}