package commands;


import controllers.SessionRequestContent;
import dao.DAOAccount;
import dao.DAOFactory;
import dao.DAOUser;
import dbmodels.Account;
import resource.ConfigurationManager;

import java.util.List;

public class LoginCommand implements Command {


    @Override
    public String execute(SessionRequestContent request) {


        String email = request.getValueByName("email");
        String password = request.getValueByName("password");
        String path;
        DAOFactory factory = DAOFactory.getInstance();
        DAOUser user = factory.getDAOUser();
        boolean isExist = user.isExist(email, password);

        if (isExist) {

            int userId = user.getId(email);
            assignedRole(user,userId,request);
            path = ConfigurationManager.getProperty("path.page.main");
            request.getSession().setAttribute("path", path);
            request.getSession().setAttribute("userId", userId);
            putAccountsIntoSession(request);


            return path;
        } else {
            path = ConfigurationManager.getProperty("path.page.error");
            return path;

        }

    }

    private void assignedRole(DAOUser user,int userId, SessionRequestContent request){

        if (user.isAdmin(userId)) {
            request.getSession().setAttribute("role", Rolls.ADMIN.name());
        }else request.getSession().setAttribute("role", Rolls.USER.name());

    }

    private void putAccountsIntoSession(SessionRequestContent request){

        DAOFactory factory = DAOFactory.getInstance();
        DAOAccount daoAccount = factory.getDAOAccount();

        int userId = (Integer) request.getSession().getAttribute("userId");

        List<Account> accountsList = daoAccount.getAllAccounts(userId);

        request.getSession().setAttribute("accountsList", accountsList);
    }


}
