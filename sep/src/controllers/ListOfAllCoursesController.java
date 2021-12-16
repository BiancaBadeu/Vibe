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
        if(booleanconfirmation())
          course.addStudent(AddStudentController.student);
      }
      else
        booleanconfirmationfalse();
    }
  }

    @FXML public void donePressed () throws Exception
    {
    model.addStudent(AddStudentController.student);
    model.writeFiles();
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


