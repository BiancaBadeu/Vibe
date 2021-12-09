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
    for(int i=0;i<teachers.size();i++)
    {
      if(id.equals(teachers.get(i).getId()))
        teachers.remove(teachers.get(i));
    }
  }

  public TeacherList getAllTeachers()
  {
    TeacherList teacherList = new TeacherList();
    for(int i=0;i<teachers.size();i++)
    {
      teacherList.addTeacher(teachers.get(i));
    }
    return teacherList;
  }

  public Teacher getTeacherByID(String id)
  {
    for(int i=0;i<teachers.size();i++)
    {
      if(id.equals(teachers.get(i).getId()))
      {
        return teachers.get(i);
      }
    }
    return null;
  }
  public TeacherList getTeachersByName(String name)
  {
    TeacherList teachersByName = new TeacherList();
    for(int i=0;i<teachers.size();i++)
    {
      if(name.equals(teachers.get(i).getName()))
        teachersByName.addTeacher(teachers.get(i));
    }
    return teachersByName;
  }

  public String toString()
  {
    return "Teacher list: " + teachers;
  }
}
