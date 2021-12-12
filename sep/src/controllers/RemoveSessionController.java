package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import model.*;

public class RemoveSessionController
{
  @FXML private TableView<Session> tableView;
  @FXML private ChoiceBox<Course> choiceBox;
  private Region root;
  private model.Model model;
  private view.ViewHandler viewHandler;

  ObservableList<Course> displayChoiceBox = (ObservableList<Course>) FXCollections
      .observableArrayList(new CourseList().getAllCoursesAsArrayList());
  ObservableList<Session> observableList= FXCollections.observableArrayList(
      new SessionList().getAllSessions());
  public RemoveSessionController()
  {

  }
  public void init(view.ViewHandler viewHandler, model.Model model, Region root, TableView<Session> tableView){
    this.viewHandler= viewHandler;
    this.model= model;
    this.root= root;
    tableView.setItems(observableList);
    choiceBox.setItems(displayChoiceBox);
    choiceBox.setValue(null);
  }

  public Region getRoot()
  {
    return root;
  }

  @FXML private void pressToRemove()
  {
    int sessionId = Integer.parseInt(sessionNumber.getText());
    int lessons = Integer.parseInt(lessonNumber.getText());
    String courseId = courseName.getText();
    Course course = new CourseList().getCourseByID(courseId);

    Session selected = tableView.getSelectionModel().getSelectedItem();
    new SessionList().removeSession(selected);
    tableView.getItems().remove(selected);
  }
  @FXML private void pressToCancel()
  {
    viewHandler.openView("manageSession");
  }

  private boolean confirmation()
  {
    int index = tableView.getSelectionModel().getSelectedIndex();
    Session selected= tableView.getSelectionModel().getSelectedItem();
    if (index < 0 || index >= tableView.getItems().size())
    {
      return false;
    }
    Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Removing session {" + selected.getCourseProperty().get() + ": "+ selectedItem.getGradeProperty().get() + "}");
    Optional<ButtonType> result = alert.showAndWait();
    return (result.isPresent())&&(result.get()==ButtonType.OK);
  }
}

   
