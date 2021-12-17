package model;

import java.util.ArrayList;

/**
 * A class representing a list of teachers
 */
public class TeacherList
{
  private ArrayList<Teacher> teachers;

  /**
   * A 0 argument constructor that initializes all instance variables
   */
  public TeacherList()
  {
    teachers = new ArrayList<>();
  }

  /**
   * @param teacher new teacher to be added
   *
   * The method adds the teacher to the list of teachers
   */
  public void addTeacher(Teacher teacher)
  {
    teachers.add(teacher);
  }

  /**
   * @param id teacher's id
   *
   * The teacher with the id is removed from the list of teachers
   */
  public void removeTeacherByID(String id)
  {
    for (int i = 0; i < teachers.size(); i++)
    {
      if (id.equals(teachers.get(i).getId()))
        teachers.remove(teachers.get(i));
    }
  }

  /**
   * @return all teachers in a teacherList
   */
  public TeacherList getAllTeachers()
  {
    TeacherList teacherList = new TeacherList();
    for (int i = 0; i < teachers.size(); i++)
    {
      teacherList.addTeacher(teachers.get(i));
    }
    return teacherList;
  }

  /**
   * @return all teachers in a teacherList as a Teacher arrayList
   */
  public ArrayList<Teacher> getAllTeachersAsArrayList()
  {
    return teachers;
  }

  /**
   * @param id teacher's id
   * @return teacher in a teacherList that has the ID
   */
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

  /**
   * @param name teacher's name
   * @return teacher in a teacherList that has the name
   */
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

  /**
   * @param nameField teacher's name
   * @param idField teacher's id
   *
   * The teacher's name cannot contain numbers or symbols
   * The teacher's id cannot contain numbers or symbols and must be uppercase
   */
  public void validateAddTeacher(String nameField, String idField)
  {
    String error = "One of the fields you introduced was not valid. Please introduce a valid teacher.";
    if(idField.length()<3 || idField.length()>4)
      throw new IllegalArgumentException(error);
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

  /**
   * @param idField teacher's id
   *
   * The teacher's id cannot contain numbers or symbols and must be uppercase
   */
  public void validateRemoveTeacher(String idField)
  {
    String error = "Please input a valid teacherID!";

    for (int i = 0; i < idField.length(); i++)
    {
      if (!(idField.charAt(i) >= 'A' && idField.charAt(i) <= 'Z'))
        throw new IllegalArgumentException(error);
    }

  }

  /**
   * @return a teacherList contents
   */
  public String toString()
  {
    return "" + teachers;
  }
}
