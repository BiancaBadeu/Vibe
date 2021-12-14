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
          courses+= model.getAllCoursesAsArrayList().get(i).getCourseID() + "; ";
      }
    }
    coursesField.setText(courses);
  }

  @FXML public void yesButtonPressed() throws Exception
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

    File file = new File("C:\\Users\\luisd\\IdeaProjects\\SEP1_V2_files\\src\\ourTxt\\studentList.txt");

    Scanner in = new Scanner(file);
    StudentList listOfStudents= new StudentList();

    in.nextLine();

    while (in.hasNext())
    {


      String line = in.nextLine();



      String[] splittingline = line.split(",");
      String nameStudent = splittingline[0].trim();
      int studentIDN = Integer.parseInt(splittingline[1].trim());
      String studentClass=splittingline[2].trim();

      Student student = new Student(nameStudent, studentIDN, studentClass);
      listOfStudents.addStudent(student);
    }




    String[] splittingline2 = studentField.getText().split(",");
    String studentName2= splittingline2[0].trim();
    int studentId2=Integer.parseInt(splittingline2[1].trim());
    String classLetter2=splittingline2[2];

    Student student23= new Student(studentName2, studentId2, classLetter2);
    listOfStudents.addStudent(student23);

    try {
      PrintWriter myWriter = new PrintWriter("C:\\Users\\luisd\\IdeaProjects\\SEP1_V2_files\\src\\ourTxt\\studentList.txt");


      for (int i=0; i<model.getAllStudentsAsArrayList().size(); i++){

        myWriter.print(model.getAllStudentsAsArrayList().get(i));

      }
      myWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
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

