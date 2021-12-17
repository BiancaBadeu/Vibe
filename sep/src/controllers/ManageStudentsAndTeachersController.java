package controllers;

    import javafx.fxml.FXML;
    import javafx.scene.layout.Region;
    import view.ViewHandler;
    import model.*;

/**
 * A class representing the controller of an FXML ManageStudentsAndTeachers
 */
public class ManageStudentsAndTeachersController
{
  private javafx.scene.layout.Region root;
  private Model model;
  private ViewHandler viewHandler;

  /**
   * Empty 0 argument constructor
   */
  public ManageStudentsAndTeachersController()
  {
  }

  /**
   * @param viewHandler a ViewHandler variable for control over the GUI
   * @param model a Model variable for the interface
   * @param root a Region variable for location within the GUI
   *
   * Initializer method for the class, the parameters are initialized.
   */
  public void init(ViewHandler viewHandler, Model model, Region root)
  {
    this.model = model;
    this.viewHandler = viewHandler;
    this.root = root;
  }

  /**
   * An FXML method called when the button Add Student is pressed. The current window is closed and a new one opens
   * @see AddStudentController
   */
  @FXML public void addStudentButtonPressed()
  {
    viewHandler.openView("AddStudent");
  }

  /**
   * An FXML method called when the button Remove Student is pressed. The current window is closed and a new one opens
   * @see RemoveStudentController
   */
  @FXML public void removeStudentButtonPressed()
  {
    viewHandler.openView("RemoveStudent");
  }

  /**
   * An FXML method called when the button Add Teacher is pressed. The current window is closed and a new one opens
   * @see AddTeacherController
   */
  @FXML public void addTeacherButtonPressed()
  {
    viewHandler.openView("AddTeacher");
  }

  /**
   * An FXML method called when the button Remove Teacher is pressed. The current window is closed and a new one opens
   * @see RemoveTeacherController
   */
  @FXML public void removeTeacherButtonPressed()
  {
    viewHandler.openView("RemoveTeacher");
  }

  /**
   * An FXML method called when the button Go Back is pressed. The current window is closed and a new one opens
   * @see StartController
   */
  @FXML public void goBackButtonPressed()
  {
    viewHandler.openView("Start");
  }

  /**
   * Empty reset method for the class
   */
  public void reset()
  {
  }

  /**
   * @return root
   *
   * Returns the current root
   */
  public Region getRoot()
  {
    return root;
  }

}