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

public class EditSessionController
{

  @FXML  TableView tableView;
  @FXML  ChoiceBox<Course> choiceBox;
  @FXML  TextField newLessons;
  @FXML  Label errorLabel;
  private Region root;
  private model.Model model;
  private view.ViewHandler viewHandler;

  static Session session;

  ObservableList<Course> displayChoiceBox = (ObservableList<Course>) FXCollections.observableArrayList(
      new CourseList().getAllCoursesAsArrayList());

  public EditSessionController() {}

  public void init(ViewHandler viewHandler, Model model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;

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

    choiceBox.setItems(displayChoiceBox);
    choiceBox.setValue(null);
  }
  public void reset(){}

  public Region getRoot()
  {
    return root;
  }

  @FXML private void pressToEdit()
  {
    int index = tableView.getSelectionModel().getFocusedIndex();
    if(index > -1)
    {
      session= model.getAllSessionsAsArrayList().get(index);
    }
    try{
      model.validateEditSession(newLessons.getText());
      errorLabel.setText("");
    }
    catch (Exception e)
    {
      errorLabel.setText(e.getMessage());
    }
    int lessonNumbers = Integer.parseInt(newLessons.getText());
    Session editedSession = new Session(session.getNumber(),
        session.getCourse(), lessonNumbers);
    tableView.getItems().remove(session);
    tableView.getItems().add(editedSession);
    new SessionList().removeSession(session);
    new SessionList().addSession(editedSession);
  }

  @FXML private void pressToCancel()
  {
    viewHandler.openView("ManageSessions");
  }

  private boolean confirmation()
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(
        "Edit number of lesson for session" + session.getNumber() + ", of the course " + session.getCourse() + "?");
    Optional<ButtonType> result = alert.showAndWait();
    return (result.isPresent()) && (result.get() == ButtonType.OK);
  }
}