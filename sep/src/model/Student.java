package model;

/**
 * A class representing a student
 */
public class Student
{
  private String name;
  private int studentID;
  private String classID;

  /**
   * @param name student's name
   * @param studentID student's ID
   * @param classID class ID
   *
   * 3 argument constructor that initializes instance variables
   */
  public Student(String name, int studentID, String classID)
  {
    this.name = name;
    this.studentID = studentID;
    this.classID = classID;
  }

  /**
   * @return name
   */
  public String getName()
  {
    return name;
  }

  /**
   * @return studentID
   */
  public int getStudentID()
  {
    return studentID;
  }

  /**
   * @return student's ID
   */
  public String getClassID()
  {
    return classID;
  }

  /**
   * @return a string containing the instance variables
   */
  public String toString()
  {
    return "\n" + name + ", " + studentID + ", " + classID;
  }

  /**
   * @param obj other student object
   * @return if a student object is equal to another
   */
  public boolean equals(Object obj)
  {
    if(!(obj instanceof Student))
      return false;
    Student other = (Student) obj;
    return this.name.equals(other.name) && this.studentID == other.studentID && this.classID.equals(other.classID);
  }
}
