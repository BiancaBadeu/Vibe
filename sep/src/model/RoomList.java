package model;

import java.util.ArrayList;

/**
 * A class representing a list of rooms
 */
public class RoomList
{
   private ArrayList<Room> roomList;

   /**
    * A 0 argument constructor that initializes an instance variable
    */
   public RoomList()
   {
      this.roomList = new ArrayList<>();
   }

   /**
    * @return the rooms contained in a roomList
    */
   public RoomList getAllRooms()
   {
      RoomList rooms = new RoomList();
      for(int i=0;i<roomList.size();i++)
         rooms.addRoom(roomList.get(i));

      return rooms;
   }

   /**
    * @return the rooms contained in a roomList as an arrayList
    */
   public ArrayList<Room> getAllRoomsAsArrayList()
   {
      return roomList;
   }

   /**
    * @param room room to be added
    *
    * The room parameter is added to a roomList
    */
   public void addRoom(Room room)
   {
      roomList.add(room);
   }

   /**
    * @param id room's ID
    * @return the room that has the id parameter
    */
   public Room getRoomByID(String id)
   {
      for(int i=0;i<roomList.size();i++)
      {
         if(id.equals(roomList.get(i).getId()))
            return roomList.get(i);
      }
      return null;
   }

   /**
    * @param capacity rooms capacity
    * @return rooms from the roomList that have the capacity parameter
    */
   public RoomList getRoomsBySize(int capacity)
   {
      RoomList roomsBySize = new RoomList();
      for(int i=0;i<roomList.size();i++)
      {
         if(capacity == roomList.get(i).getCapacity())
            roomsBySize.addRoom(roomList.get(i));
      }
      return roomsBySize;
   }

   /**
    * @param capacity rooms capacity
    * @return rooms in the roomList that have a capacity larger than the parameter
    */
   public RoomList getRoomsBySizeBiggerThan(int capacity)
   {
      RoomList roomsBySizeBiggerThan = new RoomList();
      for(int i=0;i<roomList.size();i++)
      {
         if(capacity <= roomList.get(i).getCapacity())
            roomsBySizeBiggerThan.addRoom(roomList.get(i));
      }
      return roomsBySizeBiggerThan;
   }

   /**
    * @return rooms in the roomList that can be united
    */
   public RoomList getUnitableRooms()
   {
      RoomList unitableRooms = new RoomList();
      for(int i=0;i<roomList.size();i++)
      {
         if(roomList.get(i).getUnitedWith() != null)
            unitableRooms.addRoom(roomList.get(i));
      }
      return unitableRooms;
   }

   /**
    * @param room room
    * @return room that the room can be united with
    */
   public Room getRoomUnitedWith(Room room)
   {
      RoomList unitableRooms = getUnitableRooms();

      for(int i=0;i<unitableRooms.roomList.size();i++)
      {
         if(roomList.get(i).getId().equals(room.getUnitedWith()))
            return roomList.get(i);
      }
      return null;
   }

   /**
    * @return an arrayList of rooms that contains all unbooked rooms from the roomList
    */
   public ArrayList<Room> getAllUnbookedRooms()
   {
      ArrayList<Room> unbookedRooms = new ArrayList<>();
      for(int i=0;i<roomList.size();i++)
      {
         if(!roomList.get(i).getIsBooked())
            unbookedRooms.add(roomList.get(i));
      }
      return unbookedRooms;
   }

   /**
    * @return an arrayList of rooms that contains all booked rooms from the roomList
    */
   public ArrayList<Room> getAllBookedRooms()
   {
      ArrayList<Room> bookedRooms = new ArrayList<>();
      for(int i=0;i<roomList.size();i++)
      {
         if(roomList.get(i).getIsBooked())
            bookedRooms.add(roomList.get(i));
      }
      return bookedRooms;
   }

   /**
    * @return the list of rooms
    */
   public String toString()
   {
      return "Room list: " + roomList;
   }


}
