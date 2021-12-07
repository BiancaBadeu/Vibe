import model.*;

public class test
{
  public static void main(String[] args)
  {
    StudentList studentList = new StudentList();
    int id = 315200;
    for(int i=1;i<=20;i++)
    {
      Student student = new Student("name", id+i, "1Y");
      studentList.addStudent(student);
    }
    for(int i=1;i<=20;i++)
    {
      Student student = new Student("name1", id+21+i, "1X");
      studentList.addStudent(student);
    }

    TeacherList teacherList = new TeacherList();
    Teacher teacher1 = new Teacher("Steffen", "SVA");
    Teacher teacher2 = new Teacher("Mona", "MONA");
    Teacher teacher3 = new Teacher("Lile", "LL");
    Teacher teacher4 = new Teacher("Richard", "RWD");
    teacherList.addTeacher(teacher1);
    teacherList.addTeacher(teacher2);
    teacherList.addTeacher(teacher3);
    teacherList.addTeacher(teacher4);

    Course course = new Course("1YSDJ", 10);
    String classID = "";
    classID += course.getCourseID().charAt(0);
    classID += course.getCourseID().charAt(1);
    course.setStudentList(studentList.getStudentsByClassID(classID));
    course.addTeacher(teacher1);
    course.addTeacher(teacher2);

    System.out.println(course);
  }
}
