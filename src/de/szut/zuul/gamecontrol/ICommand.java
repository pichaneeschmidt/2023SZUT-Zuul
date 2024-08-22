package de.szut.zuul.gamecontrol;

public interface ICommand {
    public void execute(Command command);
    public void reverse();
}
