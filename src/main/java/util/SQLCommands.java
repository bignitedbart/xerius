package util;

import java.sql.*;

public class SQLCommands {

    public Connection connectToDB(String URL, String userName, String password) throws SQLException {
        Connection con = DriverManager.getConnection(URL, userName, password);
        return con;
    }

    public void executeQuery(String query, Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet result = stmt.executeQuery(query);
        System.out.println(result);
    }

}