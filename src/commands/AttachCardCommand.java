package commands;

import controllers.SessionRequestContent;
import dao.DAOAccount;
import dao.DAOCard;
import dao.DAOFactory;
import dbmodels.Card;
import resource.ConfigurationManager;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class AttachCardCommand implements Command {
    @Override
    public String execute(SessionRequestContent request) {
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

        Card card = new Card(accountId, cardNum, pin, expirationDate, standard);


        DAOCard daoCard = daoFactory.getDAOCard();

        boolean isCardCreated = false;


        if (!daoCard.isExist(cardNum)) {
            isCardCreated = daoCard.addCard(card);
        }

        String path;

        if (isCardCreated) {
            path = ConfigurationManager.getProperty("path.page.openAccount");
            request.getSession().setAttribute("path", path);
            return path;
        } else {
            path = ConfigurationManager.getProperty("path.page.error");
            request.getSession().setAttribute("path", path);
            return path;
        }

    }

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
