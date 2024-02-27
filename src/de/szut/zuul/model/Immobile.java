package de.szut.zuul.model;

public class Immobile implements State{
    private static Immobile instance;
    private Player player;
    private Immobile (Player player)
    {
        this.player=player;
    }
    public static Immobile getInstance(Player player)
    {
        if(instance==null) instance = new Immobile(player);
        return instance;
    }
    @Override
    public void heal() {
        player.setActualState(Injured.getInstance(player));
    }

    @Override
    public void injure() {
        System.out.println("Nothing happens");
    }

    @Override
    public void injureHeavily() {
        System.out.println("Nothing happens");
    }

    @Override
    public String toString() {
        return "Immobile";
    }
}
