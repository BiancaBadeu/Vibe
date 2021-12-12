package controllers;

import javafx.scene.layout.Region;
import model.Model;
import view.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.awt.*;

public class CheckAddStudentController
{
  private Region root;
  private Model model;
  private ViewHandler viewHandler;
  @FXML private TextField studentField;
  @FXML private TextField coursesField;
  public CheckAddStudentController() {}

  public void init(ViewHandler viewHandler, Model model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    studentField.setText(AddStudentController.student.toString());
    String courses= "";
    for (int i = 0; i < model.getAllCoursesAsArrayList().size(); i++)
    {
      if (AddStudentController.student.getClassID().length() == 2)
      {
        String CID =""+
            model.getAllCoursesAsArrayList().get(i).getCourseID().charAt(0)
            + model.getAllCoursesAsArrayList().get(i).getCourseID()
            .charAt(1);
        if (CID.equals(AddStudentController.student.getClassID()))
          courses+= model.getAllCoursesAsArrayList().get(i).getCourseID() + ", ";
      }
    }
    coursesField.setText(courses);
  }

  @FXML public void yesButtonPressed()
  {
    model.addStudent(AddStudentController.student);
    for (int i = 0; i < model.getAllCoursesAsArrayList().size(); i++)
    {
      if (AddStudentController.student.getClassID().length() == 2)
      {
        String CID =""+
            model.getAllCoursesAsArrayList().get(i).getCourseID().charAt(0)
            + model.getAllCoursesAsArrayList().get(i).getCourseID()
            .charAt(1);
        if (CID.equals(AddStudentController.student.getClassID()))
          model.getAllCoursesAsArrayList().get(i).addStudent(AddStudentController.student);
      }
    }
    viewHandler.openView("ManageStudentsAndTeachers");
  }

  @FXML public void goBackButtonPressed()
  {
    viewHandler.openView("AddStudent");
  }

  @FXML public void changeCoursesButtonPressed()
  {
    viewHandler.openView("ListOfAllCourses");
  }

  public void reset()
  {
    studentField.setText("");
    coursesField.setText("");
  }

  public Region getRoot()
  {
    return root;
  }
}