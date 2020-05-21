package app.Dao;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessagesDao {

    private final Connection con;
    private static final String SQL_addMessage = "insert into messages (who, whom, message, date) values (?, ?, ?, ?)";

    public MessagesDao(Connection con) {
        this.con = con;
    }

    @SneakyThrows
    public void add(int from, int to, String text){
        String curTime = DateTimeFormatter.ofPattern("MMM dd, HH:mm").format(LocalDateTime.now());
        PreparedStatement st = con.prepareStatement(SQL_addMessage);
        st.setInt(1, from);
        st.setInt(2, to);
        st.setString(3, text);
        st.setString(4, curTime);
        st.execute();
    }

}
