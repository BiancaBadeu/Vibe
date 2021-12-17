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

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Scanner;

/**
 * A class representing the controller of an FXML file called AddSession @see view.AddSession.fxml
 */
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

  /**
   * A 0 argument empty constructor
   */
  public AddSessionController()
  {
  }

  /**
   * @param viewHandler a ViewHandler variable for control over the GUI
   * @param model a Model variable for the interface
   * @param root a Region variable for location within the GUI
   *
   * An initializer for the controller to create table columns for a table and add items to it. The initializer is called
   * when the window is opened
   */
  public void init(view.ViewHandler viewHandler, model.Model model, Region root){
    this.viewHandler= viewHandler;
    this.model= model;
    this.root= root;

    TableColumn numbers = new TableColumn("Number");
    numbers.setCellValueFactory(new PropertyValueFactory<>("number"));
    TableColumn course = new TableColumn("Course");
    course.setCellValueFactory(new PropertyValueFactory<>("course"));
    TableColumn numbersOfLessons = new TableColumn("No. of lessons");
    numbersOfLessons.setCellValueFactory(new PropertyValueFactory<>("numberOfLessonsInSession"));
    TableColumn numbersOfLessonsForCourse = new TableColumn("No. of lessons for course");
    numbersOfLessonsForCourse.setCellValueFactory(new PropertyValueFactory<>("numberOfLessonsForCourse"));
    TableColumn getNumbersOfLessonsRemaining = new TableColumn("No. of lessons remaining");
    getNumbersOfLessonsRemaining.setCellValueFactory(new PropertyValueFactory<>("getNumberOfLessonsRemaining"));

    tableView.getColumns().setAll(numbers,course, numbersOfLessons, numbersOfLessonsForCourse, getNumbersOfLessonsRemaining);
    try
    {
      for (int i = 0; i < model.getUnbookedSessions().size(); i++)
      {
          tableView.getItems().add(model.getUnbookedSessions().get(i));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  /**
   * A method to reset the TextFields when used. It also clears the previous values
   * given to the table columns and updates them ith the new ones.
   */
  public void reset()
  {
    courseName.setText("");
    sessionNumber.setText("");
    lessonNumber.setText("");

    tableView.getItems().clear();
    TableColumn numbers = new TableColumn("Number");
    numbers.setCellValueFactory(new PropertyValueFactory<>("number"));
    TableColumn course = new TableColumn("Course");
    course.setCellValueFactory(new PropertyValueFactory<>("course"));
    TableColumn numbersOfLessons = new TableColumn("No. of lessons");
    numbersOfLessons.setCellValueFactory(new PropertyValueFactory<>("numberOfLessonsInSession"));
    TableColumn numbersOfLessonsForCourse = new TableColumn("No. of lessons for course");
    numbersOfLessonsForCourse.setCellValueFactory(new PropertyValueFactory<>("numberOfLessonsForCourse"));
    TableColumn getNumbersOfLessonsRemaining = new TableColumn("No. of lessons remaining");
    getNumbersOfLessonsRemaining.setCellValueFactory(new PropertyValueFactory<>("getNumberOfLessonsRemaining"));

    tableView.getColumns().setAll(numbers,course, numbersOfLessons, numbersOfLessonsForCourse, getNumbersOfLessonsRemaining);
    try
    {
      for (int i = 0; i < model.getUnbookedSessions().size(); i++)
      {
        tableView.getItems().add(model.getUnbookedSessions().get(i));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

  }

  /**
   * @return root the root
   *
   * A method to return the current root
   */
  public Region getRoot()
  {
    return root;
  }

  /**
   * @throws Exception the usage of java.io.PrintWriter requires an exception to be thrown to perform operations
   *
   * An FXML method that when the button with the action #pressToAdd is pressed, this method calls the
   * validateAddSession in the model. If the latter method throws an exception, the FXML method catches it
   * and set the error label's text to the exception's message. If the error label's text is empty,
   * a session is created based on variables obtained through GUI input, added to the system, an the txt files are updated.
   * Disregarding the error label's text, it calls the reset method.
   */
  @FXML private void pressToAdd() throws Exception
  {
    try
    {
      model.validateAddSession(courseName.getText(),sessionNumber.getText(),lessonNumber.getText());
      errorLabel.setText("");
    }
    catch (Exception e)
    {
      errorLabel.setText(e.getMessage());
    }
    if (errorLabel.getText().equals(""))
    {
      String sessionField = sessionNumber.getText();
      String courseField = courseName.getText();
      String lessonField = lessonNumber.getText();

      Session session = new Session(Integer.parseInt(sessionField), model.getCourseByID(courseField), Integer.parseInt(lessonField));
      model.addSession(session);
      tableView.getItems().clear();
      model.writeFiles();
    }
    reset();
  }

  /**
   * An FXML method that when a button named Cancel is pressed, the window closes and the GUI
   * is taken to a window called ManageSessions
   * @see ManageSessionsController
   */
  @FXML private void pressToCancel()
  {
    viewHandler.openView("ManageSessions");
  }
}