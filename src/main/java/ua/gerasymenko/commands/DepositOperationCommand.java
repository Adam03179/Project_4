package ua.gerasymenko.commands;

import ua.gerasymenko.controllers.SessionRequestWrapper;
import ua.gerasymenko.dao.AccountAPI;
import ua.gerasymenko.dao.JdbcFactory;
import ua.gerasymenko.models.Account;
import ua.gerasymenko.models.AccountHistory;
import ua.gerasymenko.models.OperationType;
import org.apache.log4j.Logger;
import ua.gerasymenko.managers.ConfigurationManager;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;


/**
 * The DepositOperationCommand class realizing the logic of deposit in bank account.
 * This class is Singleton.
 * It implements interface Command, and it is part of Command and Factory patterns.
 * It includes only one method, which executes all needed task.
 *
 * @author Igor Gerasymenko
 */
public class DepositOperationCommand implements Command {
    private static final Logger logger = Logger.getLogger(DepositOperationCommand.class);
    private static DepositOperationCommand instance = null;

    private DepositOperationCommand() {
    }

    public static synchronized DepositOperationCommand getInstance() {

        if (instance == null) {
            instance = new DepositOperationCommand();
        }
        return instance;
    }

    /**
     * This method executes all needed task for deposit in bank account.
     * It uses SessionRequestWrapper to initialized all needed parameters
     * for creating Account class and AccountHistory class. If operation was success
     * it returns path to success-page to user, if not - error page.
     *
     * @param request
     * @return path to next page
     */
    @Override
    public String execute(SessionRequestWrapper request) {

        JdbcFactory jdbcFactory = JdbcFactory.getInstance();
        AccountAPI account = jdbcFactory.getJdbcAccount();

        String accountNumber = request.getValueByName("deposit");
        BigDecimal sum = new BigDecimal(request.getValueByName("sum"));
        int accountId = account.getId(accountNumber);

        Account accountForHistory = account.read(accountId);
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

        String path;

        int compare = sum.compareTo(new BigDecimal(0));
        if (compare < 0) {

            path = ConfigurationManager.getProperty("path.page.error");

        } else {
            AccountHistory accountHistory = new AccountHistory(accountForHistory,
                    sum, accountForHistory.getUser().getName() + " "
                    + accountForHistory.getUser().getSurname(), timestamp,
                    OperationType.DEPOSIT);

            path = ConfigurationManager.getProperty("path.page.operationSuccessBottom");
            request.getSession().setAttribute("path", path);

            try {
                account.addFunds(accountHistory);
            } catch (SQLException e) {
                logger.error("Deposit operation command error", e);
                return ConfigurationManager.getProperty("path.page.error");
            }
        }

        return path;
    }
}
