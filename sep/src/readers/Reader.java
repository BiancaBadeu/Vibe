package readers;

import readers.*;
import model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

public class Reader
{
  private CourseList courseList;
  private StudentList studentList;
  private TeacherList teacherList;
  private RoomList roomList;
  private SessionList sessionList;

  public Reader()
  {
    this.roomList = new RoomList();
    this.studentList = new StudentList();
    this.courseList = new CourseList();
    this.teacherList = new TeacherList();
    this.sessionList = new SessionList();
  }

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

  public void writeFiles() throws Exception
  {
    writeStudents();
    writeTeachers();
    writeCourses();
    writeRooms();
    writeSessions();
  }

  public void readFiles() throws Exception
  {
    readStudents();
    readTeachers();
    readCourses();
    readRooms();
    readSessions();
  }

  //writers
  public void writeStudents() throws Exception
  {
    PrintWriter outS = null;
    try
    {
      String fileName = "C:\\Elly\\VIA\\Elly Y\\SEP\\SEP1\\sep\\src\\ourTxt\\studentList.txt";
      File file = new File(fileName);
      outS = new PrintWriter(file);
      for (int i = 0; i < studentList.getAllStudentsAsArrayList().size(); i++)
      {
        outS.print(studentList.getAllStudentsAsArrayList().get(i).getName());
        outS.print("; ");
        outS.print(studentList.getAllStudentsAsArrayList().get(i).getStudentID());
        outS.print("; ");
        outS.println(studentList.getAllStudentsAsArrayList().get(i).getClassID());
        outS.flush();
      }
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    finally
    {
      outS.close();
    }
  }

  public void writeTeachers() throws Exception
  {
    PrintWriter outT = null;
    try
    {
      String fileName = "C:\\Elly\\VIA\\Elly Y\\SEP\\SEP1\\sep\\src\\ourTxt\\teacherList.txt";
      File file = new File(fileName);
      outT = new PrintWriter(file);
      for (int i = 0; i < teacherList.getAllTeachersAsArrayList().size(); i++)
      {
        outT.print( teacherList.getAllTeachersAsArrayList().get(i).getName());
        outT.print("; ");
        outT.println( teacherList.getAllTeachersAsArrayList().get(i).getId());
        outT.flush();
      }
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    finally
    {
      outT.close();
    }
  }

  public void writeCourses() throws Exception
  {
    PrintWriter outC = null;
    try
    {
      String fileName = "C:\\Elly\\VIA\\Elly Y\\SEP\\SEP1\\sep\\src\\ourTxt\\courseList.txt";
      File file = new File(fileName);
      outC = new PrintWriter(file);
      for (int i = 0; i < courseList.getAllCoursesAsArrayList().size(); i++)
      {
        outC.print(courseList.getAllCoursesAsArrayList().get(i).getCourseID());
        outC.print("; ");
        outC.print(courseList.getAllCoursesAsArrayList().get(i).getEcts());
        outC.print("; ");
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
            outC.print("; ");
          }
        }
        outC.println("");

        outC.print(courseList.getAllCoursesAsArrayList().get(i).getCourseID());
        outC.print("; ");
        outC.print(courseList.getAllCoursesAsArrayList().get(i).getEcts());
        outC.print("; ");
        for(int j=0;j<courseList.getAllCoursesAsArrayList().get(i).getTeacherList().getAllTeachersAsArrayList().size();j++)
        {
          if(courseList.getAllCoursesAsArrayList().get(i).getTeacherList().getAllTeachersAsArrayList().get(j) != null)
          {
            outC.print(courseList.getAllCoursesAsArrayList().get(i).getTeacherList().getAllTeachersAsArrayList().get(j).getId());
            outC.print("; ");
          }
        }
        outC.println("");
        outC.flush();
      }
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    finally
    {
      outC.close();
    }
  }

  public void writeRooms() throws Exception
  {
    PrintWriter outR = null;
    try
    {
      String fileName = "C:\\Elly\\VIA\\Elly Y\\SEP\\SEP1\\sep\\src\\ourTxt\\roomList.txt";
      File file = new File(fileName);
      outR = new PrintWriter(file);
      for (int i = 0; i < roomList.getAllRoomsAsArrayList().size(); i++)
      {
        outR.print(roomList.getAllRoomsAsArrayList().get(i).getId());
        outR.print("; ");
        outR.print(roomList.getAllRoomsAsArrayList().get(i).getCapacity());
        outR.print("; ");
        outR.print(roomList.getAllRoomsAsArrayList().get(i).getUnitedWith());
        outR.print("; ");
        outR.println(roomList.getAllRoomsAsArrayList().get(i).getIsBooked());
        outR.flush();
      }
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    finally
    {
      outR.close();
    }
  }
  public void writeSessions() throws Exception
  {
    PrintWriter outSe = null;
    try
    {
      String fileName = "C:\\Elly\\VIA\\Elly Y\\SEP\\SEP1\\sep\\src\\ourTxt\\sessionList.txt";
      File file = new File(fileName);
      outSe = new PrintWriter(file);
      for (int i = 0; i < sessionList.getAllSessions().size(); i++)
      {
        outSe.print(sessionList.getAllSessions().get(i).getNumber());
        outSe.print("; ");
        outSe.print(sessionList.getAllSessions().get(i).getCourse().getCourseID());
        outSe.print("; ");
        outSe.print(sessionList.getAllSessions().get(i).getNumberOfLessonsInSession());
        outSe.print("; ");
        if(sessionList.getAllSessions().get(i).getRoom() == null)
        {
          outSe.print("null");
          outSe.print("; ");
        }
        else
        {
          outSe.print(sessionList.getAllSessions().get(i).getRoom().getId());
          outSe.print("; ");
        }
        if(sessionList.getAllSessions().get(i).getDateAndStartTime() == null)
        {
          outSe.print("null");
          outSe.print("; ");
          outSe.print("null");
          outSe.print("; ");
          outSe.print("null");
          outSe.print("; ");
          outSe.print("null");
          outSe.print("; ");
          outSe.print("null");
          outSe.print("; ");
        }
        else
        {
          outSe.print(sessionList.getAllSessions().get(i).getDateAndStartTime().getDay());
          outSe.print("; ");
          outSe.print(sessionList.getAllSessions().get(i).getDateAndStartTime().getMonth());
          outSe.print("; ");
          outSe.print(sessionList.getAllSessions().get(i).getDateAndStartTime().getYear());
          outSe.print("; ");
          outSe.print(sessionList.getAllSessions().get(i).getDateAndStartTime().getHour());
          outSe.print("; ");
          outSe.print(sessionList.getAllSessions().get(i).getDateAndStartTime().getMinute());
          outSe.print("; ");
        }
        if(sessionList.getAllSessions().get(i).getDateAndEndTime() == null)
        {
          outSe.print("null");
          outSe.print("; ");
          outSe.print("null");
          outSe.print("; ");
          outSe.print("null");
          outSe.print("; ");
          outSe.print("null");
          outSe.print("; ");
          outSe.print("null");
        }
        else
        {
          outSe.print(sessionList.getAllSessions().get(i).getDateAndEndTime().getDay());
          outSe.print("; ");
          outSe.print(sessionList.getAllSessions().get(i).getDateAndEndTime().getMonth());
          outSe.print("; ");
          outSe.print(sessionList.getAllSessions().get(i).getDateAndEndTime().getYear());
          outSe.print("; ");
          outSe.print(sessionList.getAllSessions().get(i).getDateAndEndTime().getHour());
          outSe.print("; ");
          outSe.println(sessionList.getAllSessions().get(i).getDateAndEndTime().getMinute());
        }
        outSe.flush();
      }
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    finally
    {
      outSe.close();
    }
  }

  //readers
  public void readStudents() throws Exception
  {
    File file = new File(
        "C:\\Elly\\VIA\\Elly Y\\SEP\\SEP1\\sep\\src\\ourTxt\\studentList.txt");
    Scanner in = new Scanner(file);

    while (in.hasNext())
    {
      String line = in.nextLine();
      String[] splittingline = line.split(";");
      String studentName = splittingline[0].trim();
      int studentId = Integer.parseInt(splittingline[1].trim());
      String classId = splittingline[2].trim();

      Student student = new Student(studentName, studentId, classId);
      studentList.addStudent(student);
    }
  }
  public void readTeachers() throws Exception
  {
    File file = new File(
        "C:\\Elly\\VIA\\Elly Y\\SEP\\SEP1\\sep\\src\\ourTxt\\teacherList.txt");
    Scanner in = new Scanner(file);

    while (in.hasNext())
    {
      String line = in.nextLine();
      String[] splittingline = line.split(";");
      String teacherName = splittingline[0].trim();
      String teacherId = splittingline[1].trim();

      Teacher teacher = new Teacher(teacherName, teacherId);
      teacherList.addTeacher(teacher);
    }
  }
  public void readCourses() throws Exception
  {
    File file = new File(
        "C:\\Elly\\VIA\\Elly Y\\SEP\\SEP1\\sep\\src\\ourTxt\\courseList.txt");
    Scanner in = new Scanner(file);

    while (in.hasNext())
    {
      int ok=0;
      String line = in.nextLine();
      String[] splittingline = line.split(";");
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
  public void readRooms() throws Exception
  {
    File file = new File(
        "C:\\Elly\\VIA\\Elly Y\\SEP\\SEP1\\sep\\src\\ourTxt\\roomList.txt");
    Scanner in = new Scanner(file);

    while (in.hasNext())
    {
      String line = in.nextLine();
      String[] splittingline = line.split(";");
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
  public void readSessions() throws Exception
  {
    File file = new File(
        "C:\\Elly\\VIA\\Elly Y\\SEP\\SEP1\\sep\\src\\ourTxt\\sessionList.txt");
    Scanner in = new Scanner(file);

    while (in.hasNext())
    {
      String line = in.nextLine();
      String[] splittingline = line.split(";");
      int sessionNum = Integer.parseInt(splittingline[0].trim());
      String courseID = splittingline[1].trim();
      int numberOfLessons = Integer.parseInt(splittingline[2].trim());
      String roomId = splittingline[3].trim();
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

      Course course = courseList.getCourseByID(courseID);
      Session session;
      if(roomId.equals("null"))
      {
        if (splittingline[4].trim().equals("null"))
          session = new Session(sessionNum, course, numberOfLessons);
        else
          session = new Session(sessionNum, course, numberOfLessons, startTime, endTime);
      }
      else
      {
        Room room = roomList.getRoomByID(roomId);
        session = new Session(sessionNum, course, numberOfLessons, room, startTime, endTime);
      }
      sessionList.addSession(session);
    }
  }
  public StudentList getStudentList()
  {
    return studentList;
  }
  public TeacherList getTeacherList()
  {
    return teacherList;
  }
  public CourseList getCourseList()
  {
    return courseList;
  }
  public RoomList getRoomList()
  {
    return roomList;
  }
  public SessionList getSessionList()
  {
    return sessionList;
  }
}
