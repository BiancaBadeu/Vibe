package model;

/**
 * A class representing a room
 */
public class Room
{
  private String id;
  private int capacity;
  private String unitedWith;
  private boolean booked;

  /**
   * @param id room ID
   * @param capacity room's capacity
   * @param unitedWith which room is the room united with
   *
   * A 3 argument constructor that initializes the instance variables
   */
  public Room(String id, int capacity, String unitedWith)
  {
    this.id = id;
    this.capacity = capacity;
    this.unitedWith = unitedWith;
    this.booked = false;
  }

  /**
   * @param id room's ID
   * @param capacity room's capacity
   *
   * A 2 argument constructor that initializes the instance variables
   */
  public Room(String id, int capacity)
  {
    this.id = id;
    this.capacity = capacity;
    this.unitedWith = null;
    this.booked = false;
  }

  /**
   * @param id room's ID
   * @param capacity room's capacity
   * @param unitedWith which room is the room united with
   * @param booked room is booked
   *
   * A 4 argument constructor that initializes all instance variables and
   * sets the room as booked
   */
  public Room(String id, int capacity, String unitedWith, boolean booked)
  {
    this.id = id;
    this.capacity = capacity;
    this.unitedWith = unitedWith;
    this.booked = booked;
  }

  /**
   * @return room's id
   */
  public String getId()
  {
    return id;
  }

  /**
   * @return room's capacity
   */
  public int getCapacity()
  {
    return capacity;
  }

  /**
   * @return room that the room is united with
   */
  public String getUnitedWith()
  {
    return unitedWith;
  }

  /**
   * @return if the room is booked
   */
  public boolean getIsBooked(){return booked;}

  /**
   * @param booked
   *
   * sets the room object as booked
   */
  public void setBooked(boolean booked)
  {
    this.booked = booked;
  }

  /**
   * @return room's ID, capacity, booked and is united with if it has a room that is united with
   */
  public String toString()
  {
    if(unitedWith == null)
      return "ID: " + this.id + ", capacity: " + this.capacity + ", is booked: " + this.booked;
    else
      return "ID: " + this.id + ", capacity: " + this.capacity + ", can be united with: " + this.unitedWith + ", is booked: " + this.booked;
  }

  /**
   * @param obj other room object to be compared
   * @return if a room object is equal to another one
   */
  public boolean equals(Object obj)
  {
    if(!(obj instanceof Room))
      return false;
    Room other = (Room) obj;
    if(unitedWith == null)
    {
      if (other.unitedWith != null)
        return false;
      else
        return id.equals(other.id) && capacity == other.capacity && this.booked == other.booked;
    }
    else if(other.unitedWith == null)
      return false;
    else return unitedWith.equals(other.unitedWith) && id.equals(other.id) && capacity == other.capacity && this.booked == other.booked;
  }

}
