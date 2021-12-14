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

public class RemoveStudentController
{
  //check remove student is an alert in a function that returns a boolean

  private view.ViewHandler viewHandler;
  private Region root;
  private model.Model model;
  @FXML private TextField idField;
  @FXML private Label errorLabel;

  public RemoveStudentController()
  {
  }

  public void init(ViewHandler viewHandler, model.Model model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
  }

  @FXML void removeButtonPressed()
  {
    int id = Integer.parseInt(idField.getText());

    Scanner in = new Scanner(
        "C:\\Users\\luisd\\IdeaProjects\\SEP1_V2_files\\src\\ourTxt\\studentList.txt");

    StudentList listOfStudents = new StudentList();

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
        model.removeStudentByID(id);

        try
        {
          PrintWriter myWriter = new PrintWriter(
              "C:\\Users\\luisd\\IdeaProjects\\SEP1_V2_files\\src\\ourTxt\\studentList.txt");

          for (int i = 0;
               i < model.getAllStudentsAsArrayList().size(); i++)
          {

            myWriter.print(model.getAllStudentsAsArrayList().get(i));

          }
          myWriter.close();
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }

      reset();

    }
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
    alert.setHeaderText("Removing student: " + student);
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