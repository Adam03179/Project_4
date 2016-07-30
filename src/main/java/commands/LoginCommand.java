package main.java.commands;


import main.java.controllers.SessionRequestWrapper;
import main.java.dao.DAOFactory;
import main.java.dao.DAOUser;
import main.java.managers.ConfigurationManager;

/**
 * The LoginCommand class realizing the logic of users login.
 * This class is Singleton.
 * It implements interface Command, and it is part of Command and Factory patterns.
 * It includes only one method, which executes all needed task.
 * @author Igor Gerasymenko
 */
public class LoginCommand implements Command {
    private static LoginCommand instance = null;

    private LoginCommand() {
    }

    public static synchronized LoginCommand getInstance() {

        if (instance == null) {
            instance = new LoginCommand();
        }
        return instance;
    }

    /**
     * This method executes all needed task for users login.
     * It checks user registered or not with the help email and password. If user is registered
     * it returns path to menu-page to user, if not - error page.
     * @param  request
     * @return path to next page
     */
    @Override
    public String execute(SessionRequestWrapper request) {


        String email = request.getValueByName("email");
        String password = request.getValueByName("password");
        String path;
        DAOFactory factory = DAOFactory.getInstance();
        DAOUser user = factory.getDAOUser();
        boolean isExist = user.isExist(email, password);

        if (isExist) {
            int userId = user.getId(email);

            path = ConfigurationManager.getProperty("path.page.main");
            request.getSession().setAttribute("path", path);
            request.getSession().setAttribute("userId", userId);

            RefreshCommand.getInstance().execute(request);
            return path;
        } else {
            path = ConfigurationManager.getProperty("path.page.error");
            return path;

        }

    }




}
