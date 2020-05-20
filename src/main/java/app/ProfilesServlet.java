package app;



import app.Dao.LikesDao;
import app.Dao.UsersDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;


public class ProfilesServlet extends HttpServlet {

    private final TemplateEngine engine;
    private final Connection con;

    int usersCounter = 0;
    Integer usersAmount = 0;

    public ProfilesServlet(TemplateEngine engine, Connection con) {
        this.engine = engine;
        this.con = con;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        UsersDao usersDao = new UsersDao(con);
        HashMap<String, Object> data = new HashMap<>();

        try {
            List<User> users = usersDao.getAllExcept(CookieFilter.getCurrentUserId(req));
            usersAmount = users.size();
            if (usersCounter < users.size()) {
                data.put("user", users.get(usersCounter));
                engine.render("like-page.ftl", data, resp);
                usersCounter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LikesDao likesDao = new LikesDao(con);
        String button = req.getParameter("Button");
        int whom = Integer.parseInt(req.getParameter("Id"));
        int who = CookieFilter.getCurrentUserId(req);
        try {
            likesDao.add(who, whom, CheckAction.check(button));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(usersAmount == usersCounter ? "/liked" : "/users");
        }
    }
