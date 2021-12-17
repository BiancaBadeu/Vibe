package readers;

import readers.*;
import model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;
import model.Model;
import model.Manager;

/**
 *Class Representing readers
 *
 */
public class Reader
{
  private TeacherList teacherList;
  private CourseList courseList;
  private StudentList studentList;
  private SessionList sessionList;
  private RoomList roomList;

  /**
   * A 5 argument Constructor containing the list classes variables to initialize them
   *
   *
   * @param studentList a list of students
   * @param teacherList a list of teachers
   * @param courseList a list of courses
   * @param roomList a list of rooms
   * @param sessionList a list of sessions
   */
  public Reader(StudentList studentList, TeacherList teacherList, CourseList courseList,RoomList roomList, SessionList sessionList)
  {
    this.roomList = roomList;
    this.studentList = studentList;
    this.courseList = courseList;
    this.teacherList = teacherList;
    this.sessionList = sessionList;
  }

  /**
   * @throws Exception the reader from the java.util.Scanner throws an exception when it comes to scanning files
   *
   * The methods within the classes readers are utilized to read the
   * files from the head of department contained in the txt package
   @see readers.StudentReader
   @see readers.CourseReader
   @see readers.TeacherReader
   @see readers.RoomReader
   */
  public void readFilesFromHOD() throws Exception
  {
    StudentReader studentReader = new StudentReader();
    studentReader.readStudents();
    studentList = studentReader.getListOfStudents();

    TeacherReader teacherReader = new TeacherReader();
    teacherReader.readTeachers();
    teacherList = teacherReader.getTeacherList();

    CourseReader courseReader = new CourseReader();
    courseReader.readCourses();
    courseList = courseReader.getListOfCourses();

    RoomReader roomReader = new RoomReader();
    roomReader.readRooms();
    roomList = roomReader.getRoomList();
  }

  /**
   * @throws Exception the usage of the import java.io.PrintWriter constitutes the use of throwing an exception
   * in order for it to work
   *
   * The writer methods within the Reader Class are called to write its respective files
   *
   */
  public void writeFiles() throws Exception
  {
    writeStudents();
    writeTeachers();
    writeCourses();
    writeRooms();
    writeSessions();
  }

  /**
   * @throws Exception the usage of java.util.Scanner requires to throw an exception
   *
   * The reader methods within the Reader class are called to perform reading operations on their respective files
   *
   */
  public void readFiles() throws Exception
  {
    readStudents();
    readTeachers();
    readCourses();
    readRooms();
    readSessions();
  }

  /**
   * @throws Exception the usage of the import java.io.PrintWriter constitutes the use of throwing an exception
   * in order for it to work
   *
   * The method performs a writing operation in the specified text file by getting all of the students as an arrayList
   * and writing that arrayList in the text file. The written information is also separated by ,.
   */
  //writers
  public void writeStudents() throws Exception
  {
    PrintWriter outS = null;
    try
    {
      String fileName = "src\\ourTxt\\studentList.txt";
      File file = new File(fileName);
      outS = new PrintWriter(file);
      System.out.println(studentList.getAllStudentsAsArrayList());
      for (int i = 0; i < studentList.getAllStudentsAsArrayList().size(); i++)
      {
        System.out.println(studentList.getAllStudentsAsArrayList().get(i));
        outS.print(studentList.getAllStudentsAsArrayList().get(i).getName());
        outS.print(", ");
        outS.print(studentList.getAllStudentsAsArrayList().get(i).getStudentID());
        outS.print(", ");
        outS.println(studentList.getAllStudentsAsArrayList().get(i).getClassID());
        outS.flush();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      outS.close();
    }
  }

  /**
   * @throws Exception the usage of the import java.io.PrintWriter constitutes the use of throwing an exception
   * in order for it to work
   *
   * The method performs a writing operation in the specified text file. The writing that takes place is of a teacherList
   * this list is stored as an arrayList and afterwards the arrayList is written in the file.
   * The written information is also separated by ,.
   */
  public void writeTeachers() throws Exception
  {
    PrintWriter outT = null;
    try
    {
      String fileName = "src\\ourTxt\\teacherList.txt";
      File file = new File(fileName);
      outT = new PrintWriter(file);
      for (int i = 0; i < teacherList.getAllTeachersAsArrayList().size(); i++)
      {
        outT.print( teacherList.getAllTeachersAsArrayList().get(i).getName());
        outT.print(", ");
        outT.println( teacherList.getAllTeachersAsArrayList().get(i).getId());
        outT.flush();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      outT.close();
    }
  }

  /**
   * @throws Exception the usage of the import java.io.PrintWriter constitutes the use of throwing an exception
   * in order for it to work
   *
   * The method performs a writing operation in the specified text file. By making use of a list of courses stored as
   * an arrayList. The written information is also separated by ,.
   */
  public void writeCourses() throws Exception
  {
    PrintWriter outC = null;
    try
    {
      String fileName = "src\\ourTxt\\courseList.txt";
      File file = new File(fileName);
      outC = new PrintWriter(file);
      for (int i = 0; i < courseList.getAllCoursesAsArrayList().size(); i++)
      {
        outC.print(courseList.getAllCoursesAsArrayList().get(i).getCourseID());
        outC.print(", ");
        outC.print(courseList.getAllCoursesAsArrayList().get(i).getEcts());
        outC.print(", ");
        String classID = "";
        String courseID = courseList.getAllCoursesAsArrayList().get(i).getCourseID();
        classID +=courseID.charAt(0);
        classID +=courseID.charAt(1);
        if(courseID.length() == 6)
        {
          classID+= courseID.charAt(2);
        }
        for(int j=0;j<courseList.getAllCoursesAsArrayList().get(i).getStudentList().getAllStudentsAsArrayList().size();j++)
        {
          if(courseList.getAllCoursesAsArrayList().get(i).getStudentList().getAllStudentsAsArrayList().get(j) != null)
          {
            outC.print(courseList.getAllCoursesAsArrayList().get(i).getStudentList().getAllStudentsAsArrayList().get(j).getStudentID());
            outC.print(", ");
          }
        }
        outC.println("");

        outC.print(courseList.getAllCoursesAsArrayList().get(i).getCourseID());
        outC.print(", ");
        outC.print(courseList.getAllCoursesAsArrayList().get(i).getEcts());
        outC.print(", ");
        for(int j=0;j<courseList.getAllCoursesAsArrayList().get(i).getTeacherList().getAllTeachersAsArrayList().size();j++)
        {
          if(courseList.getAllCoursesAsArrayList().get(i).getTeacherList().getAllTeachersAsArrayList().get(j) != null)
          {
            outC.print(courseList.getAllCoursesAsArrayList().get(i).getTeacherList().getAllTeachersAsArrayList().get(j).getId());
            outC.print(", ");
          }
        }
        outC.println("");
        outC.flush();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      outC.close();
    }
  }

  /**
   * @throws Exception the usage of the import java.io.PrintWriter constitutes the use of throwing an exception
   * in order for it to work
   *
   * The method performs a writing operation in the specified text file. Utilizing a list of rooms stored as an arrayList.
   * The written information is also separated by ,.
   */
  public void writeRooms() throws Exception
  {
    PrintWriter outR = null;
    try
    {
      String fileName = "src\\ourTxt\\roomList.txt";
      File file = new File(fileName);
      outR = new PrintWriter(file);
      for (int i = 0; i < roomList.getAllRoomsAsArrayList().size(); i++)
      {
        outR.print(roomList.getAllRoomsAsArrayList().get(i).getId());
        outR.print(", ");
        outR.print(roomList.getAllRoomsAsArrayList().get(i).getCapacity());
        outR.print(", ");
        outR.print(roomList.getAllRoomsAsArrayList().get(i).getUnitedWith());
        outR.print(", ");
        outR.println(roomList.getAllRoomsAsArrayList().get(i).getIsBooked());
        outR.flush();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      outR.close();
    }
  }

  /**
   * @throws Exception the usage of the import java.io.PrintWriter constitutes the use of throwing an exception
   * in order for it to work
   *
   * The method performs a writing operation in the specified text file. In this case, it makes use of a list of sessions
   * and afterwards writes it as an arrayList.
   * The written information is also separated by ,.
   */
  public void writeSessions() throws Exception
  {
    PrintWriter outSe = null;
    try
    {
      String fileName = "src\\ourTxt\\sessionList.txt";
      File file = new File(fileName);
      outSe = new PrintWriter(file);
      for (int i = 0; i < sessionList.getAllSessions().size(); i++)
      {
        outSe.print(sessionList.getAllSessions().get(i).getNumber());
        outSe.print(", ");
        outSe.print(sessionList.getAllSessions().get(i).getCourse().getCourseID());
        outSe.print(", ");
        outSe.print(sessionList.getAllSessions().get(i).getNumberOfLessonsInSession());
        outSe.print(", ");
        if(sessionList.getAllSessions().get(i).getRoom() == null)
        {
          outSe.print("null,");
        }
        else
        {
          outSe.print(sessionList.getAllSessions().get(i).getRoom().getId());
          outSe.print(", ");
        }
        if(sessionList.getAllSessions().get(i).getDateAndStartTime() == null)
        {
          outSe.print("null,");
          outSe.print("null,");
          outSe.print("null,");
          outSe.print("null,");
          outSe.print("null,");
        }
        else
        {
          outSe.print(sessionList.getAllSessions().get(i).getDateAndStartTime().getDay());
          outSe.print(", ");
          outSe.print(sessionList.getAllSessions().get(i).getDateAndStartTime().getMonth());
          outSe.print(", ");
          outSe.print(sessionList.getAllSessions().get(i).getDateAndStartTime().getYear());
          outSe.print(", ");
          outSe.print(sessionList.getAllSessions().get(i).getDateAndStartTime().getHour());
          outSe.print(", ");
          outSe.print(sessionList.getAllSessions().get(i).getDateAndStartTime().getMinute());
          outSe.print(", ");
        }
        if(sessionList.getAllSessions().get(i).getDateAndEndTime() == null)
        {
          outSe.print("null,");
          outSe.print("null,");
          outSe.print("null,");
          outSe.print("null,");
          outSe.print("null");
        }
        else
        {
          outSe.print(sessionList.getAllSessions().get(i).getDateAndEndTime().getDay());
          outSe.print(", ");
          outSe.print(sessionList.getAllSessions().get(i).getDateAndEndTime().getMonth());
          outSe.print(", ");
          outSe.print(sessionList.getAllSessions().get(i).getDateAndEndTime().getYear());
          outSe.print(", ");
          outSe.print(sessionList.getAllSessions().get(i).getDateAndEndTime().getHour());
          outSe.print(", ");
          outSe.print(sessionList.getAllSessions().get(i).getDateAndEndTime().getMinute());
        }
        outSe.println("");
        outSe.flush();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      outSe.close();
    }
  }

  /**
   * @throws Exception the usage of java.util.Scanner requires to throw an exception
   *
   * The method performs a scan of the specified text file line by line and stores the information in variables
   * that are added afterwards as a Student object to a studentList.
   */
  //readers
  public void readStudents() throws Exception
  {
    File file = new File(
        "src\\ourTxt\\studentList.txt");
    Scanner in = new Scanner(file);
    //in.nextLine();
    while (in.hasNext())
    {
      String line = in.nextLine();
      String[] splittingline = line.split(",");
      String studentName = splittingline[0].trim();
      int studentId = Integer.parseInt(splittingline[1].trim());
      String classId = splittingline[2].trim();

      Student student = new Student(studentName, studentId, classId);
      studentList.addStudent(student);
    }
  }

  /**
   * @throws Exception the usage of java.util.Scanner requires to throw an exception
   *
   * The method performs a scan of the specified text file line by line and stores the information in variables
   * that are added afterwards as a Teacher object to a TeacherList.
   */
  public void readTeachers() throws Exception
  {
    File file = new File(
        "src\\ourTxt\\teacherList.txt");
    Scanner in = new Scanner(file);

    //in.nextLine();
    while (in.hasNext())
    {
      String line = in.nextLine();
      String[] splittingline = line.split(",");
      String teacherName = splittingline[0].trim();
      String teacherId = splittingline[1].trim();

      Teacher teacher = new Teacher(teacherName, teacherId);
      teacherList.addTeacher(teacher);
    }
  }

  /**
   * @throws Exception the usage of java.util.Scanner requires to throw an exception
   *
   * The method performs a scan of the specified text file line by line and stores the information in variables
   * that are added afterwards as a Course object to a CourseList.
   */
  public void readCourses() throws Exception
  {
    File file = new File(
        "src\\ourTxt\\courseList.txt");
    Scanner in = new Scanner(file);

    while (in.hasNext())
    {
      int ok=0;
      String line = in.nextLine();
      String[] splittingline = line.split(",");
      String courseID = splittingline[0].trim();
      int ects = Integer.parseInt(splittingline[1].trim());
      //teacherList
      for(int i=0;i<courseList.getAllCoursesAsArrayList().size();i++)
      {
        if(courseID.equals(courseList.getAllCoursesAsArrayList().get(i).getCourseID()))
        {
          int j=2;
          while(j<splittingline.length)
          {
            String teacherID = splittingline[j].trim();
            j++;
            Teacher teacher = teacherList.getTeacherByID(teacherID);
            courseList.getAllCoursesAsArrayList().get(i).addTeacher(teacher);
          }
          ok=1;
        }
      }
      if(ok==0)
      //studentList
      {
        Course course = new Course(courseID, ects);
        StudentList courseSutdents = new StudentList();
        int j = 2;
        while (j < splittingline.length && !splittingline[j].trim().equals(""))
        {
          int studentId = Integer.parseInt(splittingline[j].trim());
          j++;
          Student student = studentList.getStudentByID(studentId);
          courseSutdents.addStudent(student);
        }
        course.setStudentList(courseSutdents);
        courseList.addCourse(course);
      }
    }
  }

  /**
   * @throws Exception the usage of java.util.Scanner requires to throw an exception
   *
   * The method performs a scan of the specified text file line by line and stores the information in variables
   * that are added afterwards as a Room object to a RoomList.
   */
  public void readRooms() throws Exception
  {
    File file = new File(
        "src\\ourTxt\\roomList.txt");
    Scanner in = new Scanner(file);

    while (in.hasNext())
    {
      String line = in.nextLine();
      String[] splittingline = line.split(",");
      String roomId = splittingline[0].trim();
      int capacity = Integer.parseInt(splittingline[1].trim());
      String unitedWith = splittingline[2].trim();
      String booked = splittingline[3].trim();
      if(unitedWith.equals("null"))
        unitedWith = null;
      boolean isBooked = false;
      if(booked.equals("true"))
        isBooked = true;
      Room room = new Room(roomId, capacity, unitedWith, isBooked);
      roomList.addRoom(room);
    }
  }

  /**
   * @throws Exception the usage of java.util.Scanner requires to throw an exception
   *
   * The method performs a scan of the specified text file line by line and stores the information in variables
   * that are added afterwards as a Session object to a SessionList.
   */
  public void readSessions() throws Exception
  {
    File file = new File(
        "src\\ourTxt\\sessionList.txt");
    Scanner in = new Scanner(file);

    while (in.hasNext())
    {
      String line = in.nextLine();
      String[] splittingline = line.split(",");
      int sessionNum = Integer.parseInt(splittingline[0].trim());
      String courseID = splittingline[1].trim();
      int numberOfLessons = Integer.parseInt(splittingline[2].trim());
      String roomId = splittingline[3].trim();


      Course course = courseList.getCourseByID(courseID);
      Session session;
      if(roomId.equals("null"))
      {
        if (splittingline[4].trim().equals("null"))
          session = new Session(sessionNum, course, numberOfLessons);
        else
        {
          int d = Integer.parseInt(splittingline[4].trim());
          int m = Integer.parseInt(splittingline[5].trim());
          int y = Integer.parseInt(splittingline[6].trim());
          int h = Integer.parseInt(splittingline[7].trim());
          int min = Integer.parseInt(splittingline[8].trim());
          DateTime startTime = new DateTime(d, m, y, h, min);
          d = Integer.parseInt(splittingline[4].trim());
          m = Integer.parseInt(splittingline[5].trim());
          y = Integer.parseInt(splittingline[6].trim());
          h = Integer.parseInt(splittingline[7].trim());
          min = Integer.parseInt(splittingline[8].trim());
          DateTime endTime = new DateTime(d, m, y, h, min);
          session = new Session(sessionNum, course, numberOfLessons, startTime, endTime);
        }
      }
      else
      {
        Room room = roomList.getRoomByID(roomId);
        int d = Integer.parseInt(splittingline[4].trim());
        int m = Integer.parseInt(splittingline[5].trim());
        int y = Integer.parseInt(splittingline[6].trim());
        int h = Integer.parseInt(splittingline[7].trim());
        int min = Integer.parseInt(splittingline[8].trim());
        DateTime startTime = new DateTime(d, m, y, h, min);
        d = Integer.parseInt(splittingline[4].trim());
        m = Integer.parseInt(splittingline[5].trim());
        y = Integer.parseInt(splittingline[6].trim());
        h = Integer.parseInt(splittingline[7].trim());
        min = Integer.parseInt(splittingline[8].trim());
        DateTime endTime = new DateTime(d, m, y, h, min);
        session = new Session(sessionNum, course, numberOfLessons, room, startTime, endTime);
      }
      sessionList.addSession(session);
    }
  }

  /**
   * @return studentList.
   *
   * Getter method for the case of a StudentList Object.
   */
  public StudentList getStudentList()
  {
    return studentList;
  }

  /**
   * @return teacherList.
   *
   * Getter method for the case of a TeacherList Object.
   */
  public TeacherList getTeacherList()
  {
    return teacherList;
  }

  /**
   * @return courseList.
   *
   * Getter method for the case of a CourseList Object.
   */
  public CourseList getCourseList()
  {
    return courseList;
  }

  /**
   * @return roomList.
   *
   * Getter method for the case of a RoomList Object.
   */

  public RoomList getRoomList()
  {
    return roomList;
  }

  /**
   * @return sessionList.
   *
   * Getter method for the case of a SessionList Object.
   */
  public SessionList getSessionList()
  {
    return sessionList;
  }
}
