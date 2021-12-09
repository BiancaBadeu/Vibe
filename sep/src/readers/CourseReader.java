package readers;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import model.*;

public class CourseReader
{
  private CourseList listOfCourses;

  public CourseReader()
  {
    this.listOfCourses = new CourseList();
  }
  public CourseList getListOfCourses()
  {
    return listOfCourses;
  }

  public void readCourses() throws Exception
  {
    /* file variable is created with the data from the text file */
    File file = new File("C:\\Elly\\VIA\\Elly Y\\SEP\\SEP1\\sep\\src\\txt\\Courses.txt");

    Scanner in = new Scanner(file);
    TeacherList teacherList;
    StudentList studentList;

    StudentReader studentReader = new StudentReader();
    studentReader.readStudents();
    studentList = studentReader.getListOfStudents();

    TeacherReader teacherReader = new TeacherReader();
    teacherReader.readTeachers();
    teacherList = teacherReader.getTeacherList();

    while (in.hasNext())
    {

      String line = in.nextLine();
      String[] splittingline = line.split(",");

      int semester = Integer.parseInt(splittingline[0].trim());
      String classletter = splittingline[1].trim();
      String course = splittingline[2].trim();
      String teacherid= splittingline[3].trim();
      int ectsvalue= Integer.parseInt(splittingline[4].trim());
      String courseId = semester+classletter+course;
      String classId = semester+classletter;

      int ok=1;
      Teacher teacher = teacherList.getTeacherByID(teacherid);
      for(int i=0;i<listOfCourses.getAllCoursesAsArrayList().size();i++)
        if(courseId.equals(listOfCourses.getAllCoursesAsArrayList().get(i).getCourseID()))
        {
          listOfCourses.getAllCoursesAsArrayList().get(i).addTeacher(teacher);
          ok=0;
        }
      if(ok==1)
      {
        Course course1 = new Course(courseId, ectsvalue);
        course1.setStudentList(studentList.getStudentsByClassID(classId));
        course1.addTeacher(teacher);
        listOfCourses.addCourse(course1);
      }
    }

  }

}
