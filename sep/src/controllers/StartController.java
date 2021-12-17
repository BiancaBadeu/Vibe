package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;
import model.Student;
import view.ViewHandler;

import java.util.Optional;

/**
 * A class representing the controller for an FXML Start
 */
public class StartController
{
    private Region root;
    private model.Model model;
    private view.ViewHandler viewHandler;

    /**
     * An Empty 0 argument constructor
     */
    public StartController(){}

    /**
     * @param viewHandler a ViewHandler variable for control over the GUI
     * @param model a Model variable for the interface
     * @param root a Region variable for location within the GUI
     *
     * An initializer method for the class. The parameters are initialized
     */
    public void init(ViewHandler viewHandler, model.Model model, Region root)
    {
        this.viewHandler = viewHandler;
        this.model = model;
        this.root = root;
    }

    /**
     * An FXML method called when the button Go to the booking system is pressed. The current window is closed and a new
     * one opens
     * @see BookingSystemController
     */
    @FXML public void goToTheBookingSystemPressed()
    {
        viewHandler.openView("BookingSystem");
    }

    /**
     * An FXML method called when the button View timetable is pressed. The current window is closed and a new one opens
     * @see TimetableController
     */
    @FXML public void viewTimetablePressed()
    {
        viewHandler.openView("Timetable");
    }

    /**
     * An FXML method called when the button Manage students and teachers is pressed. The current window is closed and a
     * new one opens
     * @see ManageStudentsAndTeachersController
     */
    @FXML public void manageStudentsAndTeachersPressed()
    {
        viewHandler.openView("ManageStudentsAndTeachers");
    }

    /**
     * An FXML method called when the button Manage sessions is pressed. The current window is closed and a
     * new one opens
     * @see ManageSessionsController
     */
    @FXML public void manageSessionsPressed()
    {
        viewHandler.openView("ManageSessions");
    }

    /**
     * @throws Exception java.util.Scanner requires an exception to be thrown in order to perform operations
     *
     * An FXML method called when a button Input files from head of department is pressed. If the confirmation
     * is true the model reads the files
     * @see model.Model
     * {@link #booleanconfirmation()}
     *
     */
    @FXML public void filesPressed() throws Exception
    {
        if (booleanconfirmation())
            model.inputFiles();
    }

    /**
     * @return a check if the Alert is present and if the ok button of the Alert has been pressed
     *
     * A boolean confirmation method that creates an alert
     */
    private boolean booleanconfirmation()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(
            "Are you sure you want to overwrite the existing files with the ones from the head of departament. All your progress will be lost!");
        Optional<ButtonType> result = alert.showAndWait();
        return (result.isPresent()) && (result.get() == ButtonType.OK);
    }

    /**
     * Empty reset method for the class
     */
    public void reset()
    {
    }

    /**
     * @return root
     * Returns the current root
     */
    public Region getRoot()
    {
        return root;
    }
}
