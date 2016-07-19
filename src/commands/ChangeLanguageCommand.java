package commands;

import requestContent.SessionRequestContent;
import resource.ConfigurationManager;

public class ChangeLanguageCommand implements Command {
    @Override
    public String execute(SessionRequestContent request) {

        String language = request.getValueByName("language");
        request.addAttrToSession("setLocale", language);
        String path = (String) request.getSessionAttrByName("path");
        if (path == null) {
            return ConfigurationManager.getProperty("path.page.index");
        } else {
            return path;
        }

    }
}
