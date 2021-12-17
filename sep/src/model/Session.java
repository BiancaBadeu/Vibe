package model;

/**
 * A class representing a session
 */
public class Session
{
  private int number;
  private Course course;
  private int numberOfLessonsInSession;
  private Room room;
  private int numberOfLessonsForCourse;
  private int getNumberOfLessonsRemaining;
  private DateTime dateAndStartTime;
  private DateTime dateAndEndTime;

  /**
   * @param number session number
   * @param course session's course
   *
   * A 2 argument constructor that initializes all instance variables
   */
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
  }

  /**
   * @param number session number
   * @param course session's course
   * @param numberOfLessonsInSession number of lessons for session
   *
   * A 3 argument constructor that initializes all instance variables and sets a number
   * of lessons for the session
   */
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
  }

  /**
   * @param number session number
   * @param course session's course
   * @param numberOfLessonsInSession number of lessons for the session
   * @param dateAndStartTime date and start time of the session
   * @param dateAndEndTime date and end time of the session
   *
   * A 5 argument constructor that initializes all instance variables and sets the date and start time and the end time
   * of a session
   */
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
  }

  /**
   * @param number session number
   * @param course session's course
   * @param numberOfLessonsInSession number of lessons for a session
   * @param room session's room
   * @param dateAndStartTime date and start time of the session
   * @param dateAndEndTime date and end time of the session
   *
   * A 6 argument constructor that initializes all instance variables and sets a room for the session
   */
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
  }

  /**
   * @param course courseID
   * @return number of lessons for the course based on ects
   */
  public int calculateNumberOfLessonsForCourse(Course course)
  {
    int s = (int) Math.floor(7.2 * course.getEcts());
    return s;
  }

  /**
   * @param numberOfLessonsInSession number of lessons for the session
   * @return end time of a session based on the start time and number of lessons
   * each lesson=45 minutes
   */
  public DateTime calculateEndTimeForSession(int numberOfLessonsInSession)
  {
    int min = numberOfLessonsInSession * 45;
    int hour = 0;
    int day = dateAndStartTime.getDay();
    int month = dateAndStartTime.getMonth();
    int year = dateAndStartTime.getYear();
    while(min>59)
    {
      hour++;
      min/=60;
    }
    int newMin = dateAndStartTime.getMinute() + min;
    int newHour = dateAndStartTime.getHour() + hour;
    if(newMin > 59)
    {
      newHour++;
      newMin = newMin - 60;
    }
    if(newHour > 23)
    {
      day++;
      newHour = newHour - 24;
    }
    if(day>getMaxNumberOfDaysForMonth(month, year))
    {
      month++;
      day = day - getMaxNumberOfDaysForMonth(month, year);
    }
    if(month>12)
    {
      year++;
      month = month - 12;
    }
    DateTime dateAndEndTime = new DateTime(day, month, year, newHour, newMin);
    this.dateAndEndTime = dateAndEndTime;
    return dateAndEndTime;
  }

  /**
   * @param year year
   * @return if a year is a leap year
   */
  public boolean isLeapYear(int year)
  {
    return year % 4 == 0 && (year % 100 != 0 || year % 400 ==0);
  }

  /**
   * @param month month
   * @param year year
   * @return based on the {@link #isLeapYear(int)} determine the number of days a month has
   */
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

  /**
   * @return number of lessons for the course
   */
  public int getNumberOfLessonsForCourse()
  {
    return numberOfLessonsForCourse;
  }

  /**
   * @return number of lessons remaining
   */
  public int getGetNumberOfLessonsRemaining()
  {
    return getNumberOfLessonsRemaining;
  }

  /**
   * @return number of lessons in a session object
   */
  public int getNumberOfLessonsInSession()
  {
    return numberOfLessonsInSession;
  }

  /**
   * @param number number of lessons to set the session to
   *
   * The session number of lesson is updated to the parameter and the lessons remaining are updated
   */
  public void setNumberOfLessonsInSession(int number)
  {
    this.numberOfLessonsInSession = number;
    this.getNumberOfLessonsRemaining = numberOfLessonsForCourse - numberOfLessonsInSession;
    if(dateAndStartTime != null)
      calculateEndTimeForSession(numberOfLessonsInSession);
  }

  /**
   *  Adds a lesson the current number of lesson for a session and updates the remaining number of lessons
   */
  public void addLessonToSession()
  {
    this.numberOfLessonsInSession++;
    if(dateAndStartTime != null)
      calculateEndTimeForSession(numberOfLessonsInSession);
  }

  /**
   * @param room room
   *
   * Sets a room object
   */
  public void setRoom(Room room)
  {
    this.room = room;
  }

  /**
   * @return room object
   */
  public Room getRoom()
  {
    return room;
  }

  /**
   * @return session number
   */
  public int getNumber()
  {
    return number;
  }

  /**
   * @return session's course
   */
  public Course getCourse()
  {
    return course;
  }

  /**
   * @param dateAndStartTime date and start time
   *
   * sets the date and the start time of a session and calculates the end time
   */
  public void setDateAndStartTime(DateTime dateAndStartTime)
  {
    this.dateAndStartTime = dateAndStartTime;
    calculateEndTimeForSession(numberOfLessonsInSession);
  }

  /**
   * @return date and start time of a session
   */
  public DateTime getDateAndStartTime()
  {
    return dateAndStartTime;
  }

  /**
   * @return date and end time of a session
   */
  public DateTime getDateAndEndTime()
  {
    return dateAndEndTime;
  }

  /**
   * @return string containing all the instance variables and getter for the number of lessons remaining, number of lessons for the session and
   * number of lessons for the course
   */
  public String toString()
  {
    return "Number: " + number + ", course: " + course + ", number of lessons in session: " + numberOfLessonsInSession
        + ", room: " + room + ", number of lessons for course: " + numberOfLessonsForCourse + ", number of lessons remaining: "
        + getNumberOfLessonsRemaining + ", start: " + dateAndStartTime.toString() + ", end: " + dateAndEndTime.toString();
  }

  /**
   * @param obj other Session object
   * @return if the session object is equal to another session object
   */
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
}