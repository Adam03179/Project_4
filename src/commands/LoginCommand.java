package commands;


import dao.DAOFactory;
import dao.DAOUser;
import dbmodels.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        String path;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User u = new User();
        u.setLogIn(email);
        u.setPassword(password);
        DAOFactory factory = DAOFactory.getInstance();
        DAOUser user = factory.getDAOUser();
        boolean isExist = user.isExist(u);

        if (isExist) {
            user.getClient(email, password);
            path = "/jsp/user/registration.jsp";
            session.setAttribute("path", path);
            return path;
        } else {
            path = "/jsp/user/errorPage.jsp";
            return path;

        }

    }


}
