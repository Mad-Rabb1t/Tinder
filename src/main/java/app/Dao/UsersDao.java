package app.Dao;


import app.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsersDao {

    private final Connection con;
    private final static String SQL_getAllUsers = "select * from users";
    final String SQL_GetUserByUsername = "select id from users where name = ?";
    final String SQL_GetUserByLogin = "select id from users where login = ?";
    final String SQL_GetUserById = "select name, photo from users where id = ?";
    final String SQL_ValidateUser = "select id from users where login = ? and password = ?";
    final String SQL_AddUser = "insert into users (login, password, name, photo) values (?, ?, ?, ?)"; //?

    public UsersDao(Connection connection) {
        this.con = connection;
    }

    public List<User> getAllExcept(int id) throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        PreparedStatement st = con.prepareStatement(SQL_getAllUsers);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            users.add(
                    new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("photo")
                    )
            );
        }
        return users.stream().filter(u -> u.id != id).collect(Collectors.toList());
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

    public int getUserId(String login) throws SQLException {
        PreparedStatement st = con.prepareStatement(SQL_GetUserByLogin);
        st.setString(1, login);
        ResultSet rs = st.executeQuery();
        rs.next();
        return rs.getInt("id");
    }

    public User getUserById(int id) throws SQLException {
        PreparedStatement st = con.prepareStatement(SQL_GetUserById);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        rs.next();
        return new User(id, rs.getString("name"), rs.getString("photo"));
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
