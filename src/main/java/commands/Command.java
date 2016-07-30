package main.java.commands;


import main.java.controllers.SessionRequestWrapper;

public interface Command {

    String execute(SessionRequestWrapper request);
}
