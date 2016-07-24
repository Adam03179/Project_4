package commands;

import controllers.SessionRequestContent;
import dao.DAOAccount;
import dao.DAOFactory;
import dbmodels.Account;
import resource.ConfigurationManager;

import java.util.List;

public class ShowAccountsCommand implements Command {
    @Override
    public String execute(SessionRequestContent request) {


        String path = ConfigurationManager.getProperty("path.page.showAccounts");

        request.getSession().setAttribute("path", path);

        return path;
    }
}
