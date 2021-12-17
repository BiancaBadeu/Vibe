package readers;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import model.*;

/**
 * A class representing a reader for teachers
 */
public class TeacherReader
{
  private TeacherList listOfTeachers;

  /**
   * A 0 argument constructor that initializes the variable listOfTeachers of type TeacherList
   */
  public TeacherReader()
  {
    this.listOfTeachers = new TeacherList();
  }

  /**
   * @return listOfTeachers a list of teachers
   *
   * A method returning the instance variable listOfTeachers
   */
  public TeacherList getTeacherList()
  {
    return listOfTeachers;
  }

  /**
   * @throws Exception the import java.util.Scanner requires an exception to be thrown in order to perform operations
   *
   * The method performs a scan of an specified text file and obtains information. Such information is then stored in
   * variables and Teacher objects are created with it. Lastly, the Teacher object are added to the listOfTeachers.
   */
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