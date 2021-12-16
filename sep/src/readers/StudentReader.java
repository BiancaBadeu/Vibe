package readers;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import model.*;

public class StudentReader
{
  private StudentList listOfStudents;

  public StudentReader()
  {
    this.listOfStudents = new StudentList();
  }
  public StudentList getListOfStudents()
  {
    return listOfStudents;
  }

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