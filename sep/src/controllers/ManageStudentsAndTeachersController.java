package controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import view.ViewHandler;
import model.Model;



public class ManageStudentsAndTeachersController
{
  private Region root;
  private Model model;
  private ViewHandler viewHandler;

  public ManageStudentsAndTeachersController()
  {
  }

  public void init(ViewHandler viewHandler, Model model, Region root)
  {
    this.model = model;
    this.viewHandler = viewHandler;
    this.root = root;
  }

  @FXML public void addStudentButtonPressed()
  {
    viewHandler.openView("AddStudent");
  }

  @FXML public void removeStudentButtonPressed()
  {
    viewHandler.openView("RemoveStudent");
  }

  @FXML public void addTeacherButtonPressed()
  {
    viewHandler.openView("AddTeacher");
  }

  @FXML public void removeTeacherButtonPressed()
  {
    viewHandler.openView("RemoveTeacher");
  }

  public void reset()
  {
  }

  public Region getRoot()
  {
    return root;
  }

}
