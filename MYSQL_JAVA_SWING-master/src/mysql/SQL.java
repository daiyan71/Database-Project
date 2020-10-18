/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Saliya
 */
public class SQL {

    private static SQL INSTENCE = null;
    private static Connection MYSQL_CONNECTION;
    private final String IP = "103.134.43.221";
    private final int TIMEOUT = 3000;
    private final String PORT = "1433";
    private final String DATABASE = "PROJECT";
    private final String USER = "sa";
    private final String PASSWORD = "123456";
    private DatabaseMetaData DATABASE_METADATA = null;
    private final boolean CONNECTION_AUTOCOMMIT = false;
    private final boolean AUTORECONNECT = true;
    private static ResultSet RESULTSET;
    private static PreparedStatement PREPAREDSTATEMENT;

    public SQL() {

    }

    public Connection EstablishConnection() throws Exception {
        if (!isReachable(IP, TIMEOUT)) {
            return null;
        } else {
            try {
                if (MYSQL_CONNECTION == null || MYSQL_CONNECTION.isClosed()) {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance(); 
                    MYSQL_CONNECTION = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PROJECT;selectMethod=cursor", "sa", "123456");
                    MYSQL_CONNECTION.setAutoCommit(CONNECTION_AUTOCOMMIT);
                }
                return MYSQL_CONNECTION;
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
                throw new Exception(e);
            } finally {
                try {
                    if (MYSQL_CONNECTION == null) {
                        MYSQL_CONNECTION.close();
                    }
                } catch (SQLException e) {
                    MYSQL_CONNECTION.close();
                    throw new Exception(e);
                }
            }
        }

    }

    private boolean isReachable(String IP, int Timeout) {
        try {
            InetAddress inet = InetAddress.getByName(IP);
            boolean status = inet.isReachable(Timeout);
            return status;
        } catch (UnknownHostException ex) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    public static synchronized SQL getInstence() {
        if (INSTENCE == null) {
            INSTENCE = new SQL();
        }
        return INSTENCE;
    }

    public void setCommand(Object sqlQuery) throws Exception {
        PREPAREDSTATEMENT = SQL.MYSQL_CONNECTION.prepareStatement(sqlQuery.toString());
        PREPAREDSTATEMENT.executeUpdate();
    }

    public ResultSet setCommand(String sqlQuery) throws Exception {
        RESULTSET = SQL.MYSQL_CONNECTION.createStatement().executeQuery(sqlQuery);
        return RESULTSET;
    }

    public DatabaseMetaData getMetadata() throws Exception {
        try {
            if (MYSQL_CONNECTION != null) {
                this.DATABASE_METADATA = MYSQL_CONNECTION.getMetaData();
            }
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        return this.DATABASE_METADATA;
    }
}
