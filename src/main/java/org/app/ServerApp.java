package org.app;

import org.app.servlets.*;
import org.app.utils.CookieFilter;
import org.app.utils.DbSetup;
import org.app.utils.HerokuEnv;
import org.app.utils.TemplateEngine;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.sql.Connection;
import java.util.EnumSet;

public class ServerApp {
    public static void main(String[] args) throws Exception {
        Server server = new Server(HerokuEnv.port());
        ServletContextHandler handler = new ServletContextHandler();

        String USER_NAME = HerokuEnv.jdbc_username();
        String PASSWORD = HerokuEnv.jdbc_password();
        String URL = HerokuEnv.jdbc_url();
        DbSetup.execute(URL, USER_NAME, PASSWORD);
        Connection con = DbSetup.createConnection(URL);

        TemplateEngine engine = TemplateEngine.folder("src/main/java/org/app/content");
        handler.addServlet(new ServletHolder(new LoginServlet(engine, con)), "/login");
        handler.addServlet(new ServletHolder(new LogoutServlet(con)), "/logout");
        handler.addServlet(new ServletHolder(new RegistrationServlet(engine, con)), "/registration");
        handler.addServlet(new ServletHolder(new ProfilesServlet(engine, con)), "/users");
        handler.addServlet(new ServletHolder(new LikedServlet(engine, con)), "/liked");
        handler.addServlet(new ServletHolder(new MessagesServlet(engine, con)), "/messages");
        handler.addServlet(new ServletHolder(new ReferenceServlet("css")), "/css/*");
        handler.addServlet(new ServletHolder(new ReferenceServlet("img")), "/img/*");
        handler.addServlet(new ServletHolder(new RedirectServlet()), "");
        handler.addServlet(new ServletHolder(new RedirectServlet()), "/*");
        handler.addFilter(new FilterHolder(new CookieFilter()), "/users", EnumSet.of(DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new CookieFilter()), "/liked", EnumSet.of(DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new CookieFilter()), "/messages", EnumSet.of(DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new CookieFilter()), "/logout", EnumSet.of(DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new CookieFilter()), "", EnumSet.of(DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new CookieFilter()), "/*", EnumSet.of(DispatcherType.REQUEST));
        server.setHandler(handler);

        server.start();
        server.join();
    }
}
