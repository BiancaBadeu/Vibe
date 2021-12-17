package model;

/**
 * A class representing the course for a courseList
 * @see CourseList
 */
public class Course
{
  private String courseID;
  private StudentList students;
  private TeacherList teachers;
  private int ects;

  /**
   * @param courseID the ID of a course
   * @param ects number of ects of the course
   *
   * A 2 argument constructor initializing the two arguments and student and teacher lists
   * @see StudentList
   * @see TeacherList
   */
  public Course(String courseID, int ects)
  {
    this.courseID = courseID;
    this.ects = ects;
    this.students = new StudentList();
    this.teachers = new TeacherList();
  }

  /**
   * @param students list of student of the course
   *
   * A method to set the list of student for the course
   */
  public void setStudentList(StudentList students)
  {
    this.students = students;
  }

  /**
   * @param teacher teacher to be added
   *
   * The method adds the parameter to the listOfTeachers
   */
  public void addTeacher(Teacher teacher)
  {
    this.teachers.addTeacher(teacher);
  }

  /**
   * @param student student to be added
   *
   * The method adds the parameter to the listOfStudents
   */
  public void addStudent(Student student){this.students.addStudent(student);}

  /**
   * @return courseID returns the ID of the course
   */
  public String getCourseID()
  {
    return courseID;
  }

  /**
   * @return ects the amount of ects of the course
   */
  public int getEcts()
  {
    return ects;
  }

  /**
   * @return students returns the list of students for the course
   */
  public StudentList getStudentList()
  {
    return students;
  }

  /**
   * @return teachers returns the list of teachers for the course
   */
  public TeacherList getTeacherList()
  {
    return teachers;
  }

  /**
   * @param id id of the teacher to remove
   *
   * The method removes the teacher from the list of teachers from the course
   */
  public void removeTeacherByIDFromCourse(String id)
  {
      teachers.removeTeacherByID(id);
  }

  /**
   * @param id id of the student to remove
   *
   * The method removes the student from the list of students from the course
   */
  public void removeStudentByIDFromCourse(int id)
  {
      students.removeStudentByID(id);
  }

  /**
   * @return a string with the courseID, ects, student and teacher list
   */
  public String toString()
  {
    return "\nCourse: " + courseID +  ", ECTS: " + ects + "\n" + students + "\n" + teachers;
  }

  /**
   * @param obj Other course object
   * @return check if the course object is equal to the other one
   */
  public boolean equals(Object obj)
  {
    if(!(obj instanceof Course))
      return false;
    Course other = (Course) obj;
    return this.courseID.equals(other.courseID) && this.ects == other.ects
        && this.students.equals(other.students) && this.teachers.equals(other.teachers);
  }
}
