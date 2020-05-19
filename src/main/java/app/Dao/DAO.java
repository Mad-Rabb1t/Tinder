package app.Dao;

import app.User;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface DAO {
    List<User> getAll();
    void add();
    void modify();
    default List<User> getBy(Predicate<User> p) {
        return getAll().stream().filter(p).collect(Collectors.toList());
    }
}
