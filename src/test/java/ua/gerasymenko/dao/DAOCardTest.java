package ua.gerasymenko.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.gerasymenko.models.Account;
import ua.gerasymenko.models.Card;
import ua.gerasymenko.models.User;

import java.math.BigDecimal;
import java.sql.Date;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
public class DAOCardTest {
    private DAOCard daoCard;
    private DAOFactory daoFactory = PowerMockito.mock(DAOFactory.class);
    private User user;
    private Account account;
    private Card card;

    @Before
    public void setUp() {
        Mockito.reset(daoFactory);
        daoCard = new DAOCard(DataSourceTest.getSource());

        user = new User(12, "test", "test", "test", "test", "test",
                0, "test", false);

        account = new Account(111,user, "test", 0.0, new Date(0)
                , new BigDecimal(0), "TST", true);

        card = new Card(account, "test", 0, new Date(0), "test");

    }

    @Test
    public void testAddCard() throws Exception {
        Mockito.when(DAOFactory.getInstance()).thenReturn(daoFactory);
        Mockito.verify(daoCard.addCard(card));

        //assertTrue(daoCard.addCard(card));
    }

    @Test
    public void testIsExist() throws Exception {

    }

    @Test
    public void testGetAllCards() throws Exception {

    }
}