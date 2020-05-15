package app;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
}
