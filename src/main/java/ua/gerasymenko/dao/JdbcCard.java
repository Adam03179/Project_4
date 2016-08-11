package ua.gerasymenko.dao;

import ua.gerasymenko.models.Account;
import ua.gerasymenko.models.Card;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The JdbcCard class responds for getting and putting information about
 * bank cards.
 *
 * @author Igor Gerasymenko
 */

public class JdbcCard implements CardAPI {

    private DataSource dataSource;
    private JdbcFactory jdbcFactory;
    private static final Logger logger = Logger.getLogger(JdbcCard.class);
    private static final ResourceBundle resourceBundle =
            ResourceBundle.getBundle("requestsql");

    public JdbcCard(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcFactory = JdbcFactory.getInstance();
    }

    /**
     * This method adds information about new card to database.
     *
     * @param card
     * @return true - if operation successful, false - if operation failed.
     */

    public boolean create(Card card) {

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psAddCard = connection.prepareStatement
                    (resourceBundle.getString("ADD_CARD"));

            psAddCard.setInt(1, card.getAccount().getId());
            psAddCard.setString(2, card.getNumber());
            psAddCard.setString(3, card.getCardStandard());
            psAddCard.setInt(4, card.getPinCode());
            psAddCard.setDate(5, card.getExpirationDate());
            psAddCard.execute();
            return true;

        } catch (SQLException e) {
            logger.error("create card error", e);
            return false;
        }


    }

    @Override
    public Card read(int id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psGetCardById = connection.prepareStatement
                    (resourceBundle.getString("GET_CARD_BY_ID"));

            psGetCardById.setInt(1, id);
            ResultSet result = psGetCardById.executeQuery();

            result.next();
            return getCardFromDb(result);

        } catch (SQLException e) {
            logger.error("read card error", e);
            return null;
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psDelete = connection.prepareStatement
                    (resourceBundle.getString("DELETE_CARD"));

            psDelete.setInt(1, id);

            ResultSet resultSet = psDelete.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            logger.error("delete card error ", e);
            return false;
        }
    }

    /**
     * This method checks if exist bank card in database or not.
     *
     * @param cardNumber
     * @return true - if account is exist, false - if not.
     */
    public boolean isExist(String cardNumber) {

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psIsCardExist = connection.prepareStatement
                    (resourceBundle.getString("IS_CARD_EXIST"));

            psIsCardExist.setString(1, cardNumber);

            ResultSet resultSet = psIsCardExist.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            logger.error("is card exist error ", e);
            return false;
        }

    }

    /**
     * This method gets information about all cards one specific account .
     *
     * @param accountId
     * @return list of all bank cards of one account
     */
    public List<Card> getAllCards(int accountId) {
        List<Card> cardList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psGetAllCards = connection.prepareStatement
                    (resourceBundle.getString("GET_ALL_CARDS"));
            psGetAllCards.setInt(1, accountId);

            ResultSet resultSet = psGetAllCards.executeQuery();

            while (resultSet.next()) {

                cardList.add(getCardFromDb(resultSet));

            }
            return cardList;

        } catch (SQLException e) {
            logger.error("error get all cards", e);
            return null;
        }

    }

    /**
     * This method needs only for getting a bank card from database by result of ResultSet
     *
     * @param resultSet
     * @return card or null if card doesn't exist.
     */
    private Card getCardFromDb(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("cards_id");
            int accountId = resultSet.getInt("account_id");
            String number = resultSet.getString("number");
            String standard = resultSet.getString("standard");
            int pin = resultSet.getInt("pin_code");
            Date expirationDate = resultSet.getDate("expiration_date");

            JdbcAccount jdbcAccount = jdbcFactory.getDAOAccount();
            Account account = jdbcAccount.read(accountId);


            return new Card(id, account, number, pin, expirationDate, standard);

        } catch (SQLException e) {
            logger.error("error card from db", e);
            return null;
        }
    }
}


