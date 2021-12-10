package controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import view.ViewHandler;

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

    public void reset()
    {
    }
    public Region getRoot()
    {
        return root;
    }
}
