package controllers;

import javafx.scene.layout.Region;
import model.*;
import readers.Reader;
import view.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

/**
 * A class representing the controller for an FXML CheckAddStudent
 */
public class CheckAddStudentController
{
  private Region root;
  private Model model;
  private ViewHandler viewHandler;
  @FXML private TextField studentField;
  @FXML private TextField coursesField;

  /**
   * A 0 argument empty constructor
   */
  public CheckAddStudentController() {}
  /**
   * @param viewHandler a ViewHandler variable for control over the GUI
   * @param model a Model variable for the interface
   * @param root a Region variable for location within the GUI
   *
   * A method called to start the GUI window by initializing the parameters mentioned and displaying previous input from
   * TextFields and displaying the courses.
   * @see AddStudentController
   */
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
          courses+= model.getAllCoursesAsArrayList().get(i).getCourseID() + "; ";
      }
    }
    coursesField.setText(courses);
  }

  /**
   * @throws Exception the import java.io.PrintWriter requires an exception to be thrown in order to perform writing operations
   *
   * An FXML method that when a button named Yes is pressed, the student previously taken from addStudentController is added
   * to a listOfStudents and written to a file.
   */
  @FXML public void yesButtonPressed() throws Exception
  {
    model.addStudent(AddStudentController.student);
    model.writeFiles();
    for (int i = 0; i < model.getAllCoursesAsArrayList().size(); i++)
    {
      if (AddStudentController.student.getClassID().length() == 2)
      {
        String CID = "" + model.getAllCoursesAsArrayList().get(i).getCourseID().charAt(0)
            + model.getAllCoursesAsArrayList().get(i).getCourseID().charAt(1);
        if (CID.equals(AddStudentController.student.getClassID()))
        {
          model.getAllCoursesAsArrayList().get(i).addStudent(AddStudentController.student);
          model.writeFiles();
          viewHandler.openView("ManageStudentsAndTeachers");
        }
      }
    }
  }

  /**
   * An FXML method called when a button named Go Back is pressed, the current window is closed and a new one opened.
   * @see AddStudentController
   */
  @FXML public void goBackButtonPressed()
  {
    viewHandler.openView("AddStudent");
  }

  /**
   * An FXML method called when a button named Change Courses is pressed, the current window is closed and a new one opened.
   * @see ListOfAllCoursesController
   */
  @FXML public void changeCoursesButtonPressed()
  {
    viewHandler.openView("ListOfAllCourses");
  }

  /**
   * A method that when called the TextFields are reset.
   */
  public void reset()
  {
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
          courses+= model.getAllCoursesAsArrayList().get(i).getCourseID() + "; ";
      }
    }
    coursesField.setText(courses);
  }

  /**
   * @return root the root
   *
   * A getter for the current root
   */
  public Region getRoot()
  {
    return root;
  }


}

