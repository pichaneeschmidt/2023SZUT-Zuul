package de.szut.zuul;

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
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;

    //ideas
    // private boolean hidden;
    // private boolean lock;
    // public boolean checkPermission(){check if the player is allowed or not. Item/Level}
    // public boolean unLock(SomeItemClass item){}
    // public boolean appear(SomeItemClass item){}

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east exit.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room north, Room east, Room south, Room west) 
    {
        if(north != null) {
            northExit = north;
        }
        if(east != null) {
            eastExit = east;
        }
        if(south != null) {
            southExit = south;
        }
        if(west != null) {
            westExit = west;
        }
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
        if(direction.equalsIgnoreCase("north") && northExit!=null) {
            return northExit;
        }
        else if(direction.equalsIgnoreCase("east") && eastExit!=null) {
            return eastExit;
        }
        else if(direction.equalsIgnoreCase("south") && southExit!=null)  {
            return southExit;
        }
        else if(direction.equalsIgnoreCase("west") && westExit!=null)  {
            return westExit;
        }
        else return null;
    }

    public String exitsToString(){
        StringBuilder bilder = new StringBuilder("");
        //String exits = "";

        if(northExit != null) {
            //exits+="north ";
            bilder.append("north ");
        }
        if(eastExit != null) {
            //exits+="east ";
            bilder.append("east ");
        }
        if(southExit != null) {
            //exits+="south ";
            bilder.append("south ");
        }
        if(westExit != null) {
            //exits+="west ";
            bilder.append("west ");
        }
        return bilder.toString();//exits;
    }

}
