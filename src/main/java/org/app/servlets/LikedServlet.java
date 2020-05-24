package org.app.servlets;

import org.app.Dao.LikesDao;
import org.app.Dao.UsersDao;
import org.app.entities.User;
import org.app.utils.CookieFilter;
import org.app.utils.TemplateEngine;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class LikedServlet extends HttpServlet {
    private final TemplateEngine engine;
    private final Connection con;

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        LikesDao likesDao = new LikesDao(con);
        UsersDao usersDao = new UsersDao(con);

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
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String button = req.getParameter("Button");
        if (button.equals("logout")) {
            resp.sendRedirect("/logout");
        } else
            resp.sendRedirect("/messages?id=" + button);
    }
}
