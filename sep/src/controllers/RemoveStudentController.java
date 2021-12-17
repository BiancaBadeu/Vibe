package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;
import model.*;
import view.ViewHandler;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.Scanner;

/**
 * A class representing the controller of an FXML RemoveStudent
 */
public class RemoveStudentController
{
  //check remove student is an alert in a function that returns a boolean

  private view.ViewHandler viewHandler;
  private Region root;
  private model.Model model;
  @FXML private TextField idField;
  @FXML private Label errorLabel;

  /**
   * Empty 0 argument constructor
   */
  public RemoveStudentController()
  {
  }

  /**
   * @param viewHandler a ViewHandler variable for control over the GUI
   * @param model a Model variable for the interface
   * @param root a Region variable for location within the GUI
   *
   * Initializer method for the class. The parameters are initialized
   */
  public void init(ViewHandler viewHandler, model.Model model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
  }

  /**
   * @throws Exception import java.io.PrintWriter requires an exception to be thrown in order to perform operations
   *
   * An FXML method that is called when the remove button is pressed. The validation methods in the interface are called
   * If the check is not passed an error label is displayed by setting its text. If the check is passed then the student is
   * removed from the listOfStudents and the files are updated
   */
  @FXML void removeButtonPressed() throws Exception
  {
    int id = Integer.parseInt(idField.getText());

    try
    {
      model.validateRemoveStudent(idField.getText());
      errorLabel.setText("");
    }
    catch (Exception e)
    {
      errorLabel.setText(e.getMessage());
    }
    if (errorLabel.getText().equals(""))
    {
      if (booleanconfirmation())
      {
        model.removeStudentFromSystemByID(id);
        model.writeFiles();
        viewHandler.openView("ManageStudentsAndTeachers");
        errorLabel.setText("");
      }
    }
    reset();
  }

  /**
   * An FXML method called when the button Go Back is pressed. The current window is closed and
   * a new opens
   * @see ManageStudentsAndTeachersController
   */
  @FXML void goBackButtonPressed()
  {
    viewHandler.openView("ManageStudentsAndTeachers");
  }

  /**
   * @return a check if the Alert is still present and the ok button in the alert has been pressed
   *
   * A boolean method that creates an Alert that is displayed to the user and takes an ok or a cancel.
   * {@link #removeButtonPressed()}
   */
  private boolean booleanconfirmation()
  {
    int id = Integer.parseInt(idField.getText());
    Student student = model.getAllStudents().getStudentByID(id);

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Removing student: " + student);
    Optional<ButtonType> result = alert.showAndWait();
    return (result.isPresent()) && (result.get() == ButtonType.OK);
  }

  /**
   * Reset method for the class. The TextField is reset
   */
  public void reset()
  {
    idField.setText("");
  }

  /**
   * @return root
   *
   * Returns the current root
   */
  public Region getRoot()
  {
    return root;
  }
}
