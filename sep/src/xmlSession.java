import model.*;
import parser.XmlJsonParser;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class xmlSession
  {
    public static void main(String[] args) throws Exception
    {
      Model model = new Manager();
      ArrayList<Session> sessions = model.getBookedSessions();
      Comparator<Session> compareByDate = new Comparator<Session>()
      {
        @Override public int compare(Session o1, Session o2)
        {
          if(o1.getCourse().getCourseID().charAt(1) > o2.getCourse().getCourseID().charAt(1))
            return 1;
          else if(o2.getCourse().getCourseID().charAt(1) > o1.getCourse().getCourseID().charAt(1))
            return -1;
          else {
          if(o2.getDateAndStartTime().getYear()>o1.getDateAndStartTime().getYear())
            return -1;
          else if(o1.getDateAndStartTime().getYear()>o2.getDateAndStartTime().getYear())
            return 1;
          else
          {
            if (o2.getDateAndStartTime().getMonth() > o1.getDateAndStartTime().getMonth())
              return -1;
            else if (o1.getDateAndStartTime().getMonth() > o2.getDateAndStartTime().getMonth())
              return 1;
            else
            {
              if (o2.getDateAndStartTime().getDay() > o1.getDateAndStartTime().getDay())
                return -1;
              else if (o1.getDateAndStartTime().getDay() > o2.getDateAndStartTime().getDay())
                return 1;
              else
                return o1.getCourse().getCourseName().compareTo(o2.getCourse().getCourseName());
            }
          }
          }
        }
      };

      Collections.sort(sessions, compareByDate);


      SessionList list = new SessionList();
      for(int i=0;i<sessions.size();i++)
      {
        list.addSession(sessions.get(i));
      }

      XmlJsonParser parser = new XmlJsonParser();
      File file= parser.toXml(list, "sep\\src\\session.xml");
      System.out.println("File: " + file.getAbsolutePath());
    }
  }
