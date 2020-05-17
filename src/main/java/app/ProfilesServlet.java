package app;



import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;


public class ProfilesServlet extends HttpServlet {

    private final TemplateEngine engine;

    int usersCounter = 0;

    public ProfilesServlet(TemplateEngine engine) {
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HashMap<String, Object> data = new HashMap<>();

        if (usersCounter < DAO.users.size()) {
            data.put("user", DAO.users.get(usersCounter));
            engine.render("like-page.ftl", data, resp);
            usersCounter++;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String button = req.getParameter("Button");
        if(CheckLike.check(button)) {
            int id = Integer.parseInt(req.getParameter("Id"));
            String userName = req.getParameter("User");
            String photo = req.getParameter("Photo");
            User curUser = new User(id,userName, photo);
            if (!DAO.likedUser.contains(curUser)) {
                DAO.likedUser.add(curUser);
            }
        }
        resp.sendRedirect(DAO.users.size() == usersCounter ? "/liked" : "/users");
        }
    }
