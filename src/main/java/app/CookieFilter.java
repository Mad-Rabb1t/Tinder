package app;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

public class CookieFilter {

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
}
