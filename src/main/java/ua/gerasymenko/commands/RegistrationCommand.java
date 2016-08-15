package ua.gerasymenko.commands;

import ua.gerasymenko.controllers.SessionRequestWrapper;
import ua.gerasymenko.dao.*;
import ua.gerasymenko.models.Contacts;
import ua.gerasymenko.models.User;
import ua.gerasymenko.managers.ConfigurationManager;

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

        User newUser = createUserFromRequest(request);

        JdbcFactory factory = JdbcFactory.getInstance();
        UserAPI user = factory.getJdbcUser();

        boolean isUserAdded = user.create(newUser);

        //Setting Id to newUser
        newUser.setId(user.getId(request.getValueByName("email")));

        Contacts newContacts = createContactsFromRequest(request, newUser);
        ContactsAPI contacts = factory.getJdbcContacts();
        boolean isContactsAdded = contacts.create(newContacts);

        if (isContactsAdded && isUserAdded) {
            path = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("path", path);

        } else {
            path = ConfigurationManager.getProperty("path.page.error");
        }

        return path;

    }

    /**
     * Private method createUserFromRequest(SessionRequestWrapper request)
     * needs for creating object of User.class with parameters,
     * that came in the request.
     *
     * @param request
     * @return new User object .
     */
    private User createUserFromRequest(SessionRequestWrapper request) {
        String name = request.getValueByName("name");
        String surname = request.getValueByName("surname");
        String individualTaxNumber = request.getValueByName("taxNumber");
        String password = request.getValueByName("password");
        String logIn = request.getValueByName("email");
        int numberOfPassport =
                Integer.parseInt(request.getValueByName("numberOfPassport"));
        String seriesOfPassport = request.getValueByName("seriesOfPassport");

        return new User(name, surname, individualTaxNumber, logIn,
                password, numberOfPassport, seriesOfPassport, false);
    }

    /**
     * Private method createContactsFromRequest(SessionRequestWrapper request,
     * User newUser)
     * needs for creating object of Contacts.class with parameters,
     * that came in the request.
     *
     * @param request
     * @param newUser
     * @return new Contacts object .
     */
    private Contacts createContactsFromRequest(SessionRequestWrapper request,
                                               User newUser) {

        String city = request.getValueByName("city");
        int postCode = Integer.parseInt(request.getValueByName("postCode"));
        String street = request.getValueByName("street");
        String numberOfHouse = request.getValueByName("numberOfHouse");
        String numberOfApartment = request.getValueByName("numberOfApartment");
        String telephoneNumber = request.getValueByName("telephoneNumber");
        String email = request.getValueByName("email");
        String region = request.getValueByName("region");

        return new Contacts(newUser, postCode, region, city, street,
                numberOfHouse, numberOfApartment, telephoneNumber, email);

    }
}
