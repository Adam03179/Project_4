package ua.gerasymenko.dao;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;


public class DataSourceTest {
    private static final org.apache.log4j.Logger logger
            = org.apache.log4j.Logger.getLogger(DataSourceTest.class);

    private static DataSource dataSource = new DataSource() {
        @Override
        public Connection getConnection(String username,
                                        String password) throws SQLException {
            return null;
        }

        @Override
        public Connection getConnection()
                throws SQLException {
            try {

                Class.forName("com.mysql.jdbc.Driver");

            } catch (ClassNotFoundException e) {
                logger.error("test connection error", e);
            }

            String url = "jdbc:mysql://127.0.0.1/payments";
            return DriverManager.getConnection(url,"root","onscreen");
        }

        @Override
        public PrintWriter getLogWriter() throws SQLException {
            return null;
        }

        @Override
        public void setLogWriter(PrintWriter out) throws SQLException {

        }

        @Override
        public void setLoginTimeout(int seconds) throws SQLException {

        }

        @Override
        public int getLoginTimeout() throws SQLException {
            return 0;
        }

        @Override
        public java.util.logging.Logger getParentLogger()
                throws SQLFeatureNotSupportedException {
            return null;
        }

        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            return null;
        }

        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return false;
        }
    };

    public static DataSource getSource() {
        return dataSource;
    }
}


