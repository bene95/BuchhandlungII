



import java.sql.Connection;
public interface IComponent {
    void insert(String format, Connection connection);
    void update();
    void delete();
    void select();
}
