package org.app.utils;


import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
@Log4j2
public class CookieFilter implements Filter {

    private static EncodeDecode encodeDecode = new EncodeDecode();

    public static int getCurrentUserId(HttpServletRequest req) {
        try {
            String userId = getCookie(req)
                    .map(Cookie::getValue)
                    .orElseThrow(IllegalArgumentException::new);
            return Integer.parseInt(encodeDecode.decrypt(userId));
        } catch (IllegalArgumentException ex) {
            log.info("No currently logged in user found!");
            return -1;
        }
    }

    public static Optional<Cookie> getCookie(HttpServletRequest req) {
        try {
            return Arrays.stream(req.getCookies())
                    .filter(c -> c.getName().equals("userId"))
                    .findFirst();
        } catch (NullPointerException ex) {
            log.info("No cookies found!");
            return Optional.empty();
        }
    }

    private boolean isHttp(ServletRequest request) {
        return request instanceof HttpServletRequest;
    }

    public static boolean isCookieOk(HttpServletRequest request) {
        return getCookie(request).isPresent();
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (isHttp(request) && isCookieOk((HttpServletRequest) request)) {
            chain.doFilter(request, response);
        } else ((HttpServletResponse) response).sendRedirect("/login");
    }

    @Override
    public void destroy() {

    }
}
