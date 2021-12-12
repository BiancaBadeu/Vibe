package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.Course;
import model.Model;
import view.ViewHandler;

public class ListOfAllCoursesController
{
  private Region root;
  private Model model;
  private ViewHandler viewHandler;

  @FXML TableView courseTable;

  public ListOfAllCoursesController(){}

  public void init(ViewHandler viewHandler, Model model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;

    TableColumn course = new TableColumn("Course name");
    course.setCellValueFactory(new PropertyValueFactory<>("courseID"));
    TableColumn ectS= new TableColumn("ECTS");
    ectS.setCellValueFactory(new PropertyValueFactory<>("ects"));

    courseTable.getColumns().setAll(course, ectS);
    try
    {
      for (int i = 0; i < model.getAllCoursesAsArrayList().size(); i++)
      {
        courseTable.getItems().add(model.getAllCoursesAsArrayList().get(i));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  @FXML public void addStudentPressed()
  {
    int index = courseTable.getSelectionModel().getFocusedIndex();
    if(index > -1)
    {
      Course course = model.getAllCoursesAsArrayList().get(index);
      if(!(course.getStudentList().getAllStudentsAsArrayList().contains(AddStudentController.student)))
        course.addStudent(AddStudentController.student);
    }
  }

  @FXML public void donePressed()
  {
    viewHandler.openView("ManageStudentsAndTeachers");
  }

  @FXML public void filterPressed(){}

  public void reset(){}

  public Region getRoot()
  {
    return root;
  }
}
