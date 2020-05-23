package app.utils;


import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class CookieFilter implements Filter {

    public static int getCurrentUserId(HttpServletRequest req) {
        String userId = getCookie(req)
                .map(Cookie::getValue)
                .orElse("-1");
        return Integer.parseInt(userId);
    }

    public static Optional<Cookie> getCookie(HttpServletRequest req){
        return Arrays.stream(req.getCookies())
                .filter(c -> c.getName().equals("userId"))
                .findFirst();
    }

    private boolean isHttp(ServletRequest request) {
        return request instanceof HttpServletRequest;
    }

    private boolean isCookieOk(HttpServletRequest request) {
        return getCookie(request).isPresent();
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (isHttp(request) && isCookieOk((HttpServletRequest)request)) {
            chain.doFilter(request, response);
        }
        else ((HttpServletResponse)response).sendRedirect("/login");
    }

    @Override
    public void destroy() {

    }
}
