package app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class User {
    public int id;
    public String name;
    public String photo;
    public String date;
}
