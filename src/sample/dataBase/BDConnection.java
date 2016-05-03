package sample.dataBase;

import javafx.stage.Stage;
import sample.dialogForms.unseccessfulREG.UnsuccessfulWindow;
import java.sql.*;

public class BDConnection {
    private static final String database = "test_user";
    private static final String username = "postgres";
    private static final String password = "floppy419";
    private static Connection connection;

    public static void createOtherQueries(String query) {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + database, username, password);
            Statement statement = connection.createStatement();
            statement.execute(query);
            connection.close();
        } catch (Exception ee) {
                ee.printStackTrace();
        }
    }
    public static ResultSet createSelectQuery(String query) throws SQLException {
        ResultSet result;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + database, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
            result = statement.executeQuery(query);
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}