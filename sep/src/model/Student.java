package model;

public class Student
{
  private String name;
  private int studentID;
  private String classID;

  public Student(String name, int studentID, String classID)
  {
    this.name = name;
    this.studentID = studentID;
    this.classID = classID;
  }

  public String getName()
  {
    return name;
  }
  public int getStudentID()
  {
    return studentID;
  }
  public String getClassID()
  {
    return classID;
  }

  public String toString()
  {
    return "\nName: " + name + ", student ID: " + studentID + ", class: " + classID;
  }

  public boolean equals(Object obj)
  {
    if(!(obj instanceof Student))
      return false;
    Student other = (Student) obj;
    return this.name.equals(other.name) && this.studentID == other.studentID && this.classID.equals(other.classID);
  }
}
