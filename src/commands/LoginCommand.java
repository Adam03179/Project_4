package commands;


import dao.DAOFactory;
import dao.DAOUser;
import dbmodels.User;
import requestContent.SessionRequestContent;
import resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {

    @Override
    public String execute(SessionRequestContent request) {

        String email = request.getValueByName("email");
        String password = request.getValueByName("password");
        String path;
        User u = new User();
        u.setLogIn(email);
        u.setPassword(password);
        DAOFactory factory = DAOFactory.getInstance();
        DAOUser user = factory.getDAOUser();
        boolean isExist = user.isExist(u);

        if (isExist) {
            user.getClient(email, password);
            path = ConfigurationManager.getProperty("path.page.registration");
            request.addAttrToSession("path", path);
            return path;
        } else {
            path = ConfigurationManager.getProperty("path.page.error");
            request.addAttrToSession("path", path);
            return path;

        }

    }


}
