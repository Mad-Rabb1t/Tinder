package app.servlets;

import app.Dao.UsersDao;
import app.utils.CookieFilter;
import lombok.SneakyThrows;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;


public class LogoutServlet extends HttpServlet {
    private final Connection con;

    public LogoutServlet(Connection con) {
        this.con = con;
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        UsersDao usersDao = new UsersDao(con);
        int currentUserId = CookieFilter.getCurrentUserId(req);
        usersDao.setLastLoginNow(currentUserId);
        Cookie cookie = CookieFilter.getCookie(req).orElseThrow(RuntimeException::new);
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
        resp.sendRedirect("/login");
    }
}
