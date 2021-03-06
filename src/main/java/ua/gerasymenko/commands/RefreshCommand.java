package ua.gerasymenko.commands;

import ua.gerasymenko.controllers.SessionRequestWrapper;
import ua.gerasymenko.dao.*;
import ua.gerasymenko.models.Account;
import ua.gerasymenko.models.AccountHistory;
import ua.gerasymenko.models.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * The RefreshCommand class realizing the logic of data updating.
 * This class is Singleton.
 * It implements interface Command, and it is part of Command and Factory patterns.
 * It includes one general method, which calls  three services methods, each of which
 * puts its specific data from DB into session.
 *
 * @author Igor Gerasymenko
 */
public class RefreshCommand implements Command {

    private JdbcFactory factory = JdbcFactory.getInstance();

    private static RefreshCommand instance = null;

    private RefreshCommand() {
    }

    public static synchronized RefreshCommand getInstance() {
        if (instance == null) {
            instance = new RefreshCommand();
        }
        return instance;
    }


    /**
     * This method calls three services methods, each of which
     * puts its specific data from DB into session. It returns the same path as was.
     *
     * @param request
     * @return path to page
     */
    @Override
    public String execute(SessionRequestWrapper request) {

        putHistoryIntoSession(request);
        putCardsIntoSession(request);
        putAccountsIntoSession(request);


        return (String) request.getSession().getAttribute("path");
    }

    /**
     * Private method putAccountsIntoSession() needs only for
     * uploading fresh bank account data from DB into session.
     *
     * @param request
     */
    private void putAccountsIntoSession(SessionRequestWrapper request) {

        factory = JdbcFactory.getInstance();
        AccountAPI account = factory.getJdbcAccount();

        int userId = (Integer) request.getSession().getAttribute("userId");

        List<Account> accountsList = account.getAllAccounts(userId);

        request.getSession().setAttribute("accountsList", accountsList);
    }

    /**
     * Private method putCardsIntoSession() needs only for uploading
     * fresh data about cards from DB into session.
     *
     * @param request
     */
    private void putCardsIntoSession(SessionRequestWrapper request) {

        factory = JdbcFactory.getInstance();
        AccountAPI account = factory.getJdbcAccount();
        CardAPI card = factory.getJdbcCard();
        int userId = (Integer) request.getSession().getAttribute("userId");

        List<Account> accountList = account.getAllAccounts(userId);
        List<Card> cardList = new ArrayList<>();


        for (Account acc : accountList) {
            cardList.addAll(card.getAllCards(acc.getId()));
        }

        request.getSession().setAttribute("cardsList", cardList);
    }

    /**
     * Private method putHistoryIntoSession() needs only for uploading
     * fresh data about history of bank accounts from DB into session.
     *
     * @param request
     */
    private void putHistoryIntoSession(SessionRequestWrapper request) {

        factory = JdbcFactory.getInstance();
        AccountHistoryAPI accountHistory = factory.getJdbcAccountHistory();
        AccountAPI account = factory.getJdbcAccount();
        int userId = (Integer) request.getSession().getAttribute("userId");

        List<AccountHistory> historyList = new ArrayList<>();

        List<Account> accountList = account.getAllAccounts(userId);

        for (Account acc : accountList) {
            historyList.addAll(accountHistory.getHistory(acc.getId()));
        }


        request.getSession().setAttribute("historyList", historyList);
    }


}
