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

public class AddTeacherController
{
  private view.ViewHandler viewHandler;
  private model.Model model;
  private Region root;
  @FXML private TextField nameField;
  @FXML private TextField idField;
  @FXML private Label errorLabel;

  public AddTeacherController()
  {
  }

  public void init(ViewHandler viewHandler, model.Model model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
  }

  @FXML void addTeacherButtonPressed() throws Exception
  {
    String name = nameField.getText();
    String id = idField.getText();
    Teacher teacher = new Teacher(name, id);
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
        model.addTeacher(teacher);
        model.writeFiles();
        errorLabel.setText("");
        viewHandler.openView("ManageStudentsAndTeachers");
      }
    reset();

  }


  private boolean booleanconfirmation()
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(
        "Adding teacher: "+ nameField.getText() + ", " + idField.getText() );
    Optional<ButtonType> result = alert.showAndWait();
    return (result.isPresent()) && (result.get() == ButtonType.OK);
  }

  @FXML void cancelButtonPressed()
  {
    viewHandler.openView("ManageStudentsAndTeachers");
  }

  public void reset()
  {
    nameField.setText("");
    idField.setText("");
  }

  public Region getRoot()
  {
    return root;
  }
}