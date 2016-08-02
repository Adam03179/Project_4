package ua.gerasymenko.commands;

import ua.gerasymenko.controllers.SessionRequestWrapper;
import ua.gerasymenko.dao.DAOAccount;
import ua.gerasymenko.dao.DAOCard;
import ua.gerasymenko.dao.DAOFactory;
import ua.gerasymenko.models.Account;
import ua.gerasymenko.models.Card;
import ua.gerasymenko.managers.ConfigurationManager;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;


/**
 * The AttachCardCommand class realizes the logic of attaching card with bank's account.
 * This class is Singleton.
 * It implements interface Command, and it is part of Command and Factory patterns.
 * It includes two methods, execute() - responsible for execution basic task, and
 *  private method generateCardNumber() which needed only for generate number of account.
 * @author Igor Gerasymenko
 */
public class AttachCardCommand implements Command {
    private static AttachCardCommand instance = null;

    private AttachCardCommand() {
    }

    public static synchronized AttachCardCommand getInstance() {

        if (instance == null) {
            instance = new AttachCardCommand();
        }
        return instance;
    }

    /**
     * This method executes all needed task for attaching card with user's account.
     * It uses SessionRequestWrapper to initialized all needed parameters
     * for creating Card class. If operation was success
     * it returns path to success-page to user, if not - error page.
     * @param  request
     * @return path to next page
     */
    @Override
    public String execute(SessionRequestWrapper request) {

        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOAccount daoAccount = daoFactory.getDAOAccount();

        String numberOfAccount = request.getValueByName("account");
        Integer accountId = daoAccount.getId(numberOfAccount);
        String standard = request.getValueByName("standard");
        Integer pin = Integer.parseInt(request.getValueByName("pin"));
        Calendar year = GregorianCalendar.getInstance();
        year.add(Calendar.YEAR, 1);
        Date expirationDate = new Date(year.getTime().getTime());
        String cardNum = generateCardNumber().toString();
        Account account = daoAccount.getAccountById(accountId);

        Card card = new Card(account, cardNum, pin, expirationDate, standard);
        DAOCard daoCard = daoFactory.getDAOCard();

        boolean isCardCreated = false;

        if (!daoCard.isExist(cardNum)) {
            isCardCreated = daoCard.addCard(card);
        }

        String path;
        if (isCardCreated) {
            path = ConfigurationManager.getProperty("path.page.operationSuccess");
            request.getSession().setAttribute("path", path);
            return path;
        } else {
            path = ConfigurationManager.getProperty("path.page.error");
            request.getSession().setAttribute("path", path);
            return path;
        }
    }

    /**
     * Private method generateCardNumber() needs only for generating an account number randomly.
     *
     * @return StringBuilder object - the number of account.
     */
    private StringBuilder generateCardNumber() {

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
