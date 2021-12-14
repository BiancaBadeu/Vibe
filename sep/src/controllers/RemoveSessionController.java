package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.*;
import view.ViewHandler;

import java.util.Optional;

public class RemoveSessionController
{
  @FXML  TableView tableView;
  @FXML  ChoiceBox choiceBox;
  private Region root;
  private Model model;
  private view.ViewHandler viewHandler;

  static Session session;
  public String[] getCourses()
  {
    String[] courses = new String[200];
    for(int i=0;i<model.getAllCoursesAsArrayList().size();i++)
    {
      courses[i] = model.getAllCoursesAsArrayList().get(i).getCourseID();
    }
    return courses;
  }

  //ObservableList<String> displayChoiceBox = FXCollections.observableArrayList(getCourses());


  public RemoveSessionController() {}
  public void init(ViewHandler viewHandler, Model model, Region root){
    this.viewHandler= viewHandler;
    this.model= model;
    this.root= root;


    TableColumn numbers = new TableColumn("Number");
    numbers.setCellValueFactory(new PropertyValueFactory<>("number"));
    TableColumn numbersOfLessons = new TableColumn("No. of lessons");
    numbersOfLessons.setCellValueFactory(new PropertyValueFactory<>("numberOfLessons"));
    TableColumn numbersOfLessonsForCourse = new TableColumn("No. of lessons for course");
    numbersOfLessonsForCourse.setCellValueFactory(new PropertyValueFactory<>("numberOfLessonsForCourse"));
    TableColumn getNumbersOfLessonsRemaining = new TableColumn("No. of lessons remaining");
    getNumbersOfLessonsRemaining.setCellValueFactory(new PropertyValueFactory<>("getNumberOfLessonsRemaining"));

    tableView.getColumns().setAll(numbers, numbersOfLessons, numbersOfLessonsForCourse, getNumbersOfLessonsRemaining);
    try
    {
      for (int i = 0; i < model.getAllSessionsAsArrayList().size(); i++)
      {
          tableView.getItems().add(model.getAllSessionsAsArrayList().get(i));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    //choiceBox.setItems(displayChoiceBox);
    //choiceBox.setValue(model.getAllCoursesAsArrayList().get(0));
  }

  @FXML private void pressToRemove()
  {
    int index = tableView.getSelectionModel().getFocusedIndex();
    if(index > -1)
    {
      session= model.getAllSessionsAsArrayList().get(index);
      if(confirmation())
        model.getAllSessions().removeSession(session);
    }

    tableView.getItems().remove(session);
  }
  @FXML private void pressToCancel()
  {
    viewHandler.openView("ManageSessions");
  }

  private boolean confirmation()
  {
    Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Removing session {" + session.getNumber() + ": "+ session.getCourse() + "}");
    Optional<ButtonType> result = alert.showAndWait();
    return (result.isPresent())&&(result.get()==ButtonType.OK);
  }

  public void reset() {}

  public Region getRoot()
  {
    return root;
  }

}
