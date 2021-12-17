import model.*;
import parser.ParserException;
import parser.XmlJsonParser;

import java.io.File;
import java.util.ArrayList;

/**
 * A class that creates the xml file
 */
public class xmlSession
  {
    /**
     * The method that runs and writes in the xml file
     * @param args arg
     * @throws Exception the usage of Manager requires an exception to be thrown
     */
    public static void main(String[] args) throws Exception
    {
      Model model = new Manager();
      SessionList session = model.getAllSessions().getBookedSessionsAsList();
      XmlJsonParser parser = new XmlJsonParser();
      File file= parser.toXml(session, "sep\\src\\session.xml");
      System.out.println("File: " + file.getAbsolutePath());
    }
  }
