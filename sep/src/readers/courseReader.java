package fileReaders;


import view.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class courseReader
{

  public static void main(String[] args) throws Exception
  {
    /* file variable is created with the data from the text file */
    File file = new File("C:\\Users\\luisd\\Downloads\\Courses.txt");

    Scanner in = new Scanner(file);
    ArrayList<Course> listOfCourses = new ArrayList<Course>();


    while (in.hasNext())
    {

      String line = in.nextLine();
      String[] splittingline = line.split(",");
      int semester = Integer.parseInt(splittingline[0].trim());
      String course = splittingline[2].trim();
      String classletter = splittingline[1].trim();
      String teacherid = splittingline[3].trim();
      int ectsvalue = Integer.parseInt(splittingline[4].trim());
      String courseId = course + semester + classletter;
      TeacherList teacherList = new TeacherList();
      StudentList studentList = new StudentList();

      int ok = 1;
      Teacher teacher = teacherList.getTeacherByID(teacherid);
      for (int i = 0; i < listOfCourses.size(); i++)
        if (courseId.equals(listOfCourses.get(i).getCourseID()))
        {
          listOfCourses.get(i).addTeacher(teacher);
          ok = 0;
        }
      if (ok == 1)
      {
        Course course1 = new Course(courseId, ectsvalue);
        course1.setStudentList(studentList);
        course1.addTeacher(teacher);
        listOfCourses.add(course1);
      }
    }
    System.out.println(listOfCourses);
  }

  }

