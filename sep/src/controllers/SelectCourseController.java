package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import model.*;
import view.ViewHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SelectCourseController
{
  @FXML TableView courseTable;
  @FXML ChoiceBox drop;
  @FXML TextField filterBy;
  @FXML Button filter;

  ObservableList<String> displayChoice = FXCollections.observableArrayList("Class", "Name");

  private Region root;
  private Model model;
  private ViewHandler viewHandler;

  static Course course;

  public SelectCourseController(){}

  public void init(ViewHandler viewHandler, Model model, Region root)
      throws FileNotFoundException
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;

    drop.setItems(displayChoice);
    drop.setValue("-");

    TableColumn course = new TableColumn("Course name");
    course.setCellValueFactory(new PropertyValueFactory<>("courseID"));
    TableColumn ectS= new TableColumn("ECTS");
    ectS.setCellValueFactory(new PropertyValueFactory<>("ects"));

    courseTable.getColumns().setAll(course, ectS);

    CourseList courseList= new CourseList();
    TeacherList teacherList= new TeacherList();
    StudentList studentList= new StudentList();

    File file = new File(
        "C:\\Users\\luisd\\IdeaProjects\\SEP1_V2_files\\src\\ourTxt\\courseList.txt");
    Scanner in = new Scanner(file);

    while (in.hasNext())
    {
      int ok=0;
      String line = in.nextLine();
      String[] splittingline = line.split(";");
      String courseID = splittingline[0].trim();
      int ects = Integer.parseInt(splittingline[1].trim());
      //teacherList
      for(int i=0;i<courseList.getAllCoursesAsArrayList().size();i++)
      {
        if(courseID.equals(courseList.getAllCoursesAsArrayList().get(i).getCourseID()))
        {
          int j=2;
          while(j<splittingline.length)
          {
            String teacherID = splittingline[j].trim();
            j++;
            Teacher teacher = teacherList.getTeacherByID(teacherID);
            courseList.getAllCoursesAsArrayList().get(i).addTeacher(teacher);
          }
          ok=1;
        }
      }
      if(ok==0)
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


  @FXML public void courseContinuePressed()
  {
    int index = courseTable.getSelectionModel().getFocusedIndex();
    if(index > -1)
    {
      course = model.getAllCoursesAsArrayList().get(index);
      viewHandler.openView("SelectSession");
    }
  }

  @FXML public void courseGoBackPressed()
  {
      viewHandler.openView("BookingSystem");
  }
  public void reset()
  {
      drop.setValue("-");
      filterBy.setText("");
    courseTable.getItems().clear();
    for(int i=0;i<model.getAllCoursesAsArrayList().size();i++)
    {
      courseTable.getItems().add(model.getAllCoursesAsArrayList().get(i));
    }
  }
  public Region getRoot()
  {
    return root;
  }
}
