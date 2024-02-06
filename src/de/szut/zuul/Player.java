package de.szut.zuul;

import java.util.LinkedList;
import java.util.Optional;

public class Player {
    private Room currentRoom;
    private double loadCapacity;

    LinkedList<Item> items;

    public Player ()
    {
        items = new LinkedList<>();
        loadCapacity = 10.0;
    }

    public Player(double capacity, Room room) {
        items = new LinkedList<>();
        loadCapacity = capacity;
        currentRoom = room;
    }
    public Room getCurrentRoom() {
        return currentRoom;
    }
    public void goTo(Room newRoom)
    {
        currentRoom = newRoom;
    }

    public boolean takeItem(Item item)
    {
        if(isTakePossible(item))
        {
            return false;
        }

        items.add(item);
        return true;
    }

    private double calculateWeight()
    {
        double sum = 0;
        for (Item i:items)
        {
            sum +=i.getWeight();
        }
        return sum;
    }

    private boolean isTakePossible(Item item)
    {
        if(calculateWeight()+item.getWeight()>loadCapacity) return false;
        return true;
    }

    public Item dropItem(String name)
    {
        Optional<Item> optionalItem = items.stream().filter(i -> i.getName().equals(name)).findFirst();
        if (optionalItem.isEmpty()) {
            return null;
        }
        Item item = optionalItem.get();
        items.remove(item);
        return item;
    }

    public String showStatus()
    {
        StringBuilder bilder = new StringBuilder();
        bilder.append("Status of the player");
        bilder.append(System.getProperty("line.separator"));
        bilder.append("loadCapacity: "+loadCapacity+" kg");
        bilder.append(System.getProperty("line.separator"));
        bilder.append("taken items:");
        bilder.append(System.getProperty("line.separator"));
        double sum = 0;
        for (Item i:items)
        {
            bilder.append(i.getName()+", "+i.getWeight()+" kg");
            bilder.append(System.getProperty("line.separator"));
            sum+=i.getWeight();
        }
        bilder.append("Carrying: "+sum +" kg");
        return bilder.toString();
    }




}
