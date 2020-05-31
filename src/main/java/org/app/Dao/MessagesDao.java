package org.app.Dao;

import org.app.entities.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MessagesDao {

    private final Connection con;
    private static final String SQL_addMessage = "insert into messages (who, whom, message, date) values (?, ?, ?, ?)";
    private static final String SQL_getMessages = "select * from messages where who = ? and whom = ? order by id desc limit 10";

    public MessagesDao(Connection con) {
        this.con = con;
    }


    public void add(int from, int to, String text) throws SQLException {
        String curTime = DateTimeFormatter.ofPattern("MMM dd, HH:mm").format(LocalDateTime.now());
        PreparedStatement st = con.prepareStatement(SQL_addMessage);
        st.setInt(1, from);
        st.setInt(2, to);
        st.setString(3, text);
        st.setString(4, curTime);
        st.execute();
    }


    public List<Message> getMessages(int who, int whom) throws SQLException {
        PreparedStatement st = con.prepareStatement(SQL_getMessages);
        st.setInt(1, who);
        st.setInt(2, whom);
        ResultSet rs = st.executeQuery();
        ArrayList<Message> messages = new ArrayList<>();
        while(rs.next()) {
            messages.add(
                    new Message(
                            rs.getInt("id"),
                            rs.getInt("who"),
                            rs.getInt("whom"),
                            rs.getString("message"),
                            rs.getString("date")
                    )
            );
        }
        return messages;
    }

}
