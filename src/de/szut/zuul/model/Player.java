package de.szut.zuul.model;

import de.szut.zuul.exceptions.ItemNotFoundException;
import de.szut.zuul.exceptions.ItemTooHeavyException;

import java.util.LinkedList;
import java.util.Optional;

public class Player {
    private Room currentRoom;
    private double loadCapacity;
    private LinkedList<Item> items;
    private State actualState;

    public Player ()
    {
        items = new LinkedList<>();
        loadCapacity = 10.0;
        //setActualState(new Healthy(this));

        setActualState(Injured.getInstance(this));
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

    public void takeItem(Item item)
            throws ItemTooHeavyException
    {
        if(!isTakePossible(item))throw new ItemTooHeavyException();
        items.add(item);
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
        return calculateWeight()+item.getWeight()<=loadCapacity;
    }

    public Item dropItem(String name)
            throws ItemNotFoundException
    {
        Optional<Item> optionalItem = lookUpItems(name);//items.stream().filter(i -> i.getName().equals(name)).findFirst();
        if (optionalItem.isEmpty()) {
            throw new ItemNotFoundException("You don‘t own this item!");
            /*System.out.println("The player does not have "+name );
            return null;*/
        }
        Item item = optionalItem.get();
        items.remove(item);
        return item;
    }

    private Optional<Item> lookUpItems(String name)
    {
        Optional<Item> optionalItem = items.stream().filter(i -> i.getName().equals(name)).findFirst();
        //Optional<Item> optionalItem = items.stream().filter(i -> i.getName().equals(name)).findFirst().orElse(null); //then no need Optional
        return optionalItem;
    }

    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public void alterStatus(double addition)
    {
        this.loadCapacity+=addition;
    }

    public State getActualState() {
        return actualState;
    }
    public void setActualState(State actualState) {
        this.actualState = actualState;
    }
    public void heal()
    {
        actualState.heal();
    }
    public void injure()
    {
        actualState.injure();
    }
    public void injureHeavily()
    {
        actualState.injureHeavily();
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
        bilder.append("Remaining: "+(loadCapacity-sum)+" kg");
        bilder.append(System.getProperty("line.separator"));
        bilder.append("Status: "+actualState.toString());
        return bilder.toString();
    }

}
