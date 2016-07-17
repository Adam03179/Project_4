package commands;

import org.apache.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private static final Logger logger =
            Logger.getLogger(CommandFactory.class);

    private static Map<String, Command> commandMap = new HashMap<>();

    private static CommandFactory instance = null;


    private CommandFactory() throws NamingException {
    }

    public static synchronized CommandFactory getInstance() {
        if (instance == null) {
            try {
                instance = new CommandFactory();
            } catch (NamingException e) {
                logger.error("get instance error ", e);
            }
        }
        return instance;
    }

    static {

        commandMap.put("login", new LoginCommand());
        commandMap.put("changeLanguage", new ChangeLanguageCommand());

    }

    public Command getCommand(HttpServletRequest request) {
        String result = request.getParameter("Confirm");
        return commandMap.get(result);
    }


}
