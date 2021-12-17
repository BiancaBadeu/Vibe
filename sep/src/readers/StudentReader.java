package readers;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import model.*;

/**
 * A Class representing a reader for students
 */
public class StudentReader
{
  private StudentList listOfStudents;

  /**
   * A 0 argument constructor that initializes the listOfStudents variable of type StudentList
   */
  public StudentReader()
  {
    this.listOfStudents = new StudentList();
  }

  /**
   * @return listOfStudents a list of students
   *
   * A method returning the variable listOfStudents of type StudentList
   */
  public StudentList getListOfStudents()
  {
    return listOfStudents;
  }

  /**
   * @throws Exception the import java.util.Scanner requires an exception to be thrown in order to perform operations
   *
   * The method performs a scan of an specified text file and obtains information. Such information is stored in variables
   * of type Student that are then added to the studentList
   */
  public void readStudents() throws Exception
  {
    /* file variable is created with the data from the text file */
    File file = new File("src\\txt\\Students.txt");

    Scanner in = new Scanner(file);

    while (in.hasNext())
    {

      String line = in.nextLine();
      String[] splittingline = line.split(",");
      int semester = Integer.parseInt(splittingline[0].trim());
      String classletter = splittingline[1].trim();
      int studentId= Integer.parseInt(splittingline[2].trim());
      String studentName= splittingline[3].trim();

      String classId= semester+classletter;

      Student student= new Student(studentName, studentId, classId);
      listOfStudents.addStudent(student);
    }

  }

}