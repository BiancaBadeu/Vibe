package readers;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import model.*;

public class RoomReader
{
  private RoomList roomList;

  public RoomReader()
  {
    this.roomList = new RoomList();
  }
  public RoomList getRoomList()
  {
    return roomList;
  }

  public void readRooms() throws Exception
  {
    /* file variable is created with the data from the text file */
    File file = new File("C:\\Users\\luisd\\IdeaProjects\\SEP1_V2_files\\src\\txt\\Rooms.txt");

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