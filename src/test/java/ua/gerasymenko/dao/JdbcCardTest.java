package ua.gerasymenko.dao;

import org.junit.Before;
import org.junit.Test;

import ua.gerasymenko.models.Account;
import ua.gerasymenko.models.Card;
import ua.gerasymenko.models.User;

import java.math.BigDecimal;
import java.sql.Date;

public class JdbcCardTest {
    private JdbcCard jdbcCard;
    //private JdbcFactory daoFactory = PowerMockito.mock(JdbcFactory.class);
    private User user;
    private Account account;
    private Card card;

    @Before
    public void setUp() {
      //  Mockito.reset(daoFactory);
        jdbcCard = new JdbcCard(DataSourceTest.getSource());

        user = new User(12, "test", "test", "test", "test", "test",
                0, "test", false);

        account = new Account(111,user, "test", 0.0, new Date(0)
                , new BigDecimal(0), "TST", true);

        card = new Card(account, "test", 0, new Date(0), "test");

    }

    @Test
    public void testAddCard() throws Exception {
     //   Mockito.when(JdbcFactory.getInstance()).thenReturn(daoFactory);
     //   Mockito.verify(jdbcCard.create(card));

        //assertTrue(jdbcCard.create(card));
    }

    @Test
    public void testIsExist() throws Exception {

    }

    @Test
    public void testGetAllCards() throws Exception {

    }
}