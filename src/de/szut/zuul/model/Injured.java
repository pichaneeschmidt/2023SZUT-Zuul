package de.szut.zuul.model;

public class Injured implements State{
    private static Injured instance;
    private Player player;
    private Injured(Player player)
    {
        this.player=player;
    }
    public static Injured getInstance(Player player)
    {
        if(instance==null) instance = new Injured(player);
        return instance;
    }
    @Override
    public void heal() {
        player.setActualState(Healthy.getInstance(player));
    }

    @Override
    public void injure() {
        player.setActualState(Immobile.getInstance(player));
    }

    @Override
    public void injureHeavily() {
        player.setActualState(Immobile.getInstance(player));
    }

    @Override
    public String toString() {
        return "Injured";
    }
}
