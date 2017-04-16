import java.sql.Connection;

public interface IComponent {
    void sell(String format, Connection connection);
    void buy(String format, Connection connection);
}
