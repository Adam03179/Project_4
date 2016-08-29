package ua.gerasymenko.commands;

import ua.gerasymenko.controllers.SessionRequestWrapper;
import ua.gerasymenko.dao.JdbcFactory;
import ua.gerasymenko.dao.UserAPI;
import ua.gerasymenko.managers.ConfigurationManager;

public class ChangePasswordCommand implements Command {

    private static ChangePasswordCommand instance = null;

    private ChangePasswordCommand() {
    }

    public static synchronized ChangePasswordCommand getInstance() {
        if (instance == null) {
            instance = new ChangePasswordCommand();
        }
        return instance;
    }

    @Override
    public String execute(SessionRequestWrapper request) {


        String oldPassword = request.getValueByName("oldPassword");
        String newPassword = request.getValueByName("password");
        String newRePassword = request.getValueByName("rePassword");
        int userId = (Integer) request.getSession().getAttribute("userId");

        JdbcFactory factory = JdbcFactory.getInstance();
        UserAPI user = factory.getJdbcUser();

        String passwordFromDB = user.getPassword(userId);

        String path;
        if (newPassword.equals(newRePassword) && oldPassword.equals(passwordFromDB)) {

            if (user.changePassword(userId, newPassword)) {

                path = ConfigurationManager.
                        getProperty("path.page.operationSuccessBottom");
                request.getSession().setAttribute("path", path);
            } else {
                path = ConfigurationManager.getProperty("path.page.error");

            }
        } else {
            path = ConfigurationManager.getProperty("path.page.error");

        }


        return path;
    }
}
