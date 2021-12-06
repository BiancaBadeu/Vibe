package fileReaders;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class roomReader
{

    public static void main(String[] args) throws Exception
    {
        /* file variable is created with the data from the text file */
        File file = new File("C:\\Users\\luisd\\Downloads\\Rooms.txt");

        Scanner in = new Scanner(file);
        ArrayList<RoomList> roomLists= new ArrayList<RoomList>();
        while (in.hasNext())
        {

            String line = in.nextLine();
            String[] splittingline = line.split(",");
            String roomNumber = splittingline[0].trim();
            int roomSize = Integer.parseInt(splittingline[1].trim());
            String roomNumber2= splittingline[3].trim();

            Boolean isBooked=false;


            if(roomNumber2!=null){

                 Room room= new Room(roomNumber, roomSize, roomNumber2, isBooked);

            }
            else
            Room room= new Room(roomNumber, roomSize, isBooked );
            roomLists.addRoom(room);





        }

    }

}