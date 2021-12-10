package model;
import readers.*;

import java.util.ArrayList;

public interface Model
{
  void inputFiles() throws Exception;
  TeacherList getAllTeachers();
  ArrayList<Teacher> getAllTeachersAsArrayList();
  void addTeacher(Teacher teacher);
  void removeTeacherFromSystemByID(String id);
  void removeStudentFromSystemByID(int id);
  Teacher getTeacherByID(String id);
  TeacherList getTeachersByName(String name);
  void addTeacherToCourse(Teacher teacher, Course course);
  void removeTeacherFromCourseByID(String id, Course course);
  CourseList getAllCourses();
  ArrayList<Course> getAllCoursesAsArrayList();
  void addCourse(Course course);
  Course getCourseByID(String id);
  void calculateNumberOfLessonsForCourse(Course course);
  void removeStudentFromCourseByID(int id, Course course);
  StudentList getAllStudents();
  void validateRemoveStudent(String idField);
  void validateAddStudent(String nameField, String idField, String classField);
  void addStudent(Student student);
  void removeStudentByID(int id);
  StudentList getAllStudentsInClassID(String classID);
  StudentList getStudentsByName(String name);
  SessionList getAllSessions();
  ArrayList<Session> getAllSessionsAsArrayList();
  void addSession(Session session);
  void removeSession(Session session);
  ArrayList<Session> getBookedSessions();
  ArrayList<Session> getUnbookedSessions();
  RoomList getAllRooms();
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
  String toString();
}
