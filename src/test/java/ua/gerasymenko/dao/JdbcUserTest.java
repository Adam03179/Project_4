package ua.gerasymenko.dao;

import org.junit.Before;
import org.junit.Test;
import ua.gerasymenko.models.User;

import static org.junit.Assert.*;

public class JdbcUserTest {
    private UserAPI jdbcUser;
    private User user;

    @Before
    public void setUp() {
        jdbcUser = new JdbcUser(DataSourceTest.getSource());
        user = new User("test", "test", "test", "test", "test",
                0, "test", false);
    }

    @Test
    public void testCreate() {

        assertTrue(jdbcUser.create(user));

    }


    @Test
    public void testGetUser() {

        assertEquals("test", jdbcUser.getUser("test", "test").getName());
    }

    @Test
    public void testIsExist() {
        assertTrue(jdbcUser.isExist("test", "test"));
    }

    @Test
    public void testGetId() {
        assertEquals((Object) 18, jdbcUser.getId("test"));
    }

    @Test
    public void testIsAdmin() {
        assertFalse(jdbcUser.isAdmin(18));
    }

    @Test
    public void testRead() {
        assertEquals("test", jdbcUser.read(18).getName());
    }

    @Test
    public void testDelete() {
        assertTrue(jdbcUser.delete(18));
    }


}