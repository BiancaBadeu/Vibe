package model;

public class Course
{
  private String courseID;
  private StudentList students;
  private TeacherList teachers;
  private int ects;

  public Course(String courseID, int ects)
  {
    this.courseID = courseID;
    this.ects = ects;
    this.students = new StudentList();
    this.teachers = new TeacherList();
  }

  public void setStudentList(StudentList students)
  {
    this.students = students;
  }
  public void addTeacher(Teacher teacher)
  {
    this.teachers.addTeacher(teacher);
  }

  public String getCourseID()
  {
    return courseID;
  }
  public int getEcts()
  {
    return ects;
  }
  public StudentList getStudentList()
  {
    return students;
  }
  public TeacherList getTeacherList()
  {
    return teachers;
  }

  public void removeTeacherByIDFromCourse(String id)
  {
      teachers.removeTeacherByID(id);
  }
  public void removeStudentByIDFromCourse(int id)
  {
      students.removeStudentByID(id);
  }

  public String toString()
  {
    return "\nCourse: " + courseID +  ", ECTS: " + ects + "\n" + students + "\n" + teachers;
  }

  public boolean equals(Object obj)
  {
    if(!(obj instanceof Course))
      return false;
    Course other = (Course) obj;
    return this.courseID.equals(other.courseID) && this.ects == other.ects
        && this.students.equals(other.students) && this.teachers.equals(other.teachers);
  }
}
