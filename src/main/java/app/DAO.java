package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DAO {
    public static List<User> users = Arrays.asList(
            new User("Alex", "https://www.1zoom.me/big2/21/100325-yana.jpg"),
            new User("Kamran", "https://img2.akspic.ru/image/3366-sobaka-velsh_korgi-velsh_korgi_pembrok-morda-gruppa_porody_sobak-1920x1200.jpg"),
            new User("Turkan", "https://catsdogsvideo.com/wp-content/uploads/2017/03/maxresdefault-248.jpg")
    );

    public static List<User> likedUser = new ArrayList<>();
}
