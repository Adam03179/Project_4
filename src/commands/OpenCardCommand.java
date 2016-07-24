package commands;

import controllers.SessionRequestContent;

import resource.ConfigurationManager;




public class OpenCardCommand implements Command {
    @Override
    public String execute(SessionRequestContent request) {

        String path = ConfigurationManager.getProperty("path.page.openCard");
        request.getSession().setAttribute("path", path);
        return path;

    }
}
