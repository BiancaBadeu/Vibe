package view;

import controllers.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import model.*;

public class ViewHandeler
{
  private Scene currentScene;
  private Stage primaryStage;
  private Model model;
  private AddSessionController addSessionController;
  private AddStudentController addStudentController;
  private AddTeacherController addTeacherController;
  private BookARoomController bookARoomController;
  private BookingFormatController bookingFormatController;
  private BookingSystemController bookingSystemController;
  private CheckAddStudentController checkAddStudentController;
  private EditSessionController editSessionController;
  private EditSessionEnterNumberOfLessonsController editSessionEnterNumberOfLessonsController;
  private FinishBookingController finishBookingController;
  private ListOfAllCoursesController listOfAllCoursesController;
  private ManageSessionsController manageSessionsController;
  private ManageStudentsAndTeachersController manageStudentsAndTeachersController;
  private RemoveBookingController removeBookingController;
  private RemoveSessionController removeSessionController;
  private RemoveStudentController removeStudentController;
  private RemoveTeacherController removeTeacherController;
  private SelectCourseController selectCourseController;
  private SelectRoomController selectRoomController;
  private SelectSessionController selectSessionController;
  private StartController startController;
  private TimetableController timetableController;
  private ViewBookingsByController viewBookingsByController;
  private ViewFilteredBookingsController viewFilteredBookingsController;

  public ViewHandeler(Model model)
  {
    this.model = model;
    this.currentScene = new Scene(new Region());
  }
  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
    openView("Start");
  }
  public void openView(String id)
  {
    Region root;
    switch (id)
    {
      case "AddSession":
        root = loadAddSessionView("AddSession.fxml");
        break;
      case "AddStudent":
        root = loadAddStudentView("AddStudent.fxml");
        break;
      case "AddTeacher":
        root = loadAddTeacherView("AddTeacher.fxml");
        break;
      case "BookARoom":
        root = loadBookARoomView("BookARoom.fxml");
        break;
      case "BookingFormat":
        root = loadBookingFormatView("BookingFormat.fxml");
        break;
      case "BookingSystem":
        root = loadBookingSystemView("BookingSystem.fxml");
        break;
      case "CheckAddStudent":
        root = loadCheckAddStudentView("CheckAddStudent.fxml");
        break;
      case "EditSession":
        root = loadEditSessionView("EditSession.fxml");
        break;
      case "EditSessionEnterNumberOfLessons":
        root = loadEditSessionEnterNumberOfLessonsView(
            "EditSessionEnterNumberOfLessons");
        break;
      case "FinishBooking":
        root = loadFinishBookingView("FinishBooking.fxml");
        break;
      case "ListOfAllCourses":
        root = loadListOfAllCoursesView("ListOfAllCourses.fxml");
        break;
      case "ManageSession":
        root = loadManageSessionView("ManageSession.fxml");
        break;
      case "ManageStudentsAndTeachers":
        root = loadManageStudentsAndTeachersView(
            "ManageStudentsAndTeachers.fxml");
        break;
      case "RemoveBooking":
        root = loadRemoveBookingView("RemoveBooking.fxml");
        break;
      case "RemoveSession":
        root = loadRemoveSessionView("RemoveSession.fxml");
        break;
      case "RemoveStudent":
        root = loadRemoveStudentView("RemoveStudent.fxml");
        break;
      case "RemoveTeacher":
        root = loadRemoveTeacherView("RemoveTeacher.fxml");
        break;
      case "SelectCourse":
        root = loadSelectCourseView("SelectCourse.fxml");
        break;
      case "SelectRoom":
        root = loadSelectRoomView("SelectRoom.fxml");
        break;
      case "SelectSession":
        root = loadSelectSessionView("SelectSession.fxml");
        break;
      case "Start":
        root = loadStartView("Start.fxml");
        break;
      case "Timetable":
        root = loadTimetableView("Timetable.fxml");
        break;
      case "ViewBookingsBy":
        root = loadViewBookingsByView("ViewBookingBy.fxml");
        break;
      case "VieFilteredBookings":
        root = loadViewFilteredBookingsView("ViewFilteredBookings.fxml");
        break;
      default:
        root = loadStartView("Start.fxml");
        break;
    }

    currentScene.setRoot(root);
    String title = "";
    if(root.getUserData() != null)
    {
      title += root.getUserData();
    }
    primaryStage.setTitle(title);
    primaryStage.setScene(currentScene);
    primaryStage.setWidth(root.getPrefWidth());
    primaryStage.setHeight(root.getPrefHeight());
    primaryStage.show();
  }

  public void closeView()
  {
    primaryStage.close();
  }

  private Region loadAddSessionView(String fxmlFile)
  {
    if(addSessionController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        addSessionController = loader.getController();
        addSessionController.init(this, model, root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      addSessionController.reset();
    }
    return addSessionController.getRoot();
  }
  private Region loadAddStudentView(String fxmlFile)
  {
    if(addStudentController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        addStudentController = loader.getController();
        addStudentController.init(this, model, root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      addStudentController.reset();
    }
    return addStudentController.getRoot();
  }
  private Region loadAddTeacherView(String fxmlFile)
  {
    if(addTeacherController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        addTeacherController = loader.getController();
        addTeacherController.init(this, model, root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      addTeacherController.reset();
    }
    return addTeacherController.getRoot();
  }
  private Region loadBookARoomView(String fxmlFile)
  {
    if(bookARoomController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        bookARoomController = loader.getController();
        bookARoomController.init(this, model, root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      bookARoomController.reset();
    }
    return bookARoomController.getRoot();
  }
  private Region loadBookingFormatView(String fxmlFile)
  {
    if(bookingFormatController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        bookingFormatController = loader.getController();
        bookingFormatController.init(this, model, root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      bookingFormatController.reset();
    }
    return bookingFormatController.getRoot();
  }
  private Region loadBookingSystemView(String fxmlFile)
  {
    if(bookingSystemController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        bookingSystemController = loader.getController();
        bookingSystemController.init(this, model, root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      bookingSystemController.reset();
    }
    return bookingSystemController.getRoot();
  }
  private Region loadCheckAddStudentView(String fxmlFile)
  {
    if(checkAddStudentController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        checkAddStudentController = loader.getController();
        checkAddStudentController.init(this, model, root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      checkAddStudentController.reset();
    }
    return checkAddStudentController.getRoot();
  }
  private Region loadEditSessionView(String fxmlFile)
  {
    if(editSessionController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        editSessionController = loader.getController();
        editSessionController.init(this, model, root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      editSessionController.reset();
    }
    return editSessionController.getRoot();
  }
  private Region loadEditSessionEnterNumberOfLessonsView(String fxmlFile)
  {
    if(editSessionEnterNumberOfLessonsController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        editSessionEnterNumberOfLessonsController = loader.getController();
        editSessionEnterNumberOfLessonsController.init(this, model, root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      editSessionEnterNumberOfLessonsController.reset();
    }
    return editSessionEnterNumberOfLessonsController.getRoot();
  }
  private Region loadFinishBookingView(String fxmlFile)
  {
    if(finishBookingController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        finishBookingController = loader.getController();
        finishBookingController.init(this, model, root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      finishBookingController.reset();
    }
    return finishBookingController.getRoot();
  }
  private Region loadListOfAllCoursesView(String fxmlFile)
  {
    if(listOfAllCoursesController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        listOfAllCoursesController = loader.getController();
        listOfAllCoursesController.init(this, model, root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      listOfAllCoursesController.reset();
    }
    return listOfAllCoursesController.getRoot();
  }
  private Region loadManageSessionView(String fxmlFile)
  {
    if(manageSessionsController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        manageSessionsController = loader.getController();
        manageSessionsController.init(this, model, root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      manageSessionsController.reset();
    }
    return manageSessionsController.getRoot();
  }
  private Region loadManageStudentsAndTeachersView(String fxmlFile)
  {
    if(manageStudentsAndTeachersController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        manageStudentsAndTeachersController = loader.getController();
        manageStudentsAndTeachersController.init(this, model, root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      manageStudentsAndTeachersController.reset();
    }
    return manageStudentsAndTeachersController.getRoot();
  }
  private Region loadRemoveBookingView(String fxmlFile)
  {
    if(removeBookingController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        removeBookingController = loader.getController();
        removeBookingController.init(this, model, root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      removeBookingController.reset();
    }
    return removeBookingController.getRoot();
  }
  private Region loadRemoveSessionView(String fxmlFile)
  {
    if(removeSessionController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        removeSessionController = loader.getController();
        removeSessionController.init(this, model, root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      removeSessionController.reset();
    }
    return removeSessionController.getRoot();
  }
  private Region loadRemoveStudentView(String fxmlFile)
  {
    if(removeStudentController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        removeStudentController = loader.getController();
        removeStudentController.init(this, model, root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      removeStudentController.reset();
    }
    return removeStudentController.getRoot();
  }
  private Region loadRemoveTeacherView(String fxmlFile)
  {
    if(removeTeacherController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        removeTeacherController = loader.getController();
        removeTeacherController.init(this, model, root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      removeTeacherController.reset();
    }
    return removeTeacherController.getRoot();
  }
  private Region loadSelectCourseView(String fxmlFile)
  {
    if(selectCourseController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        selectCourseController = loader.getController();
        selectCourseController.init(this, model, root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      selectCourseController.reset();
    }
    return selectCourseController.getRoot();
  }
  private Region loadSelectRoomView(String fxmlFile)
  {
    if(selectRoomController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        selectRoomController = loader.getController();
        selectRoomController.init(this, model, root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      selectRoomController.reset();
    }
    return selectRoomController.getRoot();
  }
  private Region loadSelectSessionView(String fxmlFile)
  {
    if(selectSessionController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        selectSessionController = loader.getController();
        selectSessionController.init(this, model, root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      selectSessionController.reset();
    }
    return selectSessionController.getRoot();
  }
  private Region loadStartView(String fxmlFile)
  {
    if(startController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        startController = loader.getController();
        startController.init(this, model, root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      startController.reset();
    }
    return startController.getRoot();
  }
  private Region loadTimetableView(String fxmlFile)
  {
    if(timetableController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        timetableController = loader.getController();
        timetableController.init(this, model, root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      timetableController.reset();
    }
    return timetableController.getRoot();
  }
  private Region loadViewBookingsByView(String fxmlFile)
  {
    if(viewBookingsByController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        viewBookingsByController = loader.getController();
        viewBookingsByController.init(this, model, root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      viewBookingsByController.reset();
    }
    return viewBookingsByController.getRoot();
  }
  private Region loadViewFilteredBookingsView(String fxmlFile)
  {
    if(viewFilteredBookingsController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        viewFilteredBookingsController = loader.getController();
        viewFilteredBookingsController.init(this, model, root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      viewFilteredBookingsController.reset();
    }
    return viewFilteredBookingsController.getRoot();
  }

}
