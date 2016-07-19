package commands;


import requestContent.SessionRequestContent;


public interface Command {

    String execute(SessionRequestContent request);
}
