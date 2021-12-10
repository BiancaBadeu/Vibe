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

  public SelectCourseController(){}

  public void init(ViewHandler viewHandler, Model model, Region root)
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

  @FXML public void courseContinuePressed()
  {
    int index = courseTable.getSelectionModel().getFocusedIndex();
    Course course = model.getAllCoursesAsArrayList().get(index);


    viewHandler.openView("SelectSession");
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
