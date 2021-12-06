package fileReaders;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class studentReader
{

    public static void main(String[] args) throws Exception
    {
        /* file variable is created with the data from the text file */
        File file = new File("C:\\Users\\luisd\\Downloads\\Students.txt");

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
            ArrayList<StudentList> listOfStudents= new ArrayList<StudentList>();
            listOfStudents.addStudent(student);


        }

    }

}

