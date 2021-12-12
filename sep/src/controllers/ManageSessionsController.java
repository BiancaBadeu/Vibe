package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import model.*;


public class ManageSessionsController
{
  @FXML private TableView<Session> tableView;
  private Region root;
  private model.Model model;
  private view.ViewHandler viewHandler;
 ObservableList<Session> observableList= FXCollections.observableArrayList(
      new SessionList().getAllSessions());
  
  public ManageSessionsController()
  {
  }
  public void init(view.ViewHandler viewHandler, model.Model model, Region root){
    this.viewHandler= viewHandler;
    this.model= model;
    this.root= root;
    tableView.setItems(observableList);
  }
  public void reset(){}

  public Region getRoot()
  {
    return root;
  }
  @FXML private void toAdd(){
    viewHandler.openView("AddSession");
  }
  @FXML private void toRemove(){
    viewHandler.openView("RemoveSession");
  }
  @FXML private void toEdit(){
    viewHandler.openView("EditSession");
  }
  @FXML private void toCancel(){
    viewHandler.openView("Start");
  }
}

