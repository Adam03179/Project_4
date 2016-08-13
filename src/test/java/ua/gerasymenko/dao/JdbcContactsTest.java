package ua.gerasymenko.dao;

import org.junit.Before;
import org.junit.Test;

import ua.gerasymenko.models.Contacts;
import ua.gerasymenko.models.User;

import static org.junit.Assert.*;

public class JdbcContactsTest {
    private ContactsAPI jdbcContacts;
    private User user;
    private Contacts contacts;

    @Before
    public void setUp() {
        jdbcContacts = new JdbcContacts(DataSourceTest.getSource());
        user = new User(19, "test", "test", "test", "test", "test",
                0, "test", false);

        contacts = new Contacts(user, 0, "test", "test", "test",
                "test", "test", "test", "test", 19);
    }

    @Test
    public void testCreateContacts() {
        assertTrue(jdbcContacts.create(contacts));

    }

    @Test
    public void testDeleteContacts() {
        assertTrue(jdbcContacts.delete(22));

    }


}