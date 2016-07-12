package dao;

import dbmodels.Account;
import dbmodels.AccountHistory;
import dbmodels.OperationType;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DAOAccountHistory {
    private DataSource dataSource;
    private static final Logger logger =
            Logger.getLogger(DAOAccountHistory.class);
    private static final ResourceBundle resourceBundle =
            ResourceBundle.getBundle("requestsql");

    public DAOAccountHistory(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public List<AccountHistory> getHistory(Account account) {

        List<AccountHistory> histories = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement psHistory = connection.prepareStatement
                    (resourceBundle.getString("GET_HISTORY"));

            psHistory.setInt(1, account.getId());

            ResultSet resultSet = psHistory.executeQuery();
            while (resultSet.next()) {

                int id = resultSet.getInt("history_id");
                int accountId = resultSet.getInt("account_id");
                double sum = resultSet.getDouble("sum");
                String partnerName = resultSet.getString("partner_name");
                Date operationDate = resultSet.getDate("operation_date");
                OperationType operationType = OperationType.valueOf
                        (resultSet.getString("operation_type").toUpperCase());

                histories.add(new AccountHistory(id, accountId, sum, partnerName,
                        operationDate, operationType));

            }
            return histories;


        } catch (SQLException e) {
            logger.error("get history error", e);
            return null;
        }


    }
}
