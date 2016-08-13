package ua.gerasymenko.dao;

import org.junit.Before;
import org.junit.Test;
import ua.gerasymenko.models.Account;
import ua.gerasymenko.models.Card;
import ua.gerasymenko.models.User;

import java.math.BigDecimal;
import java.sql.Date;

import static org.junit.Assert.*;

public class JdbcCardTest {
    private CardAPI jdbcCard;
    private Card card;
    private Account account;
    private User user;


    @Before
    public void setUp() {
        jdbcCard = new JdbcCard(DataSourceTest.getSource());
        user = new User(19, "test", "test", "test", "test", "test",
                0, "test", false);
        account = new Account(23,user,"test",0.0,new Date(0),
                new BigDecimal(0),"TTT",false);
        card = new Card(account,"test",0,new Date(0),"test");

    }

    @Test
    public void testCreate() {
        assertTrue(jdbcCard.create(card));
    }


    @Test
    public void testIsExist() {
        assertTrue(jdbcCard.isExist("test"));
    }


    @Test
    public void testDelete() {
        assertTrue(jdbcCard.delete(17));

    }
}