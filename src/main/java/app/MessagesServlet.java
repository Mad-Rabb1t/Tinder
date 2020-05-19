package app;

import app.Dao.TestData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class MessagesServlet extends HttpServlet {
    private final TemplateEngine engine;

    public MessagesServlet(TemplateEngine engine) {
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("id"));
        User user = TestData.users.stream().filter(u -> u.id == userId).findFirst().orElseThrow(RuntimeException::new);
        HashMap<String, Object> data = new HashMap<>();
        data.put("user", user);
        engine.render("chat.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
