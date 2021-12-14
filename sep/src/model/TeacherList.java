package model;

import java.util.ArrayList;

public class TeacherList
{
  private ArrayList<Teacher> teachers;

  public TeacherList()
  {
    teachers = new ArrayList<>();
  }

  public void addTeacher(Teacher teacher)
  {
    teachers.add(teacher);
  }

  public void removeTeacherByID(String id)
  {
    for (int i = 0; i < teachers.size(); i++)
    {
      if (id.equals(teachers.get(i).getId()))
        teachers.remove(teachers.get(i));
    }
  }

  public TeacherList getAllTeachers()
  {
    TeacherList teacherList = new TeacherList();
    for (int i = 0; i < teachers.size(); i++)
    {
      teacherList.addTeacher(teachers.get(i));
    }
    return teacherList;
  }

  public ArrayList<Teacher> getAllTeachersAsArrayList()
  {
    return teachers;
  }

  public Teacher getTeacherByID(String id)
  {
    for (int i = 0; i < teachers.size(); i++)
    {
      if (id.equals(teachers.get(i).getId()))
      {
        return teachers.get(i);
      }
    }
    return null;
  }

  public TeacherList getTeachersByName(String name)
  {
    TeacherList teachersByName = new TeacherList();
    for (int i = 0; i < teachers.size(); i++)
    {
      if (name.equals(teachers.get(i).getName()))
        teachersByName.addTeacher(teachers.get(i));
    }
    return teachersByName;
  }

  public void validateAddTeacher(String nameField, String idField)
  {
    String error = "One of the fields you introduced was not valid. Please introduce a valid teacher.";
    for (int i = 0; i < nameField.length(); i++)
    {
      if (!((nameField.charAt(i) >= 'A' && nameField.charAt(i) <= 'Z') || (
          nameField.charAt(i) >= 'a' && nameField.charAt(i) <= 'z')
          || nameField.charAt(i) == ' '))
        throw new IllegalArgumentException(error);
    }

    for (int i = 0; i < idField.length(); i++)
    {
      if (!(idField.charAt(i) >= 'A' && idField.charAt(i) <= 'Z'))
        throw new IllegalArgumentException(error);
    }
  }

  public void validateRemoveTeacher(String idField)
  {
    String error = "Please input a valid teacherID!";

    for (int i = 0; i < idField.length(); i++)
    {
      if (!(idField.charAt(i) >= 'A' && idField.charAt(i) <= 'Z'))
        throw new IllegalArgumentException(error);
    }

  }

  public String toString()
  {
    return "" + teachers;
  }
}
