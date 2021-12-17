package model;

import readers.Reader;

import java.util.ArrayList;

/**
 * A class representing the manager that implements the model
 */
public class Manager implements Model
{
  private TeacherList teacherList;
  private CourseList courseList;
  private StudentList studentList;
  private SessionList sessionList;
  private RoomList roomList;

  /**
   * @throws Exception import java.util.Scanner requires an exception to be thrown in order to perform operations
   *
   * A 0 argument constructor that initializes lists of teachers, courses, students, sessions and rooms
   * @see TeacherList
   * @see StudentList
   * @see CourseList
   * @see RoomList
   * @see SessionList
   *
   *
   */
  public Manager() throws Exception
  {
    this.teacherList = new TeacherList();
    this.studentList = new StudentList();
    this.courseList = new CourseList();
    this.roomList = new RoomList();
    this.sessionList = new SessionList();
    readFiles();
  }

  //files

  /**
   * @throws Exception import java.util.Scanner requires an exception to be thrown in order to perform operations
   *
   * The method makes use of the readers in the Reader class to read different files from all list types
   * @see Reader
   */
  public void readFiles() throws Exception
  {
    Reader reader = new Reader(studentList, teacherList, courseList, roomList, sessionList);
    reader.readFiles();
    this.studentList = reader.getStudentList();
    this.teacherList = reader.getTeacherList();
    this.courseList = reader.getCourseList();
    this.roomList = reader.getRoomList();
    this.sessionList = reader.getSessionList();
  }

  /**
   * @throws Exception import java.io.PrintWriter requires an exception to be thrown in order for operations to be performed
   *
   * The method writes in all files based on the contents of their respective variable lists
   */
  public void writeFiles() throws Exception{
    Reader reader = new Reader(studentList, teacherList, courseList, roomList, sessionList);
    reader.writeFiles();
  }

  /**
   * @throws Exception import java.util.Scanner requires an exception to be thrown in order to perform operations
   *
   * A method that performs a reading operation of the Head Of Department files and writes them in their respective
   * files
   */
  public void inputFiles() throws Exception
  {
    Reader reader = new Reader(studentList, teacherList, courseList, roomList, sessionList);
    reader.readFilesFromHOD();
    this.studentList = reader.getStudentList();
    this.teacherList = reader.getTeacherList();
    this.courseList = reader.getCourseList();
    this.roomList = reader.getRoomList();
    this.sessionList = reader.getSessionList();
    System.out.println(studentList);
    reader.writeFiles();
  }

  /**
   * @return teacherList the current teacherList
   */
  //teacher
  public TeacherList getAllTeachers()
  {
    return teacherList;
  }

  /**
   * @return teacherList the current teacherList as an arrayList
   */
  public ArrayList<Teacher> getAllTeachersAsArrayList(){return teacherList.getAllTeachersAsArrayList();}

  /**
   * @param teacher teacher to be added to the teacherList
   */
  public void addTeacher(Teacher teacher)
  {
    teacherList.addTeacher(teacher);
  }

  /**
   * @param id teacherID of the teacher to be removed from the system
   */
  public void removeTeacherFromSystemByID(String id)
  {
    teacherList.removeTeacherByID(id);
  }

  /**
   * @param id teacherID
   * @return the teacher information
   */
  public Teacher getTeacherByID(String id)
  {
    return teacherList.getTeacherByID(id);
  }

  /**
   * @param name teacher name
   * @return teachers by their names
   */
  public TeacherList getTeachersByName(String name)
  {
    return teacherList.getTeachersByName(name);
  }

  /**
   * @param teacher teacher to be added
   * @param course course for the teacher to be added
   *
   * The method adds the teacher to the specified course
   */
  public void addTeacherToCourse(Teacher teacher, Course course)
  {
    course.addTeacher(teacher);
  }

  /**
   * @param id teacherID
   * @param course courseID
   *
   * The method removes the teacher from the specified course
   */
  public void removeTeacherFromCourseByID(String id, Course course)
  {
    course.removeTeacherByIDFromCourse(id);
  }

  /**
   * @return all the courses in the courseList
   */
  //course
  public CourseList getAllCourses()
  {
    return courseList.getAllCourses();
  }

  /**
   * @return courseList as an arrayList
   */
  public ArrayList<Course> getAllCoursesAsArrayList(){
    return courseList.getAllCoursesAsArrayList();
  }

  /**
   * @param course course to be added
   *
   * The course is added to the courseList
   */
  public void addCourse(Course course)
  {
    courseList.addCourse(course);
  }

  /**
   * @param id courseID
   * @return course by the id
   */
  public Course getCourseByID(String id)
  {
    return courseList.getCourseByID(id);
  }

  /**
   * @param course course to calculate the number of lessons
   */
  public void calculateNumberOfLessonsForCourse(Course course)
  {
    sessionList.calculateNumberOfLessonsForCourse(course);
  }

  /**
   * @return all students in the studentList
   */
  //student
  public StudentList getAllStudents()
  {
    return studentList.getAllStudents();
  }

  /**
   * @return studentList as an arrayList
   */
  public ArrayList<Student> getAllStudentsAsArrayList(){
    return studentList.getAllStudentsAsArrayList();
  }

  /**
   * @param student student to be added
   *
   * The method adds the student to the studentList
   */
  public void addStudent(Student student)
  {
    studentList.addStudent(student);
  }

  /**
   * @param id studentID of student to be removed
   * @param course course of the student to be removed
   *
   * Method removes student from the specified course
   */
  public void removeStudentFromCourseByID(int id, Course course)
  {
    course.removeStudentByIDFromCourse(id);
  }

  /**
   * @param id studentID of the student to be removed
   *
   * The student is removed from the studentList
   */
  public void removeStudentFromSystemByID(int id)
  {
    studentList.removeStudentByID(id);
    ArrayList<Course> courses= courseList.getAllCoursesAsArrayList();
    for(int i=0;i<courses.size();i++)
    {
      courses.get(i).removeStudentByIDFromCourse(id);
    }
  }

  /**
   * @param id studentID of the student to be removed
   *
   * Method to remove the student from the studentList
   */
  public void removeStudentByID(int id)
  {
    studentList.removeStudentByID(id);
  }

  /**
   * @param classID classID to get all the students from
   * @return students from the class specified
   */
  public StudentList getAllStudentsInClassID(String classID)
  {
    return studentList.getStudentsByClassID(classID);
  }

  /**
   * @param name student's name
   * @return student with the name
   */
  public StudentList getStudentsByName(String name)
  {
    return studentList.getStudentsByName(name);
  }

  /**
   * @return all the sessions in the sessionList
   */
  //session
  public SessionList getAllSessions()
  {
    return sessionList.getAllSessionsAsList();
  }

  /**
   * @return all sessions in sessionList as an arrayList
   */
  public ArrayList<Session> getAllSessionsAsArrayList()
  {
    return sessionList.getAllSessions();
  }

  /**
   * @param session session to be added
   *
   * The method adds the session to a sessionList
   */
  public void addSession(Session session)
  {
    sessionList.addSession(session);
  }

  /**
   * @param session session to be removed
   *
   * The method removes the session from the sessionList
   */
  public void removeSession(Session session)
  {
    sessionList.removeSession(session);
  }

  /**
   * @return ArrayList of all booked sessions
   *
   * The booked sessions are returned from the sessionList
   */
  public ArrayList<Session> getBookedSessions()
  {
    return sessionList.getBookedSessions();
  }

  /**
   * @return ArrayList of all unbooked sessions
   *
   * The unbooked sessions are returned from the sessionList
   */
  public ArrayList<Session> getUnbookedSessions()
  {
    return sessionList.getUnbookedSessions();
  }

  /**
   * @return all rooms in a roomList
   *
   */
  //room
  public RoomList getAllRooms()
  {
    return roomList.getAllRooms();
  }

  /**
   * @return rooms in the roomList as an arrayList
   */
  public ArrayList<Room> getAllRoomsAsArrayList(){return roomList.getAllRoomsAsArrayList();}

  /**
   * @param room room to be added
   *
   * The method adds the room to the roomList
   */
  public void addRoom(Room room)
  {
    roomList.addRoom(room);
  }

  /**
   * @param id roomID
   * @return the room that has the matching id
   */
  public Room getRoomByID(String id)
  {
    return roomList.getRoomByID(id);
  }

  /**
   * @param capacity capacity of the room
   * @return rooms in the roomList that have the capacity
   */
  public RoomList getRoomsBySize(int capacity)
  {
    return roomList.getRoomsBySize(capacity);
  }

  /**
   * @param capacity room capacity
   * @return rooms in the roomList that have a capacity larger than the parameter
   */
  public RoomList getRoomsBySizeBiggerThan(int capacity)
  {
    return roomList.getRoomsBySizeBiggerThan(capacity);
  }

  /**
   * @return all rooms in the roomList that have the unitable property
   */
  public RoomList getUnitableRooms()
  {
    return roomList.getUnitableRooms();
  }

  /**
   * @param startTime start time for availability
   * @param endTime end time for availability
   * @return rooms in the roomList that are available between the two DateTime objects
   */
  public ArrayList<Room> getRoomsAvailableInPeriod(DateTime startTime, DateTime endTime)
  {
     return sessionList.getRoomsAvailableInPeriod(startTime, endTime, roomList);
  }

  /**
   * @param capacity room capacity
   * @param startTime start time for availability
   * @param endTime end time for availability
   * @return rooms in the roomList that have the capacity and ara available between the two DateTime objects
   */
  public ArrayList<Room> getRoomsAvailableInPeriodBySize(int capacity, DateTime startTime, DateTime endTime)
  {
     return sessionList.getRoomsAvailableInPeriodBySize(capacity, startTime, endTime, roomList);
  }

  /**
   * @param capacity room capacity
   * @param startTime start time for availability
   * @param endTime end time for availability
   * @return rooms in the roomList that have a capacity larger than the specified and are available between the
   * two DateTime Objects
   */
  public ArrayList<Room> getRoomsAvailableInPeriodBySizeBiggerThan(int capacity, DateTime startTime, DateTime endTime)
  {
    return sessionList.getRoomsAvailableInPeriodBySizeBiggerThan(capacity, startTime, endTime, roomList);
  }

  /**
   * @param startTime start time for availability
   * @param endTime end time for availability
   * @return rooms in the roomList that are unitable and are available between the two DateTime objects
   */
  public ArrayList<Room> getUnitableRoomsInPeriod(DateTime startTime, DateTime endTime)
  {
    return sessionList.getUnitableRoomsInPeriod(startTime, endTime, roomList);
  }

  /**
   * @return rooms in the roomList that are booked
   */
  public ArrayList<Room> getAllBookedRooms()
  {
    return roomList.getAllBookedRooms();
  }

  /**
   * @return rooms in the roomList that are not booked
   */
  public ArrayList<Room> getAllUnbookedRooms()
  {
    return roomList.getAllUnbookedRooms();
  }

  /**
   * @param idField studentID of student to be removed.
   *
   * The student id is checked to see if has a legal value a string the id starts from 1-9 and the other digits
   * are between 0-9 and the length of the string has to be 6
   */
  //GUI
  public void validateRemoveStudent(String idField){studentList.validateRemoveStudent(idField);}

  /**
   * @param nameField name of the student
   * @param idField id of the student
   * @param classField class of the student
   *
   * The nameField is checked to see that it does not contain numbers or symbols
   *
   * The student id is checked to see if has a legal value a string the id starts from 1-9 and the other digits
   * are between 0-9 and the length of the string has to be 6
   *
   * The classField checks that the String starts with a number between 1-7, no numbers after the start of the string
   * and its length cannot be more than 4
   *
   */
  public void validateAddStudent(String nameField, String idField, String classField){studentList.validateAddStudent(nameField, idField, classField);}

  /**
   * @param nameField teacher name
   * @param idField teacher Id
   *
   * The teacher's name is checked to see if it contains symbols or numbers which cannot be.
   * The teacher's id is checked t see if it contains symbols or numbers as well
   */
  public void validateAddTeacher(String nameField, String idField){teacherList.validateAddTeacher(nameField, idField);}

  /**
   * @param idField teacher's name
   *
   * The teacher's name is checked to see if it contains symbols or numbers which cannot be and that the ID is uppercase.
   */
  public void validateRemoveTeacher(String idField){teacherList.validateRemoveTeacher(idField);}

  /**
   * @param courseField courseID
   * @param sessionField sessionID
   * @param lessonField number of lessons for the session
   *
   * The courseID is checked to see if it does not start with a valid classID {@link #validateRemoveStudent(String)}
   * The sessionID is checked to see if its a number
   * The number of lessons for the session is check to see if its a number
   */
  public void validateAddSession(String courseField, String sessionField, String lessonField){sessionList.validateAddSession(courseField, sessionField, lessonField);}

  /**
   * @param lessonField number of lessons for a session
   *
   * The new number of lessons introduced is checked to see if it is a number
   */
  public void validateEditSession(String lessonField){sessionList.validateEditSession(lessonField);}

  /**
   * @return a string containing all the list classes
   */
  public String toString()
  {
    return "Teacher list: " + teacherList + ", Student list: " + studentList + ", Course list: " + courseList +
        ", Session list: " + sessionList + ", Room list: " + roomList;
  }

}
