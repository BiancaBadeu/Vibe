package readers;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import model.*;

public class TeacherReader
{
  private TeacherList listOfTeachers;

  public TeacherReader()
  {
    this.listOfTeachers = new TeacherList();
  }

  public TeacherList getTeacherList()
  {
    return listOfTeachers;
  }
  public void readTeachers() throws Exception
  {
    /* file variable is created with the data from the text file */
    File file = new File("src\\txt\\Teachers.txt");

    Scanner in = new Scanner(file);

    while (in.hasNext())
    {

      String line = in.nextLine();
      String[] splittingline = line.split(",");
      String teacherName= splittingline[0].trim();
      String teacherId= splittingline[1].trim();

      Teacher teacher= new Teacher(teacherName, teacherId);
      listOfTeachers.addTeacher(teacher);


    }
  }

}