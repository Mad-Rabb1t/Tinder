package app;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.sql.Connection;

public class ServerApp {
    public static void main(String[] args) throws Exception {
        Server server = new Server(9000);
        ServletContextHandler handler = new ServletContextHandler();

        // Remove before deployment!!!
        String USER_NAME = "postgres";
        String PASSWORD = "postgres";
        String URL = "jdbc:postgresql://localhost:5432/postgres";
        DbSetup.execute(URL, USER_NAME, PASSWORD);
        Connection con = DbSetup.createConnection(URL, USER_NAME, PASSWORD);

        TemplateEngine engine = TemplateEngine.folder("src/main/java/app/content");
        handler.addServlet(new ServletHolder(new LoginServlet(engine, con)), "/login");
        handler.addServlet(new ServletHolder(new ProfilesServlet(engine, con)), "/users");
        handler.addServlet(new ServletHolder(new LikedServlet(engine, con)), "/liked");
        handler.addServlet(new ServletHolder(new MessagesServlet(engine)), "/messages");
        handler.addServlet(new ServletHolder(new ReferenceServlet("css")), "/css/*");
        server.setHandler(handler);

        server.start();
        server.join();
    }
}
