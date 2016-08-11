package ua.gerasymenko.dao;

import org.junit.Before;
import org.junit.Test;
import ua.gerasymenko.models.Contacts;
import ua.gerasymenko.models.User;

import java.util.List;

import static org.junit.Assert.*;

public class JdbcContactsTest {
    private JdbcContacts jdbcContacts;
    private User user;
    private Contacts contacts;

    @Before
    public void setUp() {
        jdbcContacts = new JdbcContacts(DataSourceTest.getSource());
        user = new User(12, "test", "test", "test", "test", "test",
                0, "test", false);

        contacts = new Contacts(user, 0, "test", "test", "test",
                "test", "test", "test", "test", 12);
    }

    @Test
    public void testAddContacts() throws Exception {
        assertTrue(jdbcContacts.create(contacts));

    }

    @Test
    public void testGetContacts() throws Exception {
        List<Contacts> contacts = jdbcContacts.getContacts(user.getId());
        assertTrue(contacts.size() == 1);


    }
}