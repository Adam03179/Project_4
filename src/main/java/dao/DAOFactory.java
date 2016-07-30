package main.java.dao;

import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * The DAOFactory class 'makes' all others DAO objects. This class is singleton.
 *
 */
public class DAOFactory {
    private static final Logger logger = Logger.getLogger(DAOFactory.class);
    private static DAOFactory instance = null;
    private DataSource dataSource;

    private DAOFactory() throws NamingException {
        InitialContext initialContext = new InitialContext();
        dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/payments");
    }

    public static synchronized DAOFactory getInstance() {
        if (instance == null) {
            try {
                return new DAOFactory();
            } catch (NamingException e) {
                logger.error("Instance error ", e);
            }
        }
        return instance;
    }

    public DAOAccount getDAOAccount() {
        return new DAOAccount(dataSource);
    }

    public DAOAccountHistory getDAOAccountHistory() {
        return new DAOAccountHistory(dataSource);
    }

    public DAOCard getDAOCard() {
        return new DAOCard(dataSource);
    }

    public DAOUser getDAOUser() {
        return new DAOUser(dataSource);
    }

    public DAOContacts getDAOContacts() {
        return new DAOContacts(dataSource);
    }




}
