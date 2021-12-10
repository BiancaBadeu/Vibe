package controllers;

import javafx.scene.layout.Region;
import model.Model;
import view.ViewHandler;

public class EditSessionEnterNumberOfLessonsController
{
  private Region root;
  private Model model;
  private ViewHandler viewHandler;

  public EditSessionEnterNumberOfLessonsController(){}

  public void init(ViewHandler viewHandler, Model model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
  }

  public void reset(){}

  public Region getRoot()
  {
    return root;
  }
}
