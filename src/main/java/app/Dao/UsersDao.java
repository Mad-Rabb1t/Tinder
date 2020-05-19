package app.Dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsersDao {

    private final Connection con;
    final String SQL_GetUserByUsername = "select id from users where name = ?";
    final String SQL_GetUserByLogin = "select id from users where login = ?";
    final String SQL_ValidateUser = "select id from users where login = ? and password = ?";
    final String SQL_AddUser = "insert into users (login, password, name, photo) values (?, ?, ?, ?)"; //?

    public UsersDao(Connection connection) {
        this.con = connection;
    }

    public boolean credentialsCorrect(String login, String pass) throws SQLException {
        PreparedStatement st = con.prepareStatement(SQL_ValidateUser);
        st.setString(1, login);
        st.setString(2, pass);
        return st.executeQuery().next();
    }

    public boolean usernameReserved(String name) throws SQLException {
        PreparedStatement st = con.prepareStatement(SQL_GetUserByUsername);
        st.setString(1, name);
        return st.executeQuery().next();
    }

    //refactor!
    public boolean loginReserved(String login) throws SQLException {
        PreparedStatement st = con.prepareStatement(SQL_GetUserByLogin);
        st.setString(1, login);
        return st.executeQuery().next();
    }

    public void add(String login, String pass, String name, String photo) throws SQLException {
        PreparedStatement st = con.prepareStatement(SQL_AddUser);
        st.setString(1, login);
        st.setString(2, pass);
        st.setString(3, name);
        st.setString(4, photo);
        st.execute();
    }
}
