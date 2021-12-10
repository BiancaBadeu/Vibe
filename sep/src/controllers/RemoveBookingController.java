package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Model;
import view.ViewHandler;

import javafx.scene.layout.Region;

public class RemoveBookingController
{

@FXML TableView tableView;





@FXML ChoiceBox filterChoiceBox;

ObservableList<String> filterChoiceBoxList= FXCollections.observableArrayList("TeacherID", "Class", "Day", "Week");

  Model model;
  ViewHandler viewHandler;
  Region root;

  public RemoveBookingController(){


  }

  public Region getRoot(){
    return root;
  }
  public void reset(){

  }
  public void init(ViewHandler viewHandler, Model model, Region root){
    this.root=root;
    this.viewHandler=viewHandler;
    this.model=model;
    filterChoiceBox.setItems(filterChoiceBoxList);

    TableColumn sessionNumberCol = new TableColumn("Session");
    sessionNumberCol.setCellValueFactory(new PropertyValueFactory<>("number"));
    TableColumn dateCol = new TableColumn("Date");
    dateCol.setCellValueFactory(new PropertyValueFactory<>("dateAndStartTime"));
    TableColumn courseCol = new TableColumn("Course");
    courseCol.setCellValueFactory(new PropertyValueFactory<>("course"));

    tableView.getColumns().setAll(sessionNumberCol, dateCol, courseCol);
    
    try{
      
      for(int i=0; i<model.getAllSessions().getBookedSessions().size(); i++){
        
        tableView.getItems().add(model.getAllSessions().getBookedSessions());
      }
    }
    catch (Exception e){
      e.printStackTrace();
    }
  }


}
