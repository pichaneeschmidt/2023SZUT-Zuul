package de.szut.zuul.gamecontrol;

import de.szut.zuul.model.Player;
import de.szut.zuul.model.Room;

public class Go implements ICommand{
    private Player player;

    @Override
    public void execute(Command command) {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        // Try to leave current room.
        //Room nextRoom = currentRoom.getExit(direction);
        Room nextRoom =player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            //if nextRoom!=null
            //currentRoom = nextRoom;
            player.goTo(nextRoom);
            printRoomInformation();
        }
    }
    private void printRoomInformation()
    {
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    @Override
    public void reverse() {

    }
}
