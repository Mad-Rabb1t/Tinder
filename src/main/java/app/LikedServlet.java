package app;

import app.Dao.LikesDao;
import app.Dao.TestData;
import app.Dao.UsersDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class LikedServlet extends HttpServlet {
    private final TemplateEngine engine;
    private final Connection con;

    public LikedServlet(TemplateEngine engine, Connection con) {
        this.engine = engine;
        this.con = con;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        LikesDao likesDao = new LikesDao(con);
        UsersDao usersDao = new UsersDao(con);

        try {
            List<User> likedUsers = likesDao.getLikedIdsByUser(CookieFilter.getCurrentUserId(req))
                    .stream().map(ids -> {
                        try {
                            return usersDao.getUserById(ids);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        throw new RuntimeException();
                    }).collect(Collectors.toList());

            HashMap<String, Object> data = new HashMap<>();
            data.put("likedUsers", likedUsers);
            engine.render("people-list.ftl", data, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userId = req.getParameter("Button");
        resp.sendRedirect("/messages?id=" + userId);
    }
}
