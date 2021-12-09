package readers;

import readers.*;
import model.*;


public class Reader
{
  private CourseList courseList;
  private StudentList studentList;
  private TeacherList teacherList;
  private RoomList roomList;

  public Reader()
  {
    this.roomList = new RoomList();
    this.studentList = new StudentList();
    this.courseList = new CourseList();
    this.teacherList = new TeacherList();
  }
  public void readFiles() throws Exception
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
}
