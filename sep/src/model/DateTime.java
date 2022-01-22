package model;

public class DateTime
{
  private int day;
  private int month;
  private int year;
  private int hour;
  private int minute;

  public DateTime(int day, int month, int year, int hour, int minute)
  {
    this.day = day;
    this.month = month;
    this.year = year;
    this.hour = hour;
    this.minute = minute;
  }

  public void setDay(int day)
  {
    this.day = day;
  }
  public void setMonth(int month)
  {
    this.month = month;
  }
  public void setYear(int year)
  {
    this.year = year;
  }
  public void setHour(int hour)
  {
    this.hour = hour;
  }
  public void setMinute(int minute)
  {
    this.minute = minute;
  }

  public int getDay()
  {
    return day;
  }
  public int getMonth()
  {
    return month;
  }
  public int getYear()
  {
    return year;
  }
  public int getHour()
  {
    return hour;
  }
  public int getMinute()
  {
    return minute;
  }

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

  public String toString()
  {
    return "" + this.day + "/" + this.month + "/" + this.year + "\n" + this.hour + ":" + this.minute;
  }

  public boolean equals(Object obj)
  {
    if(!(obj instanceof DateTime))
      return false;
    DateTime other = (DateTime) obj;
    return this.day == other.day && this.month == other.month && this.year == other.year
        && this.hour == other.hour && this.minute == other.minute;
  }
}
