package model;
import readers.*;

import java.util.ArrayList;

public interface Model
{
  /**
   * @throws Exception import java.util.Scanner requires an exception to be thrown in order to perform operations
   *
   * Reads the Head Of Department files
   * {@link Reader#readFiles()} ()}
   */
  //files
  void inputFiles() throws Exception;
  /**
   * @throws Exception java.io.PrintWriter requires an exception to be thrown in order to perform operations
   *
   * Writes in files
   * {@link Reader#writeFiles()}
   */
  void writeFiles() throws Exception;
  /**
   * @return all teachers in a teacherList
   * {@link TeacherList#getAllTeachers()}
   */
  //teacher
  TeacherList getAllTeachers();
  /**
   * @return all teachers as an arrayList of teachers from a teacherList
   * {@link TeacherList#getAllTeachersAsArrayList()}
   */
  ArrayList<Teacher> getAllTeachersAsArrayList();
  /**
   * @param teacher teacher to be added
   *
   * {@link TeacherList#addTeacher(Teacher)}
   */
  void addTeacher(Teacher teacher);
  /**
   * @param id teacher's id
   * @return teacher with the id
   * {@link TeacherList#getTeacherByID(String)}
   */
  Teacher getTeacherByID(String id);
  /**
   * @param name teacher's name
   * @return teacher from a teacher list with the name
   * {@link TeacherList#getTeachersByName(String)}
   */
  TeacherList getTeachersByName(String name);
  /**
   * @param teacher teacher
   * @param course course
   *
   * {@link Manager#addTeacherToCourse(Teacher, Course)} )}
   */
  void addTeacherToCourse(Teacher teacher, Course course);
  /**
   * @param id teacher's id
   * @param course course
   *
   * {@link Manager#removeTeacherFromCourseByID(String, Course)}
   */
  void removeTeacherFromCourseByID(String id, Course course);
  /**
   * @param id teacher's id
   *
   * {@link Manager#removeTeacherFromSystemByID(String)}
   */
  void removeTeacherFromSystemByID(String id);
  /**
   * @return all courses in a courseList
   * {@link Manager#getAllCourses()}
   */
  //course
  CourseList getAllCourses();
  /**
   * @return all courses in a courseList as an arrayList of type course
   * {@link Manager#getAllCoursesAsArrayList()}
   */
  ArrayList<Course> getAllCoursesAsArrayList();
  /**
   * @param course course to add
   *
   * {@link Manager#addCourse(Course)}
   */
  void addCourse(Course course);
  /**
   * @param id courseID
   * @return course
   */
  Course getCourseByID(String id);
  /**
   * @param course course object
   *
   * {@link Manager#calculateNumberOfLessonsForCourse(Course)}
   */
  void calculateNumberOfLessonsForCourse(Course course);
  /**
   * @param id student id
   * @param course course object
   *
   * {@link Manager#removeStudentFromCourseByID(int, Course)}
   */
  //student
  void removeStudentFromCourseByID(int id, Course course);
  /**
   * @return all students in a studentList
   */
  StudentList getAllStudents();
  /**
   * @return all students in a student list as an arrayList of type student
   */
  ArrayList<Student> getAllStudentsAsArrayList();
  /**
   * @param student student to add
   *
   * {@link Manager#addStudent(Student)}
   */
  void addStudent(Student student);
  /**
   * @param id student id
   *
   * {@link Manager#removeStudentByID(int)}
   */
  void removeStudentByID(int id);
  /**
   * @param classID students class ID
   * @return all students in a studentList from that class
   */
  StudentList getAllStudentsInClassID(String classID);
  /**
   * @param name students name
   * @return all students with the name parameter in a student list
   */
  StudentList getStudentsByName(String name);
  /**
   * @param id student id
   *
   * {@link Manager#removeTeacherFromSystemByID(String)}
   */
  void removeStudentFromSystemByID(int id);
  /**
   * @return all sessions from a sessionList
   *
   */
  //session
  SessionList getAllSessions();
  /**
   * @return all sessions from a sessionList as a Session arrayList
   */
  ArrayList<Session> getAllSessionsAsArrayList();
  /**
   * @param session session to be added
   *
   * {@link Manager#addSession(Session)}
   */
  void addSession(Session session);
  /**
   * @param session session to remove
   *
   * {@link Manager#removeSession(Session)}
   */
  void removeSession(Session session);
  /**
   * @return booked sessions in a sessionList as a Session arrayList
   */
  ArrayList<Session> getBookedSessions();
  /**
   * @return unbooked sessions in a sessionList as a Session arrayList
   */
  ArrayList<Session> getUnbookedSessions();
  /**
   * @return all rooms in a roomList
   */
  //room
  RoomList getAllRooms();
  /**
   * @return all rooms in a roomList as an arrayList
   *
   */
  ArrayList<Room> getAllRoomsAsArrayList();
  /**
   * @param room room to add
   *
   * {@link #addRoom(Room)}
   */
  void addRoom(Room room);
  /**
   * @param id room id
   * @return room that has the id from a roomList
   */
  Room getRoomByID(String id);
  /**
   * @param capacity room capacity
   * @return rooms with the capacity
   * {@link Manager#getRoomsBySize(int)}
   */
  RoomList getRoomsBySize(int capacity);
  /**
   * @param capacity room capacity
   * @return rooms with larger capacity than capacity parameter
   * {@link Manager#getRoomsBySizeBiggerThan(int)} (int)}
   */
  RoomList getRoomsBySizeBiggerThan(int capacity);
  /**
   * @return the rooms that are unitable
   * {@link Manager#getUnitableRooms()} (int)}
   */
  RoomList getUnitableRooms();
  /**
   * @param startTime session start time
   * @param endTime session end time
   * @return rooms available in a period
   * {@link Manager#getRoomsAvailableInPeriod(DateTime, DateTime)}
   */
  ArrayList<Room> getRoomsAvailableInPeriod(DateTime startTime, DateTime endTime);
  /**
   * @param startTime session start time
   * @param endTime session end time
   * @param capacity room's capacity
   * @return rooms available in a period by capacity
   * {@link Manager#getRoomsAvailableInPeriodBySize(int, DateTime, DateTime)} (DateTime, DateTime)}
   */
  ArrayList<Room> getRoomsAvailableInPeriodBySize(int capacity, DateTime startTime, DateTime endTime);

  /**
   * @param startTime session start time
   * @param endTime session end time
   * @param capacity room's capacity
   * @return rooms available in a period by capacity larger than the capacity parameter
   * {@link Manager#getRoomsAvailableInPeriodBySizeBiggerThan(int, DateTime, DateTime)} (int, DateTime, DateTime)} (DateTime, DateTime)}
   */
  ArrayList<Room> getRoomsAvailableInPeriodBySizeBiggerThan(int capacity, DateTime startTime, DateTime endTime);
  /**
   * @param startTime session start time
   * @param endTime session end time
   * @return rooms unitable in a period
   *
   * {@link Manager#getUnitableRoomsInPeriod(DateTime, DateTime)}
   */
  ArrayList<Room> getUnitableRoomsInPeriod(DateTime startTime, DateTime endTime);
  /**
   * @return all rooms booked
   *
   * {@link Manager#getAllBookedRooms()}
   */
  ArrayList<Room> getAllBookedRooms();
  /**
   * @return all rooms unbooked
   *
   * {@link Manager#getAllUnbookedRooms()}
   */
  ArrayList<Room> getAllUnbookedRooms();
  /**
   * @param idField student id
   *
   * {@link Manager#validateRemoveStudent(String)}
   */
  //GUI
  void validateRemoveStudent(String idField);
  /**
   * @param nameField student name
   * @param idField student id
   * @param classField student class
   *
   * {@link Manager#validateAddStudent(String, String, String)}
   */
  void validateAddStudent(String nameField, String idField, String classField);
  /**
   * @param name Teacher name
   * @param id Teacher id
   *
   * {@link Manager#validateAddTeacher(String, String)}
   */
  void validateAddTeacher(String name, String id);
  /**
   * @param id teacher id
   *
   * {@link Manager#validateRemoveTeacher(String)}
   */
  void validateRemoveTeacher(String id);
  /**
   * @param courseField courseID
   * @param sessionField session number
   * @param lessonField number of lessons for session
   *
   * {@link Manager#validateAddSession(String, String, String)}
   */
  void validateAddSession(String courseField, String sessionField, String lessonField);
  /**
   * @param lessonField number of lessons for session
   *
   * {@link Manager#validateEditSession(String)}
   */
  void validateEditSession(String lessonField);

  /**
   * @return empty toString method
   */
  String toString();
}
