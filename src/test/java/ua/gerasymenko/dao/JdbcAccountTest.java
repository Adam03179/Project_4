package ua.gerasymenko.dao;

import org.junit.Before;
import org.junit.Test;
import ua.gerasymenko.models.Account;
import ua.gerasymenko.models.User;

import java.math.BigDecimal;
import java.sql.Date;

import static org.junit.Assert.*;

public class JdbcAccountTest {
    private AccountAPI jdbcAccount;
    private Account account;
    private User user;

    @Before
    public void setUp() {
        jdbcAccount = new JdbcAccount(DataSourceTest.getSource());
        user = new User(19, "test", "test", "test", "test", "test",
                0, "test", false);
        account = new Account(user,"test",0.0,new Date(0),
                new BigDecimal(0),"TTT",false);


    }

    @Test
    public void testCreate() {
        assertTrue(jdbcAccount.create(account));

    }

    @Test
    public void testIsExist() {
        assertTrue(jdbcAccount.isExist("test"));
    }


    @Test
    public void testDelete()  {
        assertTrue(jdbcAccount.delete(23));
    }
}