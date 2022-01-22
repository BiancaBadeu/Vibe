package model;

import java.util.ArrayList;

public class SessionList
{
  private ArrayList<Session> sessionList;

  public SessionList()
  {
    this.sessionList = new ArrayList<>();
  }

  public void addSession(Session session)
  {
    sessionList.add(session);
  }
  public void removeSession(Session session)
  {
    sessionList.remove(session);
  }

  public int calculateNumberOfLessonsForCourse(Course course)
  {
    return sessionList.get(0).calculateNumberOfLessonsForCourse(course);
  }
  public ArrayList<Session> getAllSessions()
  {
    return sessionList;
  }

  public SessionList getAllSessionsAsList()
  {
    SessionList sessions = new SessionList();
    for(int i=0;i<sessionList.size();i++)
    {
      sessions.addSession(sessionList.get(i));
    }
    return sessions;
  }

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
  public ArrayList<Session> getBookedSessionsWithRoom()
  {
    ArrayList<Session> booked = getBookedSessions();
    ArrayList<Session> array = new ArrayList<>();
    for(int i=0;i<booked.size();i++)
    {
      if(booked.get(i).getRoom() != null)
        array.add(booked.get(i));
    }
    return array;
  }
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
  public ArrayList<Session> getUnbookedSessionsForCourse(Course course)
  {
    ArrayList<Session> unbookedSessions = getUnbookedSessions();
    ArrayList<Session> unbookedSessionByCourse = new ArrayList<>();
    for(int i=0;i<unbookedSessions.size();i++)
    {
      if(unbookedSessions.get(i).getCourse().equals(course))
        unbookedSessionByCourse.add(unbookedSessions.get(i));
    }
    return unbookedSessionByCourse;
  }

  public ArrayList<Room> getRoomsAvailableInPeriod(DateTime dateAndStartTime, DateTime dateAndEndTime, RoomList rooms)
  {
    ArrayList<Room> roomsAvailable = new ArrayList<>();
    for(int i=0;i<rooms.getAllBookedRooms().size();i++)
    {
      int ok=1;
      for(int j=0;j< getBookedSessionsWithRoom().size();j++)
      {
        if(getBookedSessionsWithRoom().get(j).getRoom().equals(rooms.getAllBookedRooms().get(i)))
        {
          if (getBookedSessionsWithRoom().get(j).getDateAndStartTime().isAfter(dateAndStartTime) && dateAndEndTime.isAfter(
              getBookedSessionsWithRoom().get(j).getDateAndStartTime()))
            ok = 0;
          else if (getBookedSessionsWithRoom().get(j).getDateAndEndTime().isAfter(dateAndStartTime) && dateAndEndTime.isAfter(
              getBookedSessionsWithRoom().get(j).getDateAndEndTime()))
            ok = 0;
        }
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
  public ArrayList<Room> getRoomsAvailableInPeriodBySizeBiggerThan(int capacity, DateTime dateAndStartTime, DateTime dateAndEndTime, RoomList rooms)
  {
    ArrayList<Room> roomsAvailableBySizeBiggerThan = getRoomsAvailableInPeriod(dateAndStartTime, dateAndEndTime, rooms);
    ArrayList<Room> array = new ArrayList<>();
    for(int i=0;i<roomsAvailableBySizeBiggerThan.size();i++)
    {
      if(roomsAvailableBySizeBiggerThan.get(i).getCapacity() >= capacity)
        array.add(roomsAvailableBySizeBiggerThan.get(i));
    }
    return array;
  }
  public ArrayList<Room> getRoomsAvailableInPeriodBySizeBiggerThanUnitable(int capacity, DateTime startTime, DateTime endTime, RoomList rooms)
  {
    ArrayList<Room> roomsAvailable = getRoomsAvailableInPeriodBySizeBiggerThan(capacity, startTime, endTime, rooms);
    ArrayList<Room> array = new ArrayList<>();
    for(int i=0;i<roomsAvailable.size();i++)
    {
      if(roomsAvailable.get(i).getUnitedWith() != null)
        array.add(roomsAvailable.get(i));
    }
    return array;
  }
  public ArrayList<Room> getRoomsAvailableInPeriodBySizeBiggerThanNotUnitable(int capacity, DateTime startTime, DateTime endTime, RoomList rooms)
  {
    ArrayList<Room> roomsAvailable = getRoomsAvailableInPeriodBySizeBiggerThan(capacity, startTime, endTime, rooms);
    ArrayList<Room> array = new ArrayList<>();
    for(int i=0;i<roomsAvailable.size();i++)
    {
      if(roomsAvailable.get(i).getUnitedWith() == null)
        array.add(roomsAvailable.get(i));
    }
    return array;
  }
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

  public String toString()
  {
    return "Session list: " + sessionList;
  }
}
