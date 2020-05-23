package app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
    public int id;
    public int from;
    public int to;
    public String text;
    public String date;
}
