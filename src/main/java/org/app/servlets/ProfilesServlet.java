package org.app.servlets;


import org.app.Dao.LikesDao;
import org.app.Dao.UsersDao;
import org.app.entities.User;
import org.app.utils.CheckAction;
import org.app.utils.CookieFilter;
import org.app.utils.TemplateEngine;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
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


    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        UsersDao usersDao = new UsersDao(con);
        HashMap<String, Object> data = new HashMap<>();

        List<User> users = usersDao.getAllExcept(CookieFilter.getCurrentUserId(req));
        usersAmount = users.size();
        if (usersCounter < users.size()) {
            data.put("user", users.get(usersCounter));
            engine.render("like-page.ftl", data, resp);
            usersCounter++;
        }
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        LikesDao likesDao = new LikesDao(con);
        int whom = Integer.parseInt(req.getParameter("Id"));
        int who = CookieFilter.getCurrentUserId(req);
        int action = CheckAction.check(req.getParameter("Button"));
        if (likesDao.getLikedIdsByUser(who).contains(whom)) {
            likesDao.modifyAction(who, whom, action);
        } else likesDao.add(who, whom, action);
        if (usersAmount == usersCounter) {
            resp.sendRedirect("/liked");
            usersCounter = 0;
        }
        else resp.sendRedirect("/users");

    }
}
