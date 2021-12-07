package readers;

import readers.*;
import model.*;


public class Reader
{
  public static void main(String[] args) throws Exception
  {

    CourseReader courseReader = new CourseReader();
    courseReader.readCourses();

    //System.out.println(courseReader.getListOfCourses());

    RoomReader roomReader = new RoomReader();
    roomReader.readRooms();

    //System.out.println(roomReader.getRoomList());



    
  }
}
