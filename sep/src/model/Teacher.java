package model;

public class Teacher
{
  private String name;
  private String id;

  public Teacher(String name, String id)
  {
    this.name = name;
    this.id = id;
  }

  public String getName()
  {
    return name;
  }
  public String getId()
  {
    return id;
  }

  public String toString()
  {
      return "Name: " + this.name + ", model.Teacher ID: " + this.id;
  }

  public boolean equals(Object obj)
  {
    if(!(obj instanceof Teacher))
      return false;
    Teacher other = (Teacher) obj;
    return id.equals(other.id) && name.equals(other.name);
  }
}