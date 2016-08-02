package ua.gerasymenko.commands;

import ua.gerasymenko.controllers.SessionRequestWrapper;
import ua.gerasymenko.managers.ConfigurationManager;

/**
 * The EmptyCommand class turns user to index page.
 * This class is Singleton.
 * It implements interface Command, and it is part of Command and Factory patterns.
 * It includes only one method, which executes all needed task.
 * @author Igor Gerasymenko
 */
public class EmptyCommand implements Command {
    private static EmptyCommand instance = null;

    private EmptyCommand() {
    }

    public static synchronized EmptyCommand getInstance() {

        if (instance == null) {
            instance = new EmptyCommand();
        }
        return instance;
    }

    /**
     * This method turns user to index page.
     * @param  request
     * @return path to index page
     */
    @Override
    public String execute(SessionRequestWrapper request){
        String path = ConfigurationManager.getProperty("path.page.index");

        return path;
    }
}
