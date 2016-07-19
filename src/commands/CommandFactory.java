package commands;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


public class CommandFactory {
    private static final Logger logger = Logger.getLogger(CommandFactory.class);

    private static CommandFactory instance = null;

    private CommandFactory() {
    }

    public static synchronized CommandFactory getInstance() {

        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }

    public Command defineCommand(HttpServletRequest request) {

        Command current = new EmptyCommand();
        String action = request.getParameter("Confirm");

        if (action == null || action.isEmpty()) {
            return current;
        }

        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            logger.error("Wrong action error", e);
        }
        return current;
    }

}
