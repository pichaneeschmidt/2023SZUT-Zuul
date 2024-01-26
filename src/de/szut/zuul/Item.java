package de.szut.zuul;

public class Item {
    String name;
    String description;
    double weight;

    public Item(String name, String description, double weight)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    public String toString()
    {
        StringBuilder itemInfo = new StringBuilder();
        return itemInfo.toString();
    }

}
