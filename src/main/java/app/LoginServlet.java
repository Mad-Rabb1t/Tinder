package app;

import app.Dao.UsersDao;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {

    private final TemplateEngine engine;
    private final Connection con;

    public LoginServlet(TemplateEngine engine, Connection con) {
        this.con = con;
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>();
        String errorMes = "Incorrect login or password";
        try {
            String error = req.getParameter("error");
            if(error.equals("true"))
            data.put("error", errorMes);
        } catch (NullPointerException ex) {
            data.put("error", "");
        }
        engine.render("login.ftl", data, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("password");
        UsersDao dao = new UsersDao(con);
        if(dao.credentialsCorrect(login, pass)) {
            Cookie c = new Cookie("userId",  String.valueOf(dao.getUserId(login)));
            c.setMaxAge(60*60*2);
            resp.addCookie(c);
            resp.sendRedirect("/users");
        }
        else resp.sendRedirect("/login?error=true");
    }
}
