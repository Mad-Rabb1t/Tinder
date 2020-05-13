package app;

import app.content.User;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.List;

public class TestServlet extends HttpServlet {

    private final TemplateEngine engine;

    List<User> users = Arrays.asList(
            new User("Alex", "https://www.1zoom.me/big2/21/100325-yana.jpg"),
            new User("Kamran", "https://img2.akspic.ru/image/3366-sobaka-velsh_korgi-velsh_korgi_pembrok-morda-gruppa_porody_sobak-1920x1200.jpg"),
            new User("Turkan", "https://catsdogsvideo.com/wp-content/uploads/2017/03/maxresdefault-248.jpg")
    );
    int n = 0;

    public TestServlet(TemplateEngine engine) {
        this.engine = engine;
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try(PrintWriter pw = resp.getWriter()) {
//            pw.print("Hello World");
//        }
//    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String result = new BufferedReader(new FileReader(new File("src\\main\\java\\app\\content\\like-page.html")))
//                .lines().collect(Collectors.joining("\n"));
//        try(PrintWriter pw = resp.getWriter()) {
//            pw.print(result);
//        }
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>();
        if (n < users.size()) {
            data.put("user", users.get(n));
            engine.render("like-page.ftl", data, resp);
            n++;
            if (users.size() == n) {
                n = 0;
            }
        }

//        try(PrintWriter pw = resp.getWriter()) {
//            pw.print(result);
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("Button");
        CheckLike check = new CheckLike();
        String result = check.check(param);
        resp.sendRedirect("/users");
//        try (PrintWriter pw = resp.getWriter()) {
//            pw.print(result);
//        }
    }
}
