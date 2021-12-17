package controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import model.*;
import view.ViewHandler;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

/**
 * A class representing a controller for an FXML file AddStudent @see view.AddStudent.fxml
 */
public class AddStudentController
{
  private Region root;
  private model.Model model;
  private view.ViewHandler viewHandler;
  @FXML private TextField nameField;
  @FXML private TextField idField;
  @FXML private TextField classField;
  @FXML private Label errorLabel;

  static Student student;

  /**
   * A 0 argument empty constructor
   */
  public AddStudentController() {}

  /**
   * @param viewHandler a ViewHandler variable for control over the GUI
   * @param model a Model variable for the interface
   * @param root a Region variable for location within the GUI
   *
   * The method initializes the GUI window by making use of the three parameters previously mentioned.
   */
  public void init(ViewHandler viewHandler, model.Model model, Region root)
  {
    this.model = model;
    this.root = root;
    this.viewHandler = viewHandler;
  }

  /**
   * An FXML method that when the button with the action #addButtonPressed is pressed,
   * a validation is called for the input checking if the name, the id and the class are illegal values.
   * If the test is passed, a student is created and a GUI window is opened.
   * @see CheckAddStudentController
   *
   * If the check is not passed then an error message is displayed.
   *
   * Disregarding the error label's text, it calls the reset method.
   */
  @FXML void addButtonPressed()
  {

    try
    {
      model.validateAddStudent(nameField.getText(), idField.getText(), classField.getText());
      errorLabel.setText("");
    }
    catch (Exception e)
    {
      errorLabel.setText(e.getMessage());

    }
    if(errorLabel.getText().equals(""))
    {
      int id = Integer.parseInt(idField.getText());
      String name = nameField.getText();
      String class1 = classField.getText();
      student = new Student(name, id, class1);
      viewHandler.openView("CheckAddStudent");
    }
    reset();
  }

  /**
   * An FXML method that when the button with the action #cancelButtonPressed is pressed,
   * the current GUI window is closed and another one is opened.
   * @see ManageStudentsAndTeachersController
   */
  @FXML void cancelButtonPressed()
  {
    viewHandler.openView("ManageStudentsAndTeachers");
  }

  /**
   * The method resets the TextFields within the GUI when used
   */
  public void reset()
  {
    nameField.setText("");
    idField.setText("");
    classField.setText("");
  }

  /**
   * @return root the root
   *
   * A method to return the current root
   */
  public Region getRoot()
  {
    return root;
  }

}
