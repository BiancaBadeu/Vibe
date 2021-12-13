package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;
import model.Student;
import view.ViewHandler;

import java.util.Optional;

public class StartController
{
    private Region root;
    private model.Model model;
    private view.ViewHandler viewHandler;

    public StartController(){}

    public void init(ViewHandler viewHandler, model.Model model, Region root)
    {
        this.viewHandler = viewHandler;
        this.model = model;
        this.root = root;
    }

    @FXML public void goToTheBookingSystemPressed()
    {
        viewHandler.openView("BookingSystem");
    }

    @FXML public void viewTimetablePressed()
    {
        viewHandler.openView("Timetable");
    }

    @FXML public void manageStudentsAndTeachersPressed()
    {
        viewHandler.openView("ManageStudentsAndTeachers");
    }

    @FXML public void manageSessionsPressed()
    {
        viewHandler.openView("ManageSessions");
    }

    @FXML public void filesPressed() throws Exception
    {
        if (booleanconfirmation())
            model.inputFiles();
        model.inputFiles();
    }

    private boolean booleanconfirmation()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(
            "Are you sure you want to overwrite the existing files with the ones from the head of department. All your progress will be lost!");
        Optional<ButtonType> result = alert.showAndWait();
        return (result.isPresent()) && (result.get() == ButtonType.OK);
    }

    public void reset()
    {
    }
    public Region getRoot()
    {
        return root;
    }
}
