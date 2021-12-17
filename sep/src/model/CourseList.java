package model;

import java.util.ArrayList;

/**
 * A class representing a list of courses
 * @see Course
 */
public class CourseList
{
  private ArrayList<Course> courseList;

  /**
   * A 0 argument constructor that initializes an arrayList of type CourseList
   */
  public CourseList()
  {
    this.courseList = new ArrayList<>();
  }

  /**
   * @param course the course to be added
   *
   * The method adds a Course object to a list of courses
   */
  public void addCourse(Course course)
  {
    courseList.add(course);
  }

  /**
   * @return all the courses contained in the CourseList
   */
  public CourseList getAllCourses()
  {
    CourseList courses = new CourseList();
    for(int i=0;i<courseList.size();i++)
    {
      courses.addCourse(courseList.get(i));
    }
    return courses;
  }

  /**
   * @return courseList courseList as an array
   *
   * The method returns the courseList variable as an arrayList
   */
  public ArrayList<Course> getAllCoursesAsArrayList()
  {
    return courseList;
  }

  /**
   * @param id the id of a course
   * @return the details of the course id from the courseList
   */
  public Course getCourseByID(String id)
  {
    for(int i=0;i<courseList.size();i++)
    {
      if(id.equals(courseList.get(i).getCourseID()))
        return courseList.get(i);
    }
    return null;
  }

  /**
   * @param teacher teacher to be added
   * @param courseID id of the teacher to be added
   *
   * The method adds a teacher to the course by taking parameters
   */
  public void addTeacherToCourse(Teacher teacher, String courseID)
  {
    Course course = getCourseByID(courseID);
    course.addTeacher(teacher);
  }

  /**
   * @param teacherID id of the teacher to remove
   * @param courseID courseID to remove the teacher from
   *
   * The method removes the teacher from the course by using the teacher ID
   */
  public void removeTeacherFromCourseByID(String teacherID, String courseID)
  {
    Course course = getCourseByID(courseID);
    course.removeTeacherByIDFromCourse(teacherID);
  }

  /**
   * @param studentID id of the student to remove
   * @param courseID id of the course to remove the student from
   *
   * Method to remove the student from a specific course by the courseID and studentID
   */
  public void removeStudentFromCourseByID(int studentID, String courseID)
  {
    Course course = getCourseByID(courseID);
    course.removeStudentByIDFromCourse(studentID);
  }

  /**
   * @return A string of the courses contained in the courseList
   */
  public String toString()
  {
    return "Course list: " + courseList;
  }

}
