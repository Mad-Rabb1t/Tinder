package app;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class LikedServlet extends HttpServlet {
    private final TemplateEngine engine;

    public LikedServlet(TemplateEngine engine) {
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("likedUsers", DAO.likedUser);
        engine.render("people-list.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userId = req.getParameter("Button");
        resp.sendRedirect("/messages?id=" + userId);
    }
}
