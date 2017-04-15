



import java.sql.Connection;
public interface IComponent {
    void insert(String format, Connection connection);
    void update(String format,Connection connection);
    void delete(String format, Connection connection);
    String select(String format, Connection connection);
}
