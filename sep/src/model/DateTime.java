package model;

/**
 * A class representing a date
 * @see CourseList
 * @see RoomList
 */
public class DateTime
{
  private int day;
  private int month;
  private int year;
  private int hour;
  private int minute;

  /**
   * @param day day
   * @param month month
   * @param year year
   * @param hour hour
   * @param minute minute
   *
   * A 5 argument constructor that initializes the parameters
   */
  public DateTime(int day, int month, int year, int hour, int minute)
  {
    this.day = day;
    this.month = month;
    this.year = year;
    this.hour = hour;
    this.minute = minute;
  }

  /**
   * @param day day
   *
   * A setter for the day
   */
  public void setDay(int day)
  {
    this.day = day;
  }
  /**
   * @param month month
   *
   * A setter for the month
   */
  public void setMonth(int month)
  {
    this.month = month;
  }
  /**
   * @param year year
   *
   * A setter for the year
   */
  public void setYear(int year)
  {
    this.year = year;
  }
  /**
   * @param hour hour
   *
   * A setter for the hour
   */
  public void setHour(int hour)
  {
    this.hour = hour;
  }
  /**
   * @param minute minute
   *
   * A setter for the minute
   */
  public void setMinute(int minute)
  {
    this.minute = minute;
  }

  /**
   * @return day
   */
  public int getDay()
  {
    return day;
  }
  /**
   * @return month
   */
  public int getMonth()
  {
    return month;
  }
  /**
   * @return year
   */
  public int getYear()
  {
    return year;
  }
  /**
   * @return hour
   */
  public int getHour()
  {
    return hour;
  }
  /**
   * @return minute
   */
  public int getMinute()
  {
    return minute;
  }

  /**
   * @param time DateTime object
   * @return a check if the date is after the DateTime object
   */
  public boolean isAfter(DateTime time)
  {
    if(this.year>time.year)
      return true;
    else if(this.year<time.year)
      return false;
    else
    {
      if(this.month>time.month)
        return true;
      else if(this.month<time.month)
        return false;
      else
      {
        if(this.day>time.day)
          return true;
        else if(this.day<time.day)
          return false;
        else
        {
          if(this.hour>time.hour)
            return true;
          else if(this.hour<time.hour)
            return false;
          else
          {
            if(this.minute>time.minute)
              return true;
            else
              return false;
          }
        }
      }
    }
  }

  /**
   * @return a string with the variables separated by /
   */
  public String toString()
  {
    return "" + this.day + "/" + this.month + "/" + this.year + "\n" + this.hour + ":" + this.minute;
  }

  /**
   * @param obj other DateTime object
   * @return a check if the object is equal to the other DateTime object
   */
  public boolean equals(Object obj)
  {
    if(!(obj instanceof DateTime))
      return false;
    DateTime other = (DateTime) obj;
    return this.day == other.day && this.month == other.month && this.year == other.year
        && this.hour == other.hour && this.minute == other.minute;
  }
}
