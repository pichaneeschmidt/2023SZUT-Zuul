package de.szut.zuul;

public class Player {
    private Room currentRoom;

    public Room getCurrentRoom() {
        return currentRoom;
    }
    public void goTo(Room newRoom)
    {
        currentRoom = newRoom;
    }

}
