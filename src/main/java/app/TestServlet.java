package app;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
import java.util.stream.Collectors;

public class TestServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try(PrintWriter pw = resp.getWriter()) {
//            pw.print("Hello World");
//        }
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String result = new BufferedReader(new FileReader(new File("C:\\Users\\iturk\\Desktop\\IBA\\Tinder\\src\\main\\java\\app\\content\\profile.html")))
                .lines().collect(Collectors.joining("\n"));
        try(PrintWriter pw = resp.getWriter()) {
            pw.print(result);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
