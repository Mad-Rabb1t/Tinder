package org.app.servlets;

import lombok.extern.log4j.Log4j2;
import org.app.dao.LikesDao;
import org.app.dao.UsersDao;
import org.app.entities.User;
import org.app.utils.CookieFilter;
import org.app.utils.TemplateEngine;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.postgresql.util.PSQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Log4j2
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
                    Optional<User> userOptional;
                    try {
                        userOptional = Optional.of(usersDao.getUserById(ids));
                    } catch (PSQLException e) {
                        log.error("No user with such id in db");
                        userOptional = Optional.empty();
                    }
                    return userOptional;
                })
                .flatMap(o -> o.map(Stream::of).orElseGet(Stream::empty))
                .collect(Collectors.toList());

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
