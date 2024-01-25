package de.szut.zuul;

import java.util.HashMap;
import java.util.Map;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Room 
{
    private String description;
    private Map<String, Room> rooms;

    // Future works
    // private boolean hidden;
    // private boolean lock;
    // public boolean checkPermission(){check if the player is allowed or not. Item/Level}
    // public boolean unLock(SomeItemClass item){}
    // public boolean appear(SomeItemClass item){}

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open courtyard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        rooms = new HashMap<>();
    }

    private void setExit(String direction, Room room)
    {
        rooms.putIfAbsent(direction,room);
    }

    public void setExit(Direction direction, Room room)
    {
        setExit(direction.toString(), room);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     *
     * @param direction
     * @return room or null
     * give the room of the given direction
     * give null when the direction or the room do not exist
     */
    public Room getExit(String direction){
        return rooms.get(direction.toUpperCase()); //return null when the key does not exist
    }

    public String exitsToString(){
        StringBuilder bilder = new StringBuilder();
        //for(String direction: rooms.keySet()){}
        bilder.append(rooms.keySet());
        return bilder.toString();//exits;
    }

}
