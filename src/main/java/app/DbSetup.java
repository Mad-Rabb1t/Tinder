package app;

import lombok.SneakyThrows;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbSetup {


  public static Connection createConnection(String url, String username, String password) throws SQLException {
    return DriverManager.getConnection(url, username, password);
  }

  public static Connection createConnection(String jdbcUrl) throws SQLException {
    return DriverManager.getConnection(jdbcUrl);
  }


  static void execute(String uri, String user, String password) {
    execute(uri, user, password, false);
  }

  static void execute(String uri, String user, String password, boolean clear) {
    FluentConfiguration config = new FluentConfiguration()
        .dataSource(uri, user, password);
    Flyway flyway = new Flyway(config);
    if (clear) flyway.clean();
    flyway.migrate();
  }

}
