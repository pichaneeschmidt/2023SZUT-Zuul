package de.szut.zuul.model;

public class Healthy implements State{
    private static Healthy instance;
    private Player player;

    private Healthy(Player player)
    {
        this.player = player;
    }

    public static Healthy getInstance(Player player)
    {
        if(instance==null) instance = new Healthy(player);
        return instance;
    }

    @Override
    public void heal() {
    }

    @Override
    public void injure() {
        player.setActualState(Injured.getInstance(player));
    }

    @Override
    public void injureHeavily() {
        player.setActualState(Immobile.getInstance(player));
    }

    @Override
    public String toString() {
        return "Healthy";
    }
}
