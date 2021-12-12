package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.*;
import view.*;

public class AddSessionController
{
  @FXML private TableView<Session> tableView;
  @FXML private TextField courseName;
  @FXML private TextField sessionNumber;
  @FXML private TextField lessonNumber;
  @FXML private Label errorLabel;
  private Region root;
  private model.Model model;
  private view.ViewHandler viewHandler;


  public AddSessionController()
  {
  }
  public void init(view.ViewHandler viewHandler, model.Model model, Region root){
    this.viewHandler= viewHandler;
    this.model= model;
    this.root= root;
    tableView.setItems(observableList);
  }
  ObservableList<Session> observableList= FXCollections.observableArrayList(
      new SessionList().getAllSessions());

  public void reset()
  {
    courseName.setText("");
    sessionNumber.setText("");
    lessonNumber.setText("");
  }
  public Region getRoot()
  {
    return root;
  }
  @FXML private void pressToAdd()
  {
    try{
      model.validateAddSession(courseName.getText(), sessionNumber.getText(), lessonNumber.getText());
      errorLabel.setText("");
    }
    catch (Exception e)
    {
      errorLabel.setText(e.getMessage());
    }
    int sessionId = Integer.parseInt(sessionNumber.getText());
    int lessons = Integer.parseInt(lessonNumber.getText());
    String courseId = courseName.getText();
    Course course = new CourseList().getCourseByID(courseId);
    //String toInsert = courseId + "Session"+ sessionId+ lessons;
    Session session= new Session(sessionId, course, lessons);
    new SessionList().addSession(session);
    tableView.getItems().add(session);
  }
  @FXML private void pressToCancel()
  {
    viewHandler.openView("manageSession");
  }
}

