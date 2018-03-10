import java.util.ArrayList;

/**
 * Created by Mike S. on 04.03.2018.
 */
public interface UserDao {
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(Integer id);
    void deleteUser(String login);
    User getById(Integer id);
    ArrayList<User> getAll();

}
