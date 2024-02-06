package de.szut.zuul;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    //private Room currentRoom;
    private Player player;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        player = new Player();
        parser = new Parser();
        createRooms();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room marketSquare, templePyramid, tavern, sacrificialSite, hut, jungle, secretPassage, cave, beach;
        Room magicChamber, cellar;
      
        // create the rooms
        marketSquare = new Room("on the market square");
        templePyramid = new Room("in a temple pyramid");
        tavern = new Room("in the tavern at the market square");
        sacrificialSite = new Room("at a sacrificial site");
        hut = new Room("in a hut");
        jungle = new Room("in the jungle");
        secretPassage = new Room("in a secret passage");
        cave = new Room("in a cave");
        beach = new Room("on the beach");
        magicChamber = new Room("in a room surrounded by a mysterious aura");
        cellar = new Room("in a cellar. This place reeks of dust and ash");

        // initialise room exits
        marketSquare.setExit(Direction.NORTH, tavern);
        marketSquare.setExit(Direction.EAST, templePyramid);
        marketSquare.setExit(Direction.WEST, sacrificialSite);
        templePyramid.setExit(Direction.NORTH,hut);
        templePyramid.setExit(Direction.WEST,marketSquare);
        templePyramid.setExit(Direction.UP,magicChamber);
        templePyramid.setExit(Direction.DOWN,cellar);
        tavern.setExit(Direction.EAST,hut);
        tavern.setExit(Direction.SOUTH,marketSquare);
        sacrificialSite.setExit(Direction.EAST,marketSquare);
        sacrificialSite.setExit(Direction.DOWN,cave);
        hut.setExit(Direction.EAST,jungle);
        hut.setExit(Direction.SOUTH,templePyramid);
        hut.setExit(Direction.WEST,tavern);
        jungle.setExit(Direction.WEST,hut);
        secretPassage.setExit(Direction.EAST,cellar);
        secretPassage.setExit(Direction.WEST,cave);
        cave.setExit(Direction.UP,sacrificialSite);
        cave.setExit(Direction.EAST,secretPassage);
        cave.setExit(Direction.SOUTH,beach);
        beach.setExit(Direction.NORTH,cave);
        magicChamber.setExit(Direction.DOWN,templePyramid);
        cellar.setExit(Direction.UP,templePyramid);
        cellar.setExit(Direction.WEST,secretPassage);

        //currentRoom = marketSquare;  // start game on marketSquare
        player.goTo(marketSquare);

        // crate items
        // marketSquare
        Item bow =new Item("Bogen", "ein Bogen aus Holz", 0.5);
        // cave
        Item treasure = new Item("Schatz", "eine kleine Schatztruhe mit Münzen", 7.5);
        // magicChamber
        Item arrows = new Item("Pfeile", "ein Köcher mit diversen Pfeilen", 1);
        // jungle
        Item plant = new Item("Pflanze", "eine Heilpflanze", 0.5);
        Item cocoa = new Item("Kakao", "ein kleiner Kakaobaum", 5);
        // sacrificialSite
        Item knife = new Item("Messer", "ein sehr scharfes, großes Messer", 1);
        // hut
        Item spear = new Item("Speer", "ein Speer mit dazugehöriger Schleuder", 5.0);
        // tavern
        Item food = new Item("Nahrung", "ein Teller mit deftigem Fleisch und Maisbrei", 0.5);
        // cellar
        Item jewery = new Item("Schmuck", "ein sehr hübscher Kopfschmuck", 1);
        // templePyramid, beach, magicChamber;
        //SecretPassage
        ConsumableItem blueMuffin = new ConsumableItem("BlauMuffin", "A fancy blue muffin, probably made by a skillful patissier. But why is it here?",0.3, 10,-5,true);
        ConsumableItem commonMuffin = new ConsumableItem("commonMuffin", "Just a muffin, looks a bit too dry but edible.",0.3, 0.0,0.5,false);


        //add the items into the rooms
        marketSquare.putItem(bow);
        cave.putItem(treasure);
        magicChamber.putItem(arrows);
        jungle.putItem(plant);
        jungle.putItem(cocoa);
        sacrificialSite.putItem(knife);
        hut.putItem(spear);
        tavern.putItem(food);
        tavern.putItem(commonMuffin);
        cellar.putItem(jewery);
        secretPassage.putItem(blueMuffin);
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();

        printRoomInformation();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            look();
        }
        else if (commandWord.equals("take")) {
            takeItem(command);
        }
        else if (commandWord.equals("drop")) {
            dropItem(command);
        }
        else if (commandWord.equals("eat")) {
            consumeItem(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("through the jungle. At once there is a glade. On it there a buildings...");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
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

    private void look()
    {
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    private void takeItem(Command command)
    {
        if(!command.hasSecondWord()) {
            System.out.println("take what?");
            return;
        }

        Item item = player.getCurrentRoom().removeItem(command.getSecondWord());
        if(item==null)
        {
            System.out.println("Taking "+command.getSecondWord()+" failed");
            return;
        }
        player.takeItem(item);
        System.out.println(player.showStatus());
    }

    private void dropItem(Command command)
    {
        if(!command.hasSecondWord()) {
            System.out.println("drop what?");
            return;
        }
        Item item = player.dropItem(command.getSecondWord());
        if(item==null)
        {
            System.out.println("Dropping "+command.getSecondWord()+" failed");
            return;
        }
        player.getCurrentRoom().putItem(item);
        System.out.println(player.showStatus());
    }

    private void consumeItem(Command command)
    {
        if(!command.hasSecondWord()) {
            System.out.println("eat what?");
            return;
        }
        Item item = player.dropItem(command.getSecondWord());
        if(item==null)
        {
            System.out.println(command.getSecondWord()+" not found");
            return;
        }
        if(!(item instanceof ConsumableItem)) { //or exception
            player.takeItem(item);
        }
        ConsumableItem cItem = (ConsumableItem) item;
        System.out.println(cItem.toString());
        if(cItem.isHidden())System.out.println(cItem.showHiddenAtrib());

        player.alterStatus(cItem.getHp());
    }



}
