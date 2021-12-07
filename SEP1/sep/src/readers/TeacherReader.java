package readers;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import model.*;

public class TeacherReader
{
  public static void main(String[] args) throws Exception
  {
    /* file variable is created with the data from the text file */
    File file = new File("Teachers.txt");

    Scanner in = new Scanner(file);
    TeacherList teacherList = new TeacherList();
    while (in.hasNext())
    {

      String line = in.nextLine();
      String[] splittingline = line.split(",");
      String teacherName= splittingline[0];
      String teacherId= splittingline[1];

      Teacher teacher= new Teacher(teacherName, teacherId);
      teacherList.addTeacher(teacher);

    }

  }

}