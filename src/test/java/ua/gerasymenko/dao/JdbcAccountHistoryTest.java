package ua.gerasymenko.dao;

import org.junit.Before;
import org.junit.Test;
import ua.gerasymenko.models.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import static org.junit.Assert.*;

public class JdbcAccountHistoryTest {
    private AccountHistoryAPI jdbcAccountHistory;
    private AccountHistory accountHistory;
    private Account account;
    private User user;

    @Before
    public void setUp() {
        jdbcAccountHistory = new JdbcAccountHistory(DataSourceTest.getSource());
        user = new User(19, "test", "test", "test", "test", "test",
                0, "test", false);
        account = new Account(23, user, "test", 0.0, new Date(0),
                new BigDecimal(0), "TTT", false);
        accountHistory = new AccountHistory(account, new BigDecimal(0),
                "test", new Timestamp(0), OperationType.DEPOSIT);

    }

    @Test
    public void testCreate() {
        assertTrue(jdbcAccountHistory.create(accountHistory));
    }

    @Test
    public void testDelete() {
        assertTrue(jdbcAccountHistory.delete(47));
    }
}