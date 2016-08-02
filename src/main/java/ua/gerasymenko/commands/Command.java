package ua.gerasymenko.commands;


import ua.gerasymenko.controllers.SessionRequestWrapper;

public interface Command {

    String execute(SessionRequestWrapper request);
}
