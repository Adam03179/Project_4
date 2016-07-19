package commands;

import requestContent.SessionRequestContent;
import resource.ConfigurationManager;


public class EmptyCommand implements Command {

    @Override
    public String execute(SessionRequestContent request){
        String path = ConfigurationManager.getProperty("path.page.index");

        return path;
    }
}
