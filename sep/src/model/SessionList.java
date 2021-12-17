package model;

import java.util.ArrayList;

/**
 * A class representing a list of sessions
 */
public class SessionList
{
  private ArrayList<Session> sessionList;

  /**
   *  A 0 argument constructor initializing instance variables
   */
  public SessionList()
  {
    this.sessionList = new ArrayList<>();
  }

  /**
   * @param session session to be added
   *
   * The session parameter is added to the list of sessions
   */
  public void addSession(Session session)
  {
    sessionList.add(session);
  }

  /**
   * @param session session to be removed
   *
   * The session parameter is removed from the session list
   */
  public void removeSession(Session session)
  {
    sessionList.remove(session);
  }

  /**
   * @param course course for lessons to be calculated
   * @return number of lessons for the course
   */
  public int calculateNumberOfLessonsForCourse(Course course)
  {
    return sessionList.get(0).calculateNumberOfLessonsForCourse(course);
  }

  /**
   * @return all sessions within the sessionList
   */
  public ArrayList<Session> getAllSessions()
  {
    return sessionList;
  }

  /**
   * @return all sessions as a sessionList
   */
  public SessionList getAllSessionsAsList()
  {
    SessionList sessions = new SessionList();
    for(int i=0;i<sessionList.size();i++)
    {
      sessions.addSession(sessionList.get(i));
    }
    return sessions;
  }

  /**
   * @return an arrayList of sessions that are booked within a sessionList
   */
  public ArrayList<Session> getBookedSessions()
  {
    ArrayList<Session> bookedSessions = new ArrayList<>();
    for(int i=0;i<sessionList.size();i++)
    {
      if(sessionList.get(i).getRoom() != null)
        bookedSessions.add(sessionList.get(i));
      else if(sessionList.get(i).getDateAndStartTime() != null)
        bookedSessions.add(sessionList.get(i));
    }
    return bookedSessions;
  }

  /**
   * @return a list of sessions that contains booked sessions
   */
  public SessionList getBookedSessionsAsList()
  {
    SessionList bookedSessions = new SessionList();
    for(int i=0;i<sessionList.size();i++)
    {
      if(sessionList.get(i).getRoom() != null)
        bookedSessions.addSession(sessionList.get(i));
      else if(sessionList.get(i).getDateAndStartTime() != null)
        bookedSessions.addSession(sessionList.get(i));
    }
    return bookedSessions;
  }

  /**
   * @return an arrayList of sessions that are unbooked from a sessionList
   */
  public ArrayList<Session> getUnbookedSessions()
  {
    ArrayList<Session> unbookedSessions = new ArrayList<>();
    for(int i=0;i<sessionList.size();i++)
    {
      if(sessionList.get(i).getRoom() == null && sessionList.get(i).getDateAndStartTime() == null)
        unbookedSessions.add(sessionList.get(i));
    }
    return unbookedSessions;
  }

  /**
   * @param dateAndStartTime date and start time of a session
   * @param dateAndEndTime date and end time of a session
   * @param rooms rooms
   * @return an arrayList of rooms that are available in a period between the DateTime objects
   */
  public ArrayList<Room> getRoomsAvailableInPeriod(DateTime dateAndStartTime, DateTime dateAndEndTime, RoomList rooms)
  {
    ArrayList<Room> roomsAvailable = new ArrayList<>();
    for(int i=0;i<rooms.getAllBookedRooms().size();i++)
    {
      int ok=1;
      for(int j=0;j<sessionList.size();j++)
      {
        if(sessionList.get(j).getRoom().equals(rooms.getAllBookedRooms().get(i)))
          if(sessionList.get(j).getDateAndStartTime().isAfter(dateAndStartTime) &&
              dateAndEndTime.isAfter(sessionList.get(j).getDateAndStartTime()))
              ok=0;
          else if(sessionList.get(j).getDateAndEndTime().isAfter(dateAndStartTime) &&
              dateAndEndTime.isAfter(sessionList.get(j).getDateAndEndTime()))
            ok=0;
      }
      if(ok==1)
        roomsAvailable.add(rooms.getAllBookedRooms().get(i));
    }
    for(int i=0;i<rooms.getAllUnbookedRooms().size();i++)
    {
      roomsAvailable.add(rooms.getAllUnbookedRooms().get(i));
    }
    return roomsAvailable;
  }

  /**
   * @param capacity rooms capacity
   * @param dateAndStartTime date and start time of a session
   * @param dateAndEndTime date and end time of a session
   * @param rooms rooms
   * @return an arrayList of rooms that are available in between the two DateTime objects and have the capacity
   */
  public ArrayList<Room> getRoomsAvailableInPeriodBySize(int capacity, DateTime dateAndStartTime, DateTime dateAndEndTime, RoomList rooms)
  {
    ArrayList<Room> roomsAvailableBySize = getRoomsAvailableInPeriod(dateAndStartTime, dateAndEndTime, rooms);
    for(int i=0;i<roomsAvailableBySize.size();i++)
    {
      if(roomsAvailableBySize.get(i).getCapacity() != capacity)
        roomsAvailableBySize.remove(roomsAvailableBySize.get(i));
    }
    return roomsAvailableBySize;
  }

  /**
   * @param capacity rooms capacity
   * @param dateAndStartTime date and start time for a session
   * @param dateAndEndTime date and end time for a session
   * @param rooms rooms
   * @return an arrayList of rooms that are available in between the two DateTime objects and have a capacity larger than
   * the parameter
   */
  public ArrayList<Room> getRoomsAvailableInPeriodBySizeBiggerThan(int capacity, DateTime dateAndStartTime, DateTime dateAndEndTime, RoomList rooms)
  {
    ArrayList<Room> roomsAvailableBySizeBiggerThan = getRoomsAvailableInPeriod(dateAndStartTime, dateAndEndTime, rooms);
    for(int i=0;i<roomsAvailableBySizeBiggerThan.size();i++)
    {
      if(roomsAvailableBySizeBiggerThan.get(i).getCapacity() < capacity)
        roomsAvailableBySizeBiggerThan.remove(roomsAvailableBySizeBiggerThan.get(i));
    }
    return roomsAvailableBySizeBiggerThan;
  }

  /**
   * @param dateAndStartTime date and start time of a session
   * @param dateAndEndTime date and end time of a session
   * @param rooms rooms
   * @return an arrayList of rooms that are available in between two DateTime objects and can be united
   */
  public ArrayList<Room> getUnitableRoomsInPeriod(DateTime dateAndStartTime, DateTime dateAndEndTime, RoomList rooms)
  {
    ArrayList<Room> unitableRooms = getRoomsAvailableInPeriod(dateAndStartTime, dateAndEndTime, rooms);
    for(int i=0;i<unitableRooms.size();i++)
    {
      if(unitableRooms.get(i).getUnitedWith() == null)
        unitableRooms.remove(unitableRooms.get(i));
      else
      {
        Room room = rooms.getRoomByID(unitableRooms.get(i).getUnitedWith());
        if(!(getRoomsAvailableInPeriod(dateAndStartTime, dateAndEndTime, rooms).contains(room)))
          unitableRooms.remove(unitableRooms.get(i));
      }
    }
    return unitableRooms;
  }

  /**
   * @param courseField course ID
   * @param sessionField session number
   * @param lessonField number of lessons for the session
   *
   * The course ID is checked to see if it begins with a number followed by a letter that has to be uppercase, next the course name should not be longer
   * than 4
   * The session number has to be a number
   * The number of lessons has to be a number
   */
    public void validateAddSession(String courseField, String sessionField, String lessonField)
    {
      String error="Error one of the fields has not valid data!";

      if(courseField.length()>6 || courseField.length()<5)
        throw new IllegalArgumentException(error);
      if(!(courseField.charAt(0)>='1' && courseField.charAt(0)<'7'))
        throw new IllegalArgumentException(error);
      for(int i=1;i<courseField.length(); i++)
      {
        if(!(courseField.charAt(i)>='A' && courseField.charAt(i)<='Z'))
          throw new IllegalArgumentException(error);
      }

      if(sessionField.length()>2)
        throw new IllegalArgumentException(error);
      if(!(sessionField.charAt(0)>'0' && sessionField.charAt(0)<='9'))
        throw new IllegalArgumentException(error);
      for (int i=1; i<sessionField.length();i++)
      {
        if(!(lessonField.charAt(i)>='0' && lessonField.charAt(i)<='9'))
          throw new IllegalArgumentException(error);
      }

      if(lessonField.length()>1)
        throw new IllegalArgumentException(error);
      if(!(lessonField.charAt(0)>'0' && lessonField.charAt(0)<='9'))
        throw new IllegalArgumentException(error);
    }

  /**
   * @param lessonField number of lessons for a session
   *
   * The number of lessons has to be a number
   */
  public void validateEditSession(String lessonField)
  {
    String error="Error not valid data!";

    if(lessonField.length()>3)
      throw new IllegalArgumentException(error);
    if(!(lessonField.charAt(0)>'0' && lessonField.charAt(0)<='9'))
      throw new IllegalArgumentException(error);
    for (int i=1; i<lessonField.length();i++)
    {
      if(!(lessonField.charAt(i)>='0' && lessonField.charAt(i)<='9'))
        throw new IllegalArgumentException(error);
    }
  }

  /**
   * @return a string with the sessionList content
   */
  public String toString()
  {
    return "Session list: " + sessionList;
  }
}
