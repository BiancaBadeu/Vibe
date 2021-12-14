package model;
import readers.*;

import java.util.ArrayList;

public interface Model
{
  //files
  void inputFiles() throws Exception;
  void writeFiles() throws Exception;
  //teacher
  TeacherList getAllTeachers();
  ArrayList<Teacher> getAllTeachersAsArrayList();
  void addTeacher(Teacher teacher);
  Teacher getTeacherByID(String id);
  TeacherList getTeachersByName(String name);
  void addTeacherToCourse(Teacher teacher, Course course);
  void removeTeacherFromCourseByID(String id, Course course);
  void removeTeacherFromSystemByID(String id);
  //course
  CourseList getAllCourses();
  ArrayList<Course> getAllCoursesAsArrayList();
  void addCourse(Course course);
  Course getCourseByID(String id);
  void calculateNumberOfLessonsForCourse(Course course);
  //student
  void removeStudentFromCourseByID(int id, Course course);
  StudentList getAllStudents();
  ArrayList<Student> getAllStudentsAsArrayList();
  void addStudent(Student student);
  void removeStudentByID(int id);
  StudentList getAllStudentsInClassID(String classID);
  StudentList getStudentsByName(String name);
  void removeStudentFromSystemByID(int id);
  //session
  SessionList getAllSessions();
  ArrayList<Session> getAllSessionsAsArrayList();
  void addSession(Session session);
  void removeSession(Session session);
  ArrayList<Session> getBookedSessions();
  ArrayList<Session> getUnbookedSessions();
  //room
  RoomList getAllRooms();
  ArrayList<Room> getAllRoomsAsArrayList();
  void addRoom(Room room);
  Room getRoomByID(String id);
  RoomList getRoomsBySize(int capacity);
  RoomList getRoomsBySizeBiggerThan(int capacity);
  RoomList getUnitableRooms();
  ArrayList<Room> getRoomsAvailableInPeriod(DateTime startTime, DateTime endTime);
  ArrayList<Room> getRoomsAvailableInPeriodBySize(int capacity, DateTime startTime, DateTime endTime);
  ArrayList<Room> getRoomsAvailableInPeriodBySizeBiggerThan(int capacity, DateTime startTime, DateTime endTime);
  ArrayList<Room> getUnitableRoomsInPeriod(DateTime startTime, DateTime endTime);
  ArrayList<Room> getAllBookedRooms();
  ArrayList<Room> getAllUnbookedRooms();
  //GUI
  void validateRemoveStudent(String idField);
  void validateAddStudent(String nameField, String idField, String classField);
  void validateAddTeacher(String name, String id);
  void validateRemoveTeacher(String id);
  void validateAddSession(String courseField, String sessionField, String lessonField);
  void validateEditSession(String lessonField);

  String toString();
}
