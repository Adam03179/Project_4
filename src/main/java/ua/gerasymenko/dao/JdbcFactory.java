package ua.gerasymenko.dao;

import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * The JdbcFactory class 'makes' all others Jdbc objects. This class is singleton.
 *
 */
public class JdbcFactory {
    private static final Logger logger = Logger.getLogger(JdbcFactory.class);
    private static JdbcFactory instance = null;
    private DataSource dataSource;

    private JdbcFactory() throws NamingException {
        InitialContext initialContext = new InitialContext();
        dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/payments");
    }

    public static synchronized JdbcFactory getInstance() {
        if (instance == null) {
            try {
                return new JdbcFactory();
            } catch (NamingException e) {
                logger.error("Instance error ", e);
            }
        }
        return instance;
    }

    public JdbcAccount getJdbcAccount() {
        return new JdbcAccount(dataSource);
    }

    public JdbcAccountHistory getJdbcAccountHistory() {
        return new JdbcAccountHistory(dataSource);
    }

    public JdbcCard getJdbcCard() {
        return new JdbcCard(dataSource);
    }

    public JdbcUser getJdbcUser() {
        return new JdbcUser(dataSource);
    }

    public JdbcContacts getJdbcContacts() {
        return new JdbcContacts(dataSource);
    }




}
