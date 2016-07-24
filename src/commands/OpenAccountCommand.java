package commands;


import controllers.SessionRequestContent;
import dao.DAOAccount;
import dao.DAOFactory;
import dbmodels.Account;
import resource.ConfigurationManager;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

public class OpenAccountCommand implements Command {


    @Override
    public String execute(SessionRequestContent request) {


        int userId = (Integer) request.getSession().getAttribute("userId");
        String numberOfAccount = generateAccountNumber().toString();
        double interest = 5.0;
        Date openDate = Date.valueOf(LocalDate.now());
        double balance = 100.0;
        String currency = "UAH";

        Account account = new Account(userId, numberOfAccount, interest, openDate, balance, currency);
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOAccount daoAccount = daoFactory.getDAOAccount();
        boolean isAccountCreated = false;

        while (!daoAccount.isExist(numberOfAccount)) {
            isAccountCreated = daoAccount.createAccount(account);
        }

        String path;

        if (isAccountCreated) {
            path = ConfigurationManager.getProperty("path.page.openAccount");
            request.getSession().setAttribute("path", path);
            return path;
        } else {
            path = ConfigurationManager.getProperty("path.page.error");
            request.getSession().setAttribute("path", path);
            return path;
        }


    }

    private StringBuilder generateAccountNumber() {

        Random random = new Random();

        StringBuilder result = new StringBuilder();

        final int numberOfDigits = 16;
        int counter = 0;

        while (counter != numberOfDigits) {
            result.append(random.nextInt(9));
            counter++;
        }

        return result;

    }



}
