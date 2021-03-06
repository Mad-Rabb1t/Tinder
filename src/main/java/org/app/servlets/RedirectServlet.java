package org.app.servlets;

import org.app.utils.CookieFilter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RedirectServlet extends HttpServlet {

  public RedirectServlet() {
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    if (CookieFilter.isCookieOk(req)) {
      resp.sendRedirect("/users");
    } else {
      resp.sendRedirect("/login");
    }
  }

}
