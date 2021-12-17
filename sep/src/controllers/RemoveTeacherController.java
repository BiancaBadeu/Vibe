package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;
import model.*;
import view.ViewHandler;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.Scanner;

/**
 * A class representing an FXML file RemoveTeacher
 */
public class RemoveTeacherController
{

  private view.ViewHandler viewHandler;
  private Region root;
  private model.Model model;
  @FXML private TextField idField;
  @FXML private Label errorLabel;

  /**
   * An Empty 0 argument constructor
   */
  public RemoveTeacherController(){}

  /**
   * @param viewHandler a ViewHandler variable for control over the GUI
   * @param model a Model variable for the interface
   * @param root a Region variable for location within the GUI
   *
   * An initializer for the class. The parameters are initialized
   */
  public void init(ViewHandler viewHandler, model.Model model, Region root )
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
  }

  /**
   * @throws Exception import java.io.PrintWriter requires an exception to be thrown in order to perform operations
   *
   * An FXML method called when a button Remove Teacher is pressed. Using a boolean confirmation and checking the TextField the
   * removal is confirmed. If the check is passed the teacher is removed from the listOfTeachers and the
   * files are updated
   */
  @FXML void removeTeacherButtonPressed() throws Exception
  {

    String id= idField.getText();

    try
    {
      model.validateRemoveTeacher(idField.getText());
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
        model.removeTeacherFromSystemByID(id);
        model.writeFiles();
        viewHandler.openView("ManageStudentsAndTeachers");
        errorLabel.setText("");
      }

    }
    reset();
  }

  /**
   * An FXML method called when Go Back button is pressed. The current window is closed and a new one opens
   * @see ManageStudentsAndTeachersController
   */
  @FXML void goBackButtonPressed()
  {
    viewHandler.openView("ManageStudentsAndTeachers");
  }

  /**
   * @return check if the Alert is present and if the ok button has been pressed
   *
   * A boolean confirmation that creates an Alert that is displayed when the Remove Teacher button is pressed
   * {@link #removeTeacherButtonPressed()}
   *
   */
  private boolean booleanconfirmation()
  {
    Teacher teacher = model.getTeacherByID(idField.getText());

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(
        "Removing teacher: "+ teacher);
    Optional<ButtonType> result = alert.showAndWait();
    return (result.isPresent()) && (result.get() == ButtonType.OK);
  }

  /**
   * Reset method for the class. The id TextField is reset
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
