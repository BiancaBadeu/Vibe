package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;
import model.*;
import view.ViewHandler;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.util.Optional;

public class RemoveStudentController
{
  //check remove student is an alert in a function that returns a boolean

  private view.ViewHandler viewHandler;
  private Region root;
  private model.Model model;
  @FXML private TextField idField;
  @FXML private Label errorLabel;


  public RemoveStudentController(){}

    public void init(ViewHandler viewHandler, model.Model model, Region root )
    {
      this.viewHandler = viewHandler;
      this.model = model;
      this.root = root;
    }

    @FXML private void keyTyped()
    {
      try
      {
        model.validateRemoveStudent(idField.getText());
        errorLabel.setText("");
      }
      catch (Exception e)
      {
        errorLabel.setText(e.getMessage());
      }
    }

    @FXML void removeButtonPressed()
    {
      int id = Integer.parseInt(idField.getText());
      model.removeStudentByID(id);
    }

    @FXML void goBackButtonPressed()
    {
      viewHandler.openView("ManageStudentsAndTeachers");
    }

    private boolean booleanconfirmation()
    {
      int id = Integer.parseInt(idField.getText());
      Student student = model.getAllStudents().getStudentByID(id);

      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmation");
      alert.setHeaderText(
          "Removing student: "+ student);
      Optional<ButtonType> result = alert.showAndWait();
      return (result.isPresent()) && (result.get() == ButtonType.OK);
    }

  public void reset()
  {
    idField.setText("");
  }

  public Region getRoot()
  {
    return root;
  }
}

