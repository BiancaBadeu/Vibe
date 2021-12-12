package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import model.*;
import view.ViewHandler;

import java.util.Optional;

public class EditSessionController
{

  @FXML private TableView<Session> tableView;
  @FXML private ChoiceBox<Course> choiceBox;
  @FXML private TextField newLessons;
  @FXML private Label errorLabel;
  private Region root;
  private model.Model model;
  private view.ViewHandler viewHandler;

  ObservableList<Course> displayChoiceBox = (ObservableList<Course>) FXCollections.observableArrayList(
      new CourseList().getAllCoursesAsArrayList());
  ObservableList<Session> observableList = FXCollections.observableArrayList(
      new SessionList().getAllSessions());

  public EditSessionController()
  {

  }

  public void init(view.ViewHandler viewHandler, model.Model model, Region root,
      TableView<Session> tableView)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    tableView.setItems(observableList);
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
    try{
      model.validateEditSession(newLessons.getText());
      errorLabel.setText("");
    }
    catch (Exception e)
    {
      errorLabel.setText(e.getMessage());
    }
    int lessonNumbers = Integer.parseInt(newLessons.getText());
    Session selected = tableView.getSelectionModel().getSelectedItem();
    Session editedSession = new Session(selected.getNumber(),
        selected.getCourse(), lessonNumbers);
    tableView.getItems().remove(selected);
    tableView.getItems().add(editedSession);
    new SessionList().removeSession(selected);
    new SessionList().addSession(editedSession);
  }

  @FXML private void pressToCancel()
  {
    viewHandler.openView("manageSession");
  }

  private boolean confirmation()
  {
    int index = tableView.getSelectionModel().getSelectedIndex();
    Session selected = tableView.getSelectionModel().getSelectedItem();
    if (index < 0 || index >= tableView.getItems().size())
    {
      return false;
    }
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(
        "Edit number of lesson for session" + selected.getNumber() + ", of the course " + selected.getCourse() + "?");
    Optional<ButtonType> result = alert.showAndWait();
    return (result.isPresent()) && (result.get() == ButtonType.OK);
  }
}
