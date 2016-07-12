package dao;

import dbmodels.Card;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
            psAddCard.setString(3, card.getCardStandard().name());
            psAddCard.setInt(4, card.getPinCode());
            psAddCard.setDate(5, card.getExpirationDate());
            return true;

        } catch (SQLException e) {
            logger.error("add card error", e);
            return false;
        }


    }
}


