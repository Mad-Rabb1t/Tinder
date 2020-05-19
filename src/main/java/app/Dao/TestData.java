package app.Dao;

import app.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TestData {
    public static List<User> users = Arrays.asList(
            new User(1,"Alex", "https://www.1zoom.me/big2/21/100325-yana.jpg"),
            new User(2,"Kamran", "https://img2.akspic.ru/image/3366-sobaka-velsh_korgi-velsh_korgi_pembrok-morda-gruppa_porody_sobak-1920x1200.jpg"),
            new User(3,"Turkan", "https://catsdogsvideo.com/wp-content/uploads/2017/03/maxresdefault-248.jpg")
    );

    public static List<User> likedUser = new ArrayList<>();

    HashMap<Integer, String> messages = new HashMap<Integer, String>(){{
        messages.put(2, "Hey Kamran, this is Alex");
        messages.put(3, "Hey Turkan, this is Alex");
    }};
}
