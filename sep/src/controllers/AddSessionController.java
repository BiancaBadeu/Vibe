package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.*;
import view.*;

public class AddSessionController
{
  @FXML TableView tableView;
  @FXML  TextField courseName;
  @FXML  TextField sessionNumber;
  @FXML  TextField lessonNumber;
  @FXML  Label errorLabel;
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

    TableColumn numbers = new TableColumn("Number");
    numbers.setCellValueFactory(new PropertyValueFactory<>("number"));
    TableColumn numbersOfLessons = new TableColumn("No. of lessons");
    numbersOfLessons.setCellValueFactory(new PropertyValueFactory<>("numberOfLessonsInSession"));
    TableColumn numbersOfLessonsForCourse = new TableColumn("No. of lessons for course");
    numbersOfLessonsForCourse.setCellValueFactory(new PropertyValueFactory<>("numberOfLessonsForCourse"));
    TableColumn getNumbersOfLessonsRemaining = new TableColumn("No. of lessons remaining");
    getNumbersOfLessonsRemaining.setCellValueFactory(new PropertyValueFactory<>("getNumberOfLessonsRemaining"));

    tableView.getColumns().setAll(numbers, numbersOfLessons, numbersOfLessonsForCourse, getNumbersOfLessonsRemaining);
    try
    {
      for (int i = 0; i < model.getAllSessionsAsArrayList().size(); i++)
      {
        if(model.getAllSessionsAsArrayList().get(i).getCourse().equals(SelectCourseController.course))
          tableView.getItems().add(model.getAllSessionsAsArrayList().get(i));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

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
  @FXML private void pressToAdd() throws Exception
  {
    try{
      model.validateAddSession(courseName.getText(), sessionNumber.getText(), lessonNumber.getText());
      errorLabel.setText("");
    }
    catch (Exception e)
    {
      errorLabel.setText(e.getMessage());
      e.printStackTrace();
    }
    finally
    {
      int sessionId = Integer.parseInt(sessionNumber.getText());
      int lessons = Integer.parseInt(lessonNumber.getText());
      String courseId = courseName.getText();
      Course course = model.getCourseByID(courseId);
      Session session= new Session(sessionId, course, lessons);
      model.addSession(session);
      model.writeFile("session");
      tableView.getItems().add(session);
    }

  }
  @FXML private void pressToCancel()
  {
    viewHandler.openView("ManageSessions");
  }
}
