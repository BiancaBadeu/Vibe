package model;

import java.util.ArrayList;

public class CourseList
{
  private ArrayList<Course> courseList;

  public CourseList()
  {
    this.courseList = new ArrayList<>();
  }


  public void addCourse(Course course)
  {
    courseList.add(course);
  }
  public CourseList getAllCourses()
  {
    CourseList courses = new CourseList();
    for(int i=0;i<courseList.size();i++)
    {
      courses.addCourse(courseList.get(i));
    }
    return courses;
  }
  public ArrayList<Course> getAllCoursesAsArrayList()
  {
    return courseList;
  }
  public Course getCourseByID(String id)
  {
    for(int i=0;i<courseList.size();i++)
    {
      if(id.equals(courseList.get(i).getCourseID()))
        return courseList.get(i);
    }
    return null;
  }

  public void addTeacherToCourse(Teacher teacher, String courseID)
  {
    Course course = getCourseByID(courseID);
    course.addTeacher(teacher);
  }

  public void removeTeacherFromCourseByID(String teacherID, String courseID)
  {
    Course course = getCourseByID(courseID);
    course.removeTeacherByIDFromCourse(teacherID);
  }
  public void removeStudentFromCourseByID(int studentID, String courseID)
  {
    Course course = getCourseByID(courseID);
    course.removeStudentByIDFromCourse(studentID);
  }

  public String toString()
  {
    return "Course list: " + courseList;
  }

}
