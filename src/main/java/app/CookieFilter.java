package app;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class CookieFilter {

    public static int getCurrentUserId(HttpServletRequest req) {
        String userId = Arrays.stream(req.getCookies())
                .filter(c -> c.getName().equals("userId"))
                .findFirst()
                .map(Cookie::getValue)
                .orElse("-1");
        return Integer.parseInt(userId);
    }
}
