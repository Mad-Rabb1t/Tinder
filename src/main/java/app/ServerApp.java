package app;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class ServerApp {
    public static void main(String[] args) throws Exception {
        Server server = new Server(9000);
        ServletContextHandler handler = new ServletContextHandler();

        DbSetup.execute("jdbc:postgresql://localhost:5432/postgres", "postgres", "password", true);

        TemplateEngine engine = TemplateEngine.folder("src/main/java/app/content");
        handler.addServlet(new ServletHolder(new ProfilesServlet(engine)), "/users");
        handler.addServlet(new ServletHolder(new LikedServlet(engine)), "/liked");
        handler.addServlet(new ServletHolder(new MessagesServlet(engine)), "/messages");
        handler.addServlet(new ServletHolder(new ReferenceServlet("css")), "/css/*");
        server.setHandler(handler);

        server.start();
        server.join();
    }
}
