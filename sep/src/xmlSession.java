import model.*;
import parser.ParserException;
import parser.XmlJsonParser;

import java.io.File;
import java.util.ArrayList;

public class xmlSession
  {
    public static void main(String[] args) throws Exception
    {
      Model model = new Manager();
      SessionList session = model.getAllSessions().getBookedSessionsAsList();
      XmlJsonParser parser = new XmlJsonParser();
      File file= parser.toXml(session, "sep\\src\\session.xml");
      System.out.println("File: " + file.getAbsolutePath());
    }
  }
