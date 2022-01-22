package model;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Comparator;

public class Session implements Comparable<Session>
{
  private int number;
  private Course course;
  private int numberOfLessonsInSession;
  private Room room;
  private int numberOfLessonsForCourse;
  private int getNumberOfLessonsRemaining;
  private DateTime dateAndStartTime;
  private DateTime dateAndEndTime;
  private int dayNumber;


  public Session(int number, Course course)
  {
    this.number = number;
    this.course = course;
    this.numberOfLessonsInSession = 0;
    this.room = null;
    this.numberOfLessonsForCourse = calculateNumberOfLessonsForCourse(course);
    this.getNumberOfLessonsRemaining = numberOfLessonsForCourse - numberOfLessonsInSession;
    this.dateAndStartTime = null;
    this.dateAndEndTime = null;
    this.dayNumber = 0;
  }
  public Session(int number, Course course, int numberOfLessonsInSession)
  {
    this.number = number;
    this.course = course;
    this.numberOfLessonsInSession = numberOfLessonsInSession;
    this.room = null;
    this.numberOfLessonsForCourse = calculateNumberOfLessonsForCourse(course);
    this.getNumberOfLessonsRemaining = numberOfLessonsForCourse - numberOfLessonsInSession;
    this.dateAndStartTime = null;
    this.dateAndEndTime = null;
    this.dayNumber = 0;
  }
  public Session(int number, Course course, int numberOfLessonsInSession, DateTime dateAndStartTime, DateTime dateAndEndTime)
  {
    this.number = number;
    this.course = course;
    this.numberOfLessonsInSession = numberOfLessonsInSession;
    this.room = null;
    this.numberOfLessonsForCourse = calculateNumberOfLessonsForCourse(course);
    this.getNumberOfLessonsRemaining = numberOfLessonsForCourse - numberOfLessonsInSession;
    this.dateAndStartTime = dateAndStartTime;
    this.dateAndEndTime = dateAndEndTime;
    this.dayNumber = Session.getDayNumber(dateAndStartTime);
  }
  public Session(int number, Course course, int numberOfLessonsInSession, Room room, DateTime dateAndStartTime, DateTime dateAndEndTime)
  {
    this.number = number;
    this.course = course;
    this.numberOfLessonsInSession = numberOfLessonsInSession;
    this.room = room;
    this.numberOfLessonsForCourse = calculateNumberOfLessonsForCourse(course);
    this.getNumberOfLessonsRemaining = numberOfLessonsForCourse - numberOfLessonsInSession;
    this.dateAndStartTime = dateAndStartTime;
    this.dateAndEndTime = dateAndEndTime;
    this.dayNumber = Session.getDayNumber(dateAndStartTime);
  }

  public int calculateNumberOfLessonsForCourse(Course course)
  {
    int s = (int) Math.floor(7.2 * course.getEcts());
    return s;
  }

  public DateTime calculateEndTimeForSession(int numberOfLessonsInSession)
  {
      int min = numberOfLessonsInSession * 45 + (numberOfLessonsInSession - 1) * 10;
      int hour = 0;
      int day = dateAndStartTime.getDay();
      int month = dateAndStartTime.getMonth();
      int year = dateAndStartTime.getYear();
      while (min > 59)
      {
        hour++;
        min -= 60;
      }
      int newMin = dateAndStartTime.getMinute() + min;
      int newHour = dateAndStartTime.getHour() + hour;
      if (newMin > 59)
      {
        newHour += 1;
        newMin -= 60;
      }
      if (newHour > 23)
      {
        day++;
        newHour -= 24;
      }
      if (day > getMaxNumberOfDaysForMonth(month, year))
      {
        month++;
        day -= getMaxNumberOfDaysForMonth(month, year);
      }
      if (month > 12)
      {
        year++;
        month -= 12;
      }
      DateTime dateAndEndTimeNew = new DateTime(day, month, year, newHour,
          newMin);
      this.dateAndEndTime = dateAndEndTimeNew;
      return dateAndEndTime;
  }

  public boolean isLeapYear(int year)
  {
    return year % 4 == 0 && (year % 100 != 0 || year % 400 ==0);
  }

  public int getMaxNumberOfDaysForMonth(int month, int year)
  {
    switch (month)
    {
      case 2:
        if (isLeapYear(year))
          return 29;
        return 28;

      case 4:
      case 6:
      case 9:
      case 11:
        return 30;
      default:
        return 31;
    }
  }

  public int getNumberOfLessonsForCourse()
  {
    return numberOfLessonsForCourse;
  }
  public int getGetNumberOfLessonsRemaining()
  {
    return getNumberOfLessonsRemaining;
  }
  public int getNumberOfLessonsInSession()
  {
    return numberOfLessonsInSession;
  }
  public void setNumberOfLessonsInSession(int number)
  {
    this.numberOfLessonsInSession = number;
    this.getNumberOfLessonsRemaining = numberOfLessonsForCourse - numberOfLessonsInSession;
    if(dateAndStartTime != null)
      calculateEndTimeForSession(numberOfLessonsInSession);
  }
  public void addLessonToSession()
  {
    this.numberOfLessonsInSession++;
    if(dateAndStartTime != null)
      calculateEndTimeForSession(numberOfLessonsInSession);
  }
  public void setDayNumber(int dayNumber)
  {
    this.dayNumber = dayNumber;
  }

  public void setRoom(Room room)
  {
    this.room = room;
  }

  public Room getRoom()
  {
    return room;
  }

  public int getNumber()
  {
    return number;
  }

  public Course getCourse()
  {
    return course;
  }

  public void setDateAndStartTime(DateTime dateAndStartTime)
  {
    this.dateAndStartTime = dateAndStartTime;
    calculateEndTimeForSession(numberOfLessonsInSession);
  }
  public DateTime getDateAndStartTime()
  {
    return dateAndStartTime;
  }
  public DateTime getDateAndEndTime()
  {
    return dateAndEndTime;
  }

  public static int getDayNumber(DateTime date)
  {
    LocalDate date1 = LocalDate.of(date.getYear(), date.getMonth(), date.getDay());
    DayOfWeek day = date1.getDayOfWeek();
    return day.getValue();
  }
  public int getDayNumber()
  {
    return this.dayNumber;
  }

  public String toString()
  {
    return "Day of week: " + dayNumber + "Number: " + number + ", course: " + course + ", number of lessons in session: " + numberOfLessonsInSession
        + ", room: " + room + ", number of lessons for course: " + numberOfLessonsForCourse + ", number of lessons remaining: "
        + getNumberOfLessonsRemaining + ", start: " + dateAndStartTime.toString() + ", end: " + dateAndEndTime.toString();
  }

  public boolean equals(Object obj)
  {
    if(!(obj instanceof Session))
      return false;
    Session other = (Session) obj;
    if((room == null && other.room != null) ||(room != null && other.room == null))
      return false;
    else if((dateAndStartTime == null && other.dateAndStartTime != null) || (dateAndStartTime != null && other.dateAndStartTime == null))
      return false;
    else if((dateAndEndTime == null && other.dateAndEndTime != null) || (dateAndEndTime != null && other.dateAndEndTime == null))
      return false;
    if(room == null)
      if(dateAndStartTime == null)
        if(dateAndEndTime == null)
        {
          return number == other.number && course.equals(other.course) && numberOfLessonsInSession == other.numberOfLessonsInSession
          && numberOfLessonsForCourse == other.numberOfLessonsForCourse && getNumberOfLessonsRemaining == other.getNumberOfLessonsRemaining;
        }
      else
      {
        return number == other.number && course.equals(other.course) && numberOfLessonsInSession == other.numberOfLessonsInSession
            && numberOfLessonsForCourse == other.numberOfLessonsForCourse && getNumberOfLessonsRemaining == other.getNumberOfLessonsRemaining
            && dateAndEndTime.equals(other.dateAndEndTime);
      }
      else
        {
          return number == other.number && course.equals(other.course) && numberOfLessonsInSession == other.numberOfLessonsInSession
              && numberOfLessonsForCourse == other.numberOfLessonsForCourse && getNumberOfLessonsRemaining == other.getNumberOfLessonsRemaining
              && dateAndEndTime.equals(other.dateAndEndTime) && dateAndStartTime.equals(other.dateAndStartTime);
        }
      else
    {
      return number == other.number && course.equals(other.course) && numberOfLessonsInSession == other.numberOfLessonsInSession
          && numberOfLessonsForCourse == other.numberOfLessonsForCourse && getNumberOfLessonsRemaining == other.getNumberOfLessonsRemaining
          && dateAndEndTime.equals(other.dateAndEndTime) && dateAndStartTime.equals(other.dateAndStartTime);
    }
  }

  public int compareTo(Session s) {
    if(s.getDateAndStartTime().isAfter(this.getDateAndStartTime()))
      return -1;
    else if(this.getDateAndStartTime().isAfter(s.getDateAndStartTime()))
      return 1;
    else return 0;
  }
}