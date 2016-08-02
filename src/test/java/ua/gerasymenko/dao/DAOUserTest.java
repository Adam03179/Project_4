package ua.gerasymenko.dao;

import org.junit.Before;
import org.junit.Test;
import ua.gerasymenko.models.User;

import static org.junit.Assert.*;

public class DAOUserTest {
    private DAOUser daoUser;
    private User user;

    @Before
    public void setUp() {
        daoUser = new DAOUser(DataSourceTest.getSource());
        user = new User(12, "test", "test", "test", "test", "test",
                0, "test", false);
    }

    @Test
    public void testAddUser() throws Exception {

        assertTrue(daoUser.addUser(user));

    }


    @Test
    public void testGetUser() throws Exception {

        assertEquals("test", daoUser.getUser("test", "test").getName());
    }

    @Test
    public void testIsExist() throws Exception {
        assertTrue(daoUser.isExist("test", "test"));
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals((Object) 12, daoUser.getId("test"));
    }

    @Test
    public void testIsAdmin() throws Exception {
        assertFalse(daoUser.isAdmin(12));
    }

    @Test
    public void testGetUserById() throws Exception {
        assertEquals("test", daoUser.getUserById(12).getName());
    }
}