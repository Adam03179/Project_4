package main.java.commands;

import main.java.controllers.SessionRequestWrapper;
import main.java.managers.ConfigurationManager;

/**
 * The ChangeLanguageCommand class realizes the change of languages on UI.
 * This class is Singleton.
 * It implements interface Command, and it is part of Command and Factory patterns.
 * It includes only one methods, execute() - responsible for execution basic task.
 * @author Igor Gerasymenko
 */
public class ChangeLanguageCommand implements Command {
    private static ChangeLanguageCommand instance = null;

    private ChangeLanguageCommand() {
    }

    public static synchronized ChangeLanguageCommand getInstance() {

        if (instance == null) {
            instance = new ChangeLanguageCommand();
        }
        return instance;
    }

    /**
     * This method executes all needed task for change of languages on UI.
     * Depending of attributes value(language), it set needed parameter in attribute setLocale.
     * @param  request
     * @return if it was fist page for user, returned index-page.
     */
    @Override
    public String execute(SessionRequestWrapper request) {

        String language = request.getValueByName("language");
        request.getSession().setAttribute("setLocale", language);
        String path = (String) request.getSession().getAttribute("path");

        if (path == null) {
            return ConfigurationManager.getProperty("path.page.index");
        } else {
            return path;
        }

    }
}
