package commands;


import controllers.SessionRequestContent;

public interface Command {

    String execute(SessionRequestContent request);
}
