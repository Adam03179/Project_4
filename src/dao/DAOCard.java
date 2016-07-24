package dao;

import dbmodels.Card;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DAOCard {

    private DataSource dataSource;
    private static final Logger logger = Logger.getLogger(DAOCard.class);
    private static final ResourceBundle resourceBundle =
            ResourceBundle.getBundle("requestsql");

    public DAOCard(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public boolean addCard(Card card) {

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psAddCard = connection.prepareStatement
                    (resourceBundle.getString("ADD_CARD"));
            psAddCard.setInt(1, card.getAccountId());
            psAddCard.setString(2, card.getNumber());
            psAddCard.setString(3, card.getCardStandard());
            psAddCard.setInt(4, card.getPinCode());
            psAddCard.setDate(5, card.getExpirationDate());
            psAddCard.execute();
            return true;

        } catch (SQLException e) {
            logger.error("add card error", e);
            return false;
        }


    }

    public boolean isExist(String cardNumber){

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
}


