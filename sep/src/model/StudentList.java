package model;

import java.util.ArrayList;

public class StudentList
{
  private ArrayList<Student> studentList;

  public StudentList()
  {
    this.studentList = new ArrayList<>();
  }

  public int getSize()
  {
    return studentList.size();
  }

  public void addStudent(Student student)
  {
    studentList.add(student);
  }

  public void removeStudentByID(int id)
  {
    for(int i=0;i<studentList.size();i++)
    {
      if(id == studentList.get(i).getStudentID())
        studentList.remove(studentList.get(i));
    }
  }
  public StudentList getAllStudents()
  {
    StudentList students = new StudentList();
    for(int i=0;i<studentList.size();i++)
      students.addStudent(studentList.get(i));
    return students;
  }

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
  public Student getStudentByID(int studentID)
  {
    for(int i=0;i<studentList.size();i++)
    {
      if(studentID == studentList.get(i).getStudentID())
        return studentList.get(i);
    }
    return null;
  }
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

  public void validateAddStudent(String nameField, String idField, String classField)
  {
    String error="One of the fields you introduced was not valid. Please introduce a valid student.";
    for(int i=0;i<nameField.length(); i++)
    {
      if(!((nameField.charAt(i)>='A' && nameField.charAt(i)<='Z' ) ||
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

  public String toString()
  {
    return "List: " + studentList;
  }
}
