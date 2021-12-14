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

  public AddTeacherController(){}

  public void init(ViewHandler viewHandler, model.Model model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
  }

  @FXML void addTeacherButtonPressed() throws IOException
  {
    File file = new File("C:\\Users\\luisd\\IdeaProjects\\SEP1_V2_files\\src\\ourTxt\\teacherList.txt");

    Scanner in = new Scanner(file);
    TeacherList listOfTeachers= new TeacherList();

    in.nextLine();

    while (in.hasNext())
    {


      String line = in.nextLine();



      String[] splittingline = line.split(",");
      String nameTeacher = splittingline[0].trim();
      String teacherID = splittingline[1].trim();

      Teacher teacher = new Teacher(nameTeacher, teacherID);
      listOfTeachers.addTeacher(teacher);
    }




    Teacher newTeacher= new Teacher(nameField.getText(), idField.getText());
    listOfTeachers.addTeacher(newTeacher);
    model.addTeacher(newTeacher);
    try {
      PrintWriter myWriter = new PrintWriter("C:\\Users\\luisd\\IdeaProjects\\SEP1_V2_files\\src\\ourTxt\\teacherList.txt");


      for (int i=0; i<model.getAllTeachersAsArrayList().size(); i++){

        myWriter.print(model.getAllTeachersAsArrayList().get(i));

      }
      myWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }


    viewHandler.openView("ManageStudentsAndTeachers");
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
