package controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import model.*;
import view.ViewHandler;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;


public class AddStudentController
{
  private Region root;
  private model.Model model;
  private view.ViewHandler viewHandler;
  static Student student;
  @FXML private TextField nameField;
  @FXML private TextField idField;
  @FXML private TextField classField;
  @FXML private Label errorLabel;

  public AddStudentController()
  {
  }

  public void init(ViewHandler viewHandler, model.Model model, Region root)
  {
    this.model = model;
    this.root = root;
    this.viewHandler = viewHandler;
  }


  @FXML public void addButtonPressed()
  {
    int id = Integer.parseInt(idField.getText());
    String name = nameField.getText();
    String class1 = classField.getText();
    student = new Student(name, id, class1);
    try
    {
      model.validateAddStudent(nameField.getText(), idField.getText(),
          classField.getText());
      errorLabel.setText("");
    }
    catch (Exception e)
    {
      errorLabel.setText(e.getMessage());
    }
    viewHandler.openView("CheckAddStudent");
    reset();
  }

  @FXML public void cancelButtonPressed()
  {
    viewHandler.closeView();
  }

  public void reset()
  {
    nameField.setText("");
    idField.setText("");
    classField.setText("");
  }

  public Region getRoot()
  {
    return root;
  }

}
