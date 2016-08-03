package ua.gerasymenko.commands;

import ua.gerasymenko.controllers.SessionRequestWrapper;
import ua.gerasymenko.dao.DAOAccount;
import ua.gerasymenko.dao.DAOFactory;
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
 * The TransferOperationCommand class realizing the logic of transfer funds
 * from one account to another.
 * This class is Singleton.
 * It implements interface Command, and it is part of Command and Factory patterns.
 * It includes only one method, which executes all needed task.
 * @author Igor Gerasymenko
 */
public class TransferOperationCommand implements Command {
    private static final Logger logger = Logger.getLogger(TransferOperationCommand.class);
    private static TransferOperationCommand instance = null;

    private TransferOperationCommand() {
    }

    public static synchronized TransferOperationCommand getInstance() {
        if (instance == null) {
            instance = new TransferOperationCommand();
        }
        return instance;
    }

    /**
     * This method executes all needed task for transfer funds
     * from one account to another.
     * It uses SessionRequestWrapper to initialized all needed parameters
     * for creating Account class for user his partner
     * and AccountHistory class for them as. If operation was success
     * it returns path to success-page to user, if not - error page.
     * @param  request
     * @return path to next page
     */
    @Override
    public String execute(SessionRequestWrapper request) {
        String path = ConfigurationManager.getProperty("path.page.operationSuccess");
        request.getSession().setAttribute("path", path);

        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOAccount daoAccount = daoFactory.getDAOAccount();

        //in request we have number of account and sum, so what we need to split request
        String numberOfAccount = request.getValueByName("transfer").split(" ")[0];
        BigDecimal sum = new BigDecimal(request.getValueByName("sum"));
        String numberOfPartnersAcc = request.getValueByName("partnerAccount");
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

        int accountId = daoAccount.getId(numberOfAccount);
        int partnerAccountId = daoAccount.getId(numberOfPartnersAcc);

        Account account = daoAccount.getAccountById(accountId);
        Account partnerAccount = daoAccount.getAccountById(partnerAccountId);
        int compare = sum.compareTo(new BigDecimal(0));

        if (sum.compareTo(account.getBalance()) > 0 || !daoAccount.isExist(numberOfPartnersAcc)
                || compare < 0) {
            path = ConfigurationManager.getProperty("path.page.error");
        } else {
            AccountHistory accountHistory = new AccountHistory(account,
                    sum, partnerAccount.getUser().getName()
                    + " " + partnerAccount.getUser().getSurname(), timestamp,
                    OperationType.WITHDRAW);

            AccountHistory partnerHistory = new AccountHistory(partnerAccount,
                    sum, account.getUser().getName() + " "
                    + account.getUser().getSurname(), timestamp,
                    OperationType.DEPOSIT);

            try {
                daoAccount.transferFunds(accountHistory, partnerHistory);
            } catch (SQLException e) {
                logger.error("Transfer operation command error", e);
            }
        }
        return path;
    }
}