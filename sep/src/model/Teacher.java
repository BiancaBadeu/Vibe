package model;

/**
 * A class representing a teacher
 */
public class Teacher
{
  private String name;
  private String id;

  /**
   * @param name teacher's name
   * @param id teacher's id
   *
   * A 2 argument constructor that initializes all instance variables
   */
  public Teacher(String name, String id)
  {
    this.name = name;
    this.id = id;
  }

  /**
   * @return name
   */
  public String getName()
  {
    return name;
  }

  /**
   * @return id
   */
  public String getId()
  {
    return id;
  }

  /**
   * @return a string containing the name and id
   */
  public String toString()
  {
      return "\n" + this.name + ", " + this.id;
  }

  /**
   * @param obj other teacher object
   * @return checks if a teacher object is equal to another one
   */
  public boolean equals(Object obj)
  {
    if(!(obj instanceof Teacher))
      return false;
    Teacher other = (Teacher) obj;
    return id.equals(other.id) && name.equals(other.name);
  }
}
