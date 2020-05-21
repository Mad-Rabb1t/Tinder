package app;

import app.Dao.MessagesDao;
import app.Dao.UsersDao;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.HashMap;

public class MessagesServlet extends HttpServlet {
    private final TemplateEngine engine;
    private final Connection con;

    public MessagesServlet(TemplateEngine engine, Connection con) {
        this.engine = engine;
        this.con = con;
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        UsersDao usersDao = new UsersDao(con);
        int userId = Integer.parseInt(req.getParameter("id"));
        HashMap<String, Object> data = new HashMap<>();
        data.put("user", usersDao.getUserById(userId));
        engine.render("chat.ftl", data, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        MessagesDao messagesDao = new MessagesDao(con);
        String message = req.getParameter("message");
        int from = CookieFilter.getCurrentUserId(req);
        int to = Integer.parseInt(req.getParameter("userId"));
        messagesDao.add(from, to, message);
        resp.sendRedirect("/messages?id=" + to);
    }
}
