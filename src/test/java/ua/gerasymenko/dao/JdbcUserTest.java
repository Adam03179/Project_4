package ua.gerasymenko.dao;

import org.junit.Before;
import org.junit.Test;
import ua.gerasymenko.models.User;

import static org.junit.Assert.*;

public class JdbcUserTest {
    private JdbcUser jdbcUser;
    private User user;

    @Before
    public void setUp() {
        jdbcUser = new JdbcUser(DataSourceTest.getSource());
        user = new User(12, "test", "test", "test", "test", "test",
                0, "test", false);
    }

    @Test
    public void testAddUser() throws Exception {

        assertTrue(jdbcUser.create(user));

    }


    @Test
    public void testGetUser() throws Exception {

        assertEquals("test", jdbcUser.getUser("test", "test").getName());
}

    @Test
    public void testIsExist() throws Exception {
        assertTrue(jdbcUser.isExist("test", "test"));
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals((Object) 16, jdbcUser.getId("test"));
    }

    @Test
    public void testIsAdmin() throws Exception {
        assertFalse(jdbcUser.isAdmin(16));
    }

    @Test
    public void testGetUserById() throws Exception {
        assertEquals("test", jdbcUser.read(16).getName());
    }
}