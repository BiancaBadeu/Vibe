package model;

import readers.Reader;

import java.util.ArrayList;

public class Manager implements Model
{
  private TeacherList teacherList;
  private CourseList courseList;
  private StudentList studentList;
  private SessionList sessionList;
  private RoomList roomList;

  public Manager() throws Exception
  {
    this.teacherList = new TeacherList();
    this.studentList = new StudentList();
    this.courseList = new CourseList();
    this.roomList = new RoomList();
    this.sessionList = new SessionList();
    inputFiles();
  }

  public void inputFiles() throws Exception
  {
    Reader reader = new Reader();
    reader.readFiles();
    this.teacherList = reader.getTeacherList();
    this.studentList = reader.getStudentList();
    this.courseList = reader.getCourseList();
    this.roomList = reader.getRoomList();
  }

  public TeacherList getAllTeachers()
  {
    return teacherList;
  }

  public ArrayList<Teacher> getAllTeachersAsArrayList(){return teacherList.getAllTeachersAsArrayList();}

  public void addTeacher(Teacher teacher)
  {
    teacherList.addTeacher(teacher);
  }

  public void removeTeacherFromSystemByID(String id)
  {
    teacherList.removeTeacherByID(id);
    ArrayList<Course> courses= courseList.getAllCoursesAsArrayList();
    for(int i=0;i<courses.size();i++)
    {
      courses.get(i).removeTeacherByIDFromCourse(id);
    }
  }
  public void removeStudentFromSystemByID(int id)
  {
    studentList.removeStudentByID(id);
    ArrayList<Course> courses= courseList.getAllCoursesAsArrayList();
    for(int i=0;i<courses.size();i++)
    {
      courses.get(i).removeStudentByIDFromCourse(id);
    }
  }

  public Teacher getTeacherByID(String id)
  {
    return teacherList.getTeacherByID(id);
  }
  public TeacherList getTeachersByName(String name)
  {
    return teacherList.getTeachersByName(name);
  }
  public void addTeacherToCourse(Teacher teacher, Course course)
  {
    course.addTeacher(teacher);
  }
  public void removeTeacherFromCourseByID(String id, Course course)
  {
    course.removeTeacherByIDFromCourse(id);
  }

  public CourseList getAllCourses()
  {
    return courseList.getAllCourses();
  }
  public ArrayList<Course> getAllCoursesAsArrayList(){
    return courseList.getAllCoursesAsArrayList();
  }
  public void addCourse(Course course)
  {
    courseList.addCourse(course);
  }
  public Course getCourseByID(String id)
  {
    return courseList.getCourseByID(id);
  }
  public void calculateNumberOfLessonsForCourse(Course course)
  {
    sessionList.calculateNumberOfLessonsForCourse(course);
  }

  public void removeStudentFromCourseByID(int id, Course course)
  {
    course.removeStudentByIDFromCourse(id);
  }
  public StudentList getAllStudents()
  {
    return studentList.getAllStudents();
  }
  public void validateRemoveStudent(String idField){studentList.validateRemoveStudent(idField);}
  public void validateAddStudent(String nameField, String idField, String classField){studentList.validateAddStudent(nameField, idField, classField);}
  public void addStudent(Student student)
  {
    studentList.addStudent(student);
  }
  public void removeStudentByID(int id)
  {
    studentList.removeStudentByID(id);
  }
  public StudentList getAllStudentsInClassID(String classID)
  {
    return studentList.getStudentsByClassID(classID);
  }
  public StudentList getStudentsByName(String name)
  {
    return studentList.getStudentsByName(name);
  }

  public SessionList getAllSessions()
  {
    return sessionList.getAllSessionsAsList();
  }
  public ArrayList<Session> getAllSessionsAsArrayList()
  {
    return sessionList.getAllSessions();
  }
  public void addSession(Session session)
  {
    sessionList.addSession(session);
  }
  public void removeSession(Session session)
  {
    sessionList.removeSession(session);
  }
  public ArrayList<Session> getBookedSessions()
  {
    return sessionList.getBookedSessions();
  }
  public ArrayList<Session> getUnbookedSessions()
  {
    return sessionList.getUnbookedSessions();
  }

  public RoomList getAllRooms()
  {
    return roomList.getAllRooms();
  }
  public void addRoom(Room room)
  {
    roomList.addRoom(room);
  }
  public Room getRoomByID(String id)
  {
    return roomList.getRoomByID(id);
  }
  public RoomList getRoomsBySize(int capacity)
  {
    return roomList.getRoomsBySize(capacity);
  }
  public RoomList getRoomsBySizeBiggerThan(int capacity)
  {
    return roomList.getRoomsBySizeBiggerThan(capacity);
  }
  public RoomList getUnitableRooms()
  {
    return roomList.getUnitableRooms();
  }

  public ArrayList<Room> getRoomsAvailableInPeriod(DateTime startTime, DateTime endTime)
  {
     return sessionList.getRoomsAvailableInPeriod(startTime, endTime, roomList);
  }

  public ArrayList<Room> getRoomsAvailableInPeriodBySize(int capacity, DateTime startTime, DateTime endTime)
  {
     return sessionList.getRoomsAvailableInPeriodBySize(capacity, startTime, endTime, roomList);
  }
  public ArrayList<Room> getRoomsAvailableInPeriodBySizeBiggerThan(int capacity, DateTime startTime, DateTime endTime)
  {
    return sessionList.getRoomsAvailableInPeriodBySizeBiggerThan(capacity, startTime, endTime, roomList);
  }
  public ArrayList<Room> getUnitableRoomsInPeriod(DateTime startTime, DateTime endTime)
  {
    return sessionList.getUnitableRoomsInPeriod(startTime, endTime, roomList);
  }

  public ArrayList<Room> getAllBookedRooms()
  {
    return roomList.getAllBookedRooms();
  }
  public ArrayList<Room> getAllUnbookedRooms()
  {
    return roomList.getAllUnbookedRooms();
  }

  public String toString()
  {
    return "Teacher list: " + teacherList + ", Student list: " + studentList + ", Course list: " + courseList +
        ", Session list: " + sessionList + ", Room list: " + roomList;
  }

}
