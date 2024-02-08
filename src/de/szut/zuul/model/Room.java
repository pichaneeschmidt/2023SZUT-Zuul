package de.szut.zuul.model;

import de.szut.zuul.exceptions.ItemNotFoundException;

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
    private Map<String, Room> neighbors;
    private Map<String, Item> items;
    //TODO (ideas)
    // private boolean hidden;
    // private boolean lock;
    // public boolean checkPermission(){check if the player is allowed or not. Item/Level}
    // public boolean unLock(SomeItemClass item){}
    // public boolean appear(SomeItemClass item){}
    // idea setNormalExit() set two way exit for normal cases
    // this will reduce typos and miss match exits
    // leave setExit be because sometime oneway door is also needed

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open courtyard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        neighbors = new HashMap<>();
        items = new HashMap<>();
    }

    public void setExit(Direction direction, Room room)
    {
        setExit(direction.toString(), room);
    }
    private void setExit(String direction, Room room)
    {
        neighbors.putIfAbsent(direction,room);
    }

    public void putItem(Item newItem)
    {
        items.putIfAbsent(newItem.getName(),newItem);
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
        return neighbors.get(direction.toUpperCase()); //return null when the key does not exist
    }

    public String exitsToString(){
        StringBuilder bilder = new StringBuilder();
        //for(String direction: rooms.keySet()){}
        bilder.append(neighbors.keySet());
        return bilder.toString().substring(1,bilder.length()-1);//exits;
        //RegEx in Java
    }

    public String getLongDescription()
    {
        StringBuilder bilder = new StringBuilder();
        bilder.append("You are "+description);
        bilder.append(System.getProperty("line.separator"));
        bilder.append("Exits: "+exitsToString());
        bilder.append(System.getProperty("line.separator"));
        bilder.append("Items in this room:");
        for (String name: items.keySet())
        {
            bilder.append(System.getProperty("line.separator"));
            bilder.append("-"+items.get(name).toString());
        }
        return bilder.toString();
    }

    public Item removeItem(String name)
            throws ItemNotFoundException
    {
        if(!isItemExist(name))
        {
            throw new ItemNotFoundException("This item does not exist!");
            /*System.out.println("There is no "+name+" here");
            return null;*/
        }
        return items.remove(name);
    }

    public boolean isItemExist(String itemName)
    {
        return items.containsKey(itemName);
    }

}
