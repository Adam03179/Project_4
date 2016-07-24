package commands;

import controllers.SessionRequestContent;
import resource.ConfigurationManager;

public class ChangeLanguageCommand implements Command {
    @Override
    public String execute(SessionRequestContent request) {

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
