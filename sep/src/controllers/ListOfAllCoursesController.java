package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.*;
import view.ViewHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Scanner;

public class ListOfAllCoursesController
{
  private Region root;
  private Model model;
  private ViewHandler viewHandler;

  @FXML TableView courseTable;

  public ListOfAllCoursesController()
  {
  }

  public void init(ViewHandler viewHandler, Model model, Region root)
      throws FileNotFoundException
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;

    TableColumn course = new TableColumn("Course name");
    course.setCellValueFactory(new PropertyValueFactory<>("courseID"));
    TableColumn ectS = new TableColumn("ECTS");
    ectS.setCellValueFactory(new PropertyValueFactory<>("ects"));

    courseTable.getColumns().setAll(course, ectS);

    CourseList courseList = new CourseList();
    TeacherList teacherList = new TeacherList();
    StudentList studentList = new StudentList();

    File file = new File(
        "C:\\Users\\luisd\\IdeaProjects\\SEP1_V2_files\\src\\ourTxt\\courseList.txt");
    Scanner in = new Scanner(file);

    while (in.hasNext())
    {
      int ok = 0;
      String line = in.nextLine();
      String[] splittingline = line.split(";");
      String courseID = splittingline[0].trim();
      int ects = Integer.parseInt(splittingline[1].trim());
      //teacherList
      for (int i = 0; i < courseList.getAllCoursesAsArrayList().size(); i++)
      {
        if (courseID.equals(courseList.getAllCoursesAsArrayList().get(i).getCourseID()))
        {
          int j = 2;
          while (j < splittingline.length)
          {
            String teacherID = splittingline[j].trim();
            j++;
            Teacher teacher = teacherList.getTeacherByID(teacherID);
            courseList.getAllCoursesAsArrayList().get(i).addTeacher(teacher);
          }
          ok = 1;
        }
      }
      if (ok == 0)
      //studentList
      {
        Course course1 = new Course(courseID, ects);
        StudentList courseSutdents = new StudentList();
        int j = 2;
        while (j < splittingline.length && !splittingline[j].trim().equals(""))
        {
          int studentId = Integer.parseInt(splittingline[j].trim());
          j++;
          Student student = studentList.getStudentByID(studentId);
          courseSutdents.addStudent(student);
        }
        course1.setStudentList(courseSutdents);
        courseList.addCourse(course1);
      }
    }
    }
    private boolean booleanconfirmation () {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("The student has been added to the selected course.");
    Optional<ButtonType> result = alert.showAndWait();
    return (result.isPresent()) && (result.get() == ButtonType.OK);
  }
    private boolean booleanconfirmationfalse () {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("The student was already in the selected course.");
    Optional<ButtonType> result = alert.showAndWait();
    return (result.isPresent()) && (result.get() == ButtonType.OK);
  }

    @FXML public void addStudentPressed () {
    int index = courseTable.getSelectionModel().getFocusedIndex();
    if (index > -1)
    {
      Course course = model.getAllCoursesAsArrayList().get(index);
      if (!(course.getStudentList().getAllStudentsAsArrayList().contains(AddStudentController.student)))
      {
        course.addStudent(AddStudentController.student);
        booleanconfirmation();
      }
      else
        booleanconfirmationfalse();
    }
  }

    @FXML public void donePressed () {
    viewHandler.openView("ManageStudentsAndTeachers");
  }

    @FXML public void filterPressed () {
  }

    public void reset () {
  }

    public Region getRoot () {
    return root;
  }
  }


