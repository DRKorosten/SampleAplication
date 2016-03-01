package sample.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BDConnection {
    private static final String database = "default_user";
    private static final String username = "postgres";
    private static final String password = "floppy419";
    private static Connection connection;

    public static void createOtherQuerys(String query) {
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
    public static ResultSet createSelectQuery(String query) {
        ResultSet result = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + database, username, password);//
            Statement statement = connection.createStatement();
             result = statement.executeQuery(query);
            connection.close();
        } catch (Exception ee) {
            ee.printStackTrace();
        }
        return result;
    }
}