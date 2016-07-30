package main.java.commands;

import main.java.controllers.SessionRequestWrapper;
import main.java.dao.DAOContacts;
import main.java.dao.DAOFactory;
import main.java.dao.DAOUser;
import main.java.entities.Contacts;
import main.java.entities.User;
import main.java.managers.ConfigurationManager;

/**
 * The RegistrationCommand class realizing the logic of user registration.
 * This class is Singleton.
 * It implements interface Command, and it is part of Command and Factory patterns.
 * It includes only one method, witch executes all needed task.
 *
 * @author Igor Gerasymenko
 */
public class RegistrationCommand implements Command {
    private static RegistrationCommand instance = null;

    private RegistrationCommand() {
    }

    public static synchronized RegistrationCommand getInstance() {
        if (instance == null) {
            instance = new RegistrationCommand();
        }
        return instance;
    }


    /**
     * This method executes all needed task for user registration.
     * It uses SessionRequestWrapper to initialized all needed parameters
     * for creating User class and Contacts class. If operation was success
     * it returns path to index-page to user, if not - error page.
     *
     * @param request
     * @return path to next page
     */
    @Override
    public String execute(SessionRequestWrapper request) {

        String path = ConfigurationManager.getProperty("path.page.registration");
        request.getSession().setAttribute("path", path);

        String name = request.getValueByName("name");
        String surname = request.getValueByName("surname");
        String individualTaxNumber = request.getValueByName("taxNumber");
        String password = request.getValueByName("password");
        String logIn = request.getValueByName("email");
        int numberOfPassport =
                Integer.parseInt(request.getValueByName("numberOfPassport"));
        String seriesOfPassport = request.getValueByName("seriesOfPassport");

        User user = new User(name, surname, individualTaxNumber, logIn,
                password, numberOfPassport, seriesOfPassport, false);

        DAOFactory factory = DAOFactory.getInstance();
        DAOUser daoUser = factory.getDAOUser();

        boolean isUserAdded = daoUser.addUser(user);

        String city = request.getValueByName("city");
        int postCode = Integer.parseInt(request.getValueByName("postCode"));
        String street = request.getValueByName("street");
        String numberOfHouse = request.getValueByName("numberOfHouse");
        String numberOfApartment = request.getValueByName("numberOfApartment");
        String telephoneNumber = request.getValueByName("telephoneNumber");
        String email = request.getValueByName("email");
        String region = request.getValueByName("region");

        Contacts contacts = new Contacts(user, postCode, region, city, street,
                numberOfHouse, numberOfApartment, telephoneNumber, email);


        DAOContacts daoContacts = factory.getDAOContacts();

        boolean isContactsAdded = daoContacts.addContacts(contacts);


        if (isContactsAdded && isUserAdded) {
            path = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("path", path);
            return path;
        } else {
            path = ConfigurationManager.getProperty("path.page.error");
            request.getSession().setAttribute("path", path);
            return path;
        }

    }
}
