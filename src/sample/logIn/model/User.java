package sample.logIn.model;

/**
 * Created by Dmytro on 2/28/2016.
 */
public class User {
    public static final User USER_WITHOUT_DATA = new User();
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String eMail;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String geteMail() {
        return eMail;
    }

    public User(){
    }

    public User(String username, String password, String firstName, String lastName, String eMail) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return new String("User "+firstName+" "+lastName+" has his own account. Username = "+username+" password = "+password+" E-mail = "+eMail);
    }
}
