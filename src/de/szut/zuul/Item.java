package de.szut.zuul;

public class Item {
    private String name;
    private String description;
    private double weight;
    public double getWeight() {
        return weight;
    }

    public Item(String name, String description, double weight)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }
    public String toString()
    {
        StringBuilder itemInfo = new StringBuilder();
        itemInfo.append(name+", "+description+", "+weight+" kg");
        return itemInfo.toString();
    }
    public String getName() {
        return name;
    }

}
