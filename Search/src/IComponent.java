import java.sql.Connection;

public interface IComponent {
    String search(String title, Connection connection);

}
