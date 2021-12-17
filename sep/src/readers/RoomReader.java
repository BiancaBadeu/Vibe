package readers;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import model.*;

/**
 * A class representing a reader for rooms
 */
public class RoomReader
{
  private RoomList roomList;

  /**
   * A 0 argument constructor initializing the variable roomList
   */
  public RoomReader()
  {
    this.roomList = new RoomList();
  }

  /**
   * @return roomList the list of rooms
   *
   * A method to return the list of rooms
   */
  public RoomList getRoomList()
  {
    return roomList;
  }

  /**
   * @throws Exception the import java.util.Scanner requires to throw an exception in order to perform operations.
   *
   * The method performs a scan operation of an specified text file and gets information, storing them in variables
   * and creating Room objects that are then added to the roomList.
   */
  public void readRooms() throws Exception
  {
    /* file variable is created with the data from the text file */
    File file = new File("src\\txt\\Rooms.txt");

    Scanner in = new Scanner(file);

    while (in.hasNext())
    {

      String line = in.nextLine();
      String[] splittingline = line.split(",");
      String roomNumber = splittingline[0].trim();
      int roomSize = Integer.parseInt(splittingline[1].trim());
      String roomNumber2 = null;
      if(splittingline.length > 2)
        roomNumber2= splittingline[2].trim();
      Room room;

      if(roomNumber2!=null)
        room= new Room(roomNumber, roomSize, roomNumber2);
      else
        room= new Room(roomNumber, roomSize);

      roomList.addRoom(room);
    }

  }

}