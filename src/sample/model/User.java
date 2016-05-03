package sample.model;
import sample.dataBase.BDConnection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String eMail;

    public User(String username, String password, String firstName, String lastName, String eMail) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String geteMail() {
        return eMail;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
    public String getID(){
        if (this!=null){
            String sql = "SELECT id FROM public.\"user\" WHERE \"username\"='"+username+"';";
            try {
                ResultSet resultSet = BDConnection.createSelectQuery(sql);
                resultSet.next();
                return resultSet.getString("id");
            } catch (SQLException e) {
                System.out.println("NO USERS WITH THIS NAME");
            }

        }
        return new String();
    }

    @Override
    public String toString() {
        return "User " + firstName + " " + lastName + " has his own account. Username = " + username + " password = " + password + " E-mail = " + eMail;
    }
}
