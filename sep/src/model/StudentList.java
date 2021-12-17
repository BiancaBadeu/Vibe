package model;

import java.util.ArrayList;

/**
 * A class representing a list of students
 */
public class StudentList
{
  private ArrayList<Student> studentList;

  /**
   * A 0 argument constructor that initializes all instance variables
   */
  public StudentList()
  {
    this.studentList = new ArrayList<>();
  }

  /**
   * @return the amount of students contained in a studentList
   */
  public int getSize()
  {
    return studentList.size();
  }

  /**
   * @param student student to be added
   *
   * The student from the parameter is added to a list of students
   *
   */
  public void addStudent(Student student)
  {
    studentList.add(student);
  }

  /**
   * @param id student's id
   *
   * The student with the student's id parameter is removed from the studentList
   */
  public void removeStudentByID(int id)
  {
    for(int i=0;i<studentList.size();i++)
    {
      if(id == studentList.get(i).getStudentID())
        studentList.remove(studentList.get(i));
    }
  }

  /**
   * @return all students in a studentList
   */
  public StudentList getAllStudents()
  {
    StudentList students = new StudentList();
    for(int i=0;i<studentList.size();i++)
      students.addStudent(studentList.get(i));
    return students;
  }

  /**
   * @return All students in a studentList as an arrayList
   */
  public ArrayList<Student> getAllStudentsAsArrayList()
  {
    return studentList;
  }

  /**
   * @param classID students classID
   * @return all student from the studentList that have the classID
   */
  public StudentList getStudentsByClassID(String classID)
  {
    StudentList studentsByClass = new StudentList();
    for(int i=0;i<studentList.size();i++)
    {
      if(classID.equals(studentList.get(i).getClassID()))
      {
        studentsByClass.addStudent(studentList.get(i));
      }
    }
    return studentsByClass;
  }

  /**
   * @param studentID student's id
   * @return student from the list of students that has the ID
   */
  public Student getStudentByID(int studentID)
  {
    for(int i=0;i<studentList.size();i++)
    {
      if(studentID == studentList.get(i).getStudentID())
        return studentList.get(i);
    }
    return null;
  }

  /**
   * @param name student's name
   * @return student from the studentList that has the name
   */
  public StudentList getStudentsByName(String name)
  {
    StudentList studentsByName = new StudentList();
    for(int i=0;i<studentList.size();i++)
    {
      if(name.equals(studentList.get(i).getName()))
        studentsByName.addStudent(studentList.get(i));
    }
    return studentsByName;
  }

  /**
   * @param nameField student's name
   * @param idField student's id
   * @param classField student's class
   *
   * The student name is checked to see if it contains number or symbols which cannot be
   * The idField is checked to see if the String length is 6 and the first number is between 1-9 and the others
   * are between 0-9
   * The class is checked to see if starts with a number between 1-7 and the rest of the characters are letters
   * with the string having a maximum length of 4
   */
  public void validateAddStudent(String nameField, String idField, String classField)
  {
    String error="One of the fields you introduced was not valid. Please introduce a valid student.";
    for(int i=0;i<nameField.length(); i++)
    {
      if(!((nameField.charAt(i)>='A' && nameField.charAt(i)<='Z' )||
          (nameField.charAt(i)>='a' && nameField.charAt(i)<='z') || nameField.charAt(i)==' '))
        throw new IllegalArgumentException(error);
    }

    if(!(idField.length()==6))
      throw new IllegalArgumentException(error);
    if(!(idField.charAt(0)>'0' && idField.charAt(0)<='9'))
      throw new IllegalArgumentException(error);
    for (int i=1; i<idField.length();i++)
    {
      if(!(idField.charAt(i)>='0' && idField.charAt(i)<='9'))
        throw new IllegalArgumentException(error);
    }

    if(classField.length()<2 || classField.length()>3)
    {
      throw new IllegalArgumentException(error);
    }

    if(!(classField.charAt(0)>='1' && classField.charAt(0)<='7'))
      throw new IllegalArgumentException(error);
    for(int i=1;i<classField.length();i++)
    {
      if(!(classField.charAt(i)>='A' && classField.charAt(i)<='Z'))
      {
        throw new IllegalArgumentException(error);
      }
    }

  }

  /**
   * @param idField student's id
   *
   *  The student name is checked to see if it contains number or symbols which cannot be
   */
  public void validateRemoveStudent(String idField)
  {
    String error = "Please input a valid student ID!";
    if(!(idField.length()==6))
      throw new IllegalArgumentException(error);
    if(!(idField.charAt(0)>'0' && idField.charAt(0)<='9'))
      throw new IllegalArgumentException(error);
    for (int i=1; i<idField.length();i++)
    {
      if(!(idField.charAt(i)>='0' && idField.charAt(i)<='9'))
        throw new IllegalArgumentException(error);
    }
  }

  /**
   * @return studentList that contains students
   */
  public String toString()
  {
    return "" + studentList;
  }
}
