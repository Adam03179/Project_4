package commands;

import controllers.SessionRequestContent;
import dao.DAOContacts;
import dao.DAOFactory;
import dao.DAOUser;
import dbmodels.Contacts;
import dbmodels.User;
import resource.ConfigurationManager;

public class RegistrationCommand implements Command {

    @Override
    public String execute(SessionRequestContent request) {


        String path = ConfigurationManager.getProperty("path.page.registration");
        request.getSession().setAttribute("path", path);

        String name = request.getValueByName("name");
        String surname = request.getValueByName("surname");
        String individualTaxNumber = request.getValueByName("taxNumber");
        String password = request.getValueByName("password");
        String logIn = request.getValueByName("email");
        int numberOfPassport = Integer.parseInt(request.getValueByName("numberOfPassport"));
        String seriesOfPassport = request.getValueByName("seriesOfPassport");

        User user = new User(name, surname, individualTaxNumber, logIn, password, numberOfPassport,
                seriesOfPassport, false);

        DAOFactory factory = DAOFactory.getInstance();
        DAOUser daoUser = factory.getDAOUser();

        boolean isUserAdded = daoUser.addUser(user);


        int userId = daoUser.getId(logIn);

        String city = request.getValueByName("city");
        int postCode = Integer.parseInt(request.getValueByName("postCode"));
        String street = request.getValueByName("street");
        String numberOfHouse = request.getValueByName("numberOfHouse");
        String numberOfApartment = request.getValueByName("numberOfApartment");
        String telephoneNumber = request.getValueByName("telephoneNumber");
        String email = request.getValueByName("email");
        String region = request.getValueByName("region");

        Contacts contacts = new Contacts(userId, postCode, region, city, street,
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
