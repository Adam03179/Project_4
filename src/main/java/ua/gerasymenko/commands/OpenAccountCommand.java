package ua.gerasymenko.commands;


import ua.gerasymenko.controllers.SessionRequestWrapper;
import ua.gerasymenko.dao.*;
import ua.gerasymenko.models.Account;
import ua.gerasymenko.managers.ConfigurationManager;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

/**
 * The OpenAccountCommand class realizing the logic opening bank account.
 * This class is Singleton.
 * It implements interface Command, and it is part of Command and Factory patterns.
 * It includes one general method, which executes all needed task, and second service
 * method, which randomly generating number of bank account;
 *
 * @author Igor Gerasymenko
 */
public class OpenAccountCommand implements Command {
    private static OpenAccountCommand instance = null;

    private OpenAccountCommand() {
    }

    public static synchronized OpenAccountCommand getInstance() {

        if (instance == null) {
            instance = new OpenAccountCommand();
        }
        return instance;
    }

    /**
     * This method executes all needed task for opening bank account.
     * It uses SessionRequestWrapper to initialized all needed parameters
     * for creating Account class . If operation was success
     * it returns path to success-page to user, if not - error page.
     *
     * @param request
     * @return path to next page
     */
    @Override
    public String execute(SessionRequestWrapper request) {

        int userId = (Integer) request.getSession().getAttribute("userId");
        String numberOfAccount = generateAccountNumber().toString();
        double interest = 5.0;
        Date openDate = Date.valueOf(LocalDate.now());
        BigDecimal balance = new BigDecimal(100);
        String currency = "UAH";

        JdbcFactory jdbcFactory = JdbcFactory.getInstance();
        UserAPI user = jdbcFactory.getJdbcUser();

        Account newAccount = new Account(user.read(userId),
                numberOfAccount, interest, openDate, balance, currency,
                false);

        AccountAPI account = jdbcFactory.getJdbcAccount();
        boolean isAccountCreated = false;

        while (!account.isExist(numberOfAccount)) {
            isAccountCreated = account.create(newAccount);
        }
        String path;

        if (isAccountCreated) {
            path = ConfigurationManager.getProperty("path.page.operationSuccess");
            request.getSession().setAttribute("path", path);
        } else {
            path = ConfigurationManager.getProperty("path.page.error");
        }
        return path;

    }

    /**
     * Private method generateAccountNumber() needs only for
     * generating an account number randomly.
     *
     * @return StringBuilder object - the number of account.
     */
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
