package de.szut.zuul;

public class ConsumableItem extends Item{

    private double mp;
    private double hp;

    public boolean isHidden() {
        return hidden;
    }

    private boolean hidden;

    public double getMp() {
        return mp;
    }

    public double getHp() {
        return hp;
    }

    public ConsumableItem(String name, String description, double weight, double mp, double hp, boolean hidden)
    {
        super(name,description,weight);
        this.mp =mp;
        this.hp=hp;
        this.hidden=hidden;
    }

    @Override
    public String toString() {
        if(hidden) return super.toString();
        StringBuilder description = new StringBuilder(super.toString());
        description.append(System.getProperty("line.separator"));
        description.append(showHiddenAtrib());
        return description.toString();
    }

    public String showHiddenAtrib()
    {
        return "___recovering HP: "+hp+", Mana: "+ mp;
    }
}

