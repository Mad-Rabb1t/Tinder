package app;

import app.Dao.UsersDao;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

public class RegistrationServlet extends HttpServlet {
    private final TemplateEngine engine;
    private final Connection con;


    public RegistrationServlet(TemplateEngine engine, Connection con) {
        this.engine = engine;
        this.con = con;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>();
        String mes = "Login is already used";
        try {
            String error = req.getParameter("error");
            if (error.equals("loginReserved"))
                data.put("error", mes);
        } catch (NullPointerException ex) {
            data.put("error", "");
        }
        engine.render("registration.ftl", data, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersDao usersDao = new UsersDao(con);
        String login = req.getParameter("login");
        String pas = req.getParameter("password");
        String name = req.getParameter("name");
        String photo = req.getParameter("photo");
        if (usersDao.loginReserved(login)) {
            resp.sendRedirect("/registration?error=loginReserved");
        } else{
            usersDao.add(login, pas, name, photo);
            resp.sendRedirect("/login");
        }
    }
}
