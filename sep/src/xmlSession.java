import model.SessionList;
import parser.ParserException;
import parser.XmlJsonParser;

import java.io.File;

  public class xmlSession
  {
    public static void main(String[] args) throws ParserException
    {
      SessionList sessionList= new SessionList();
      sessionList.getBookedSessions();
      XmlJsonParser parser = new XmlJsonParser();
      File file= parser.toXml(sessionList, "session.xml");
      System.out.println("File: " + file.getAbsolutePath());
    }
  }
