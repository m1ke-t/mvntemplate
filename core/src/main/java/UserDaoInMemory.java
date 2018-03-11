import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;


public class UserDaoInMemory implements UserDao {
    static final Logger log = LoggerFactory.getLogger(UserDaoInMemory.class.getName());
    private Storage storage;

    @Override
    public void createUser(User user) {
        storage.save(user);
        log.info("User {} {} {} added", user.getId(), user.getLogin(), user.getName());
    }

    @Override
    public void updateUser(User user) {
        log.info("Searching user with id {}...", user.getId());
        if (storage.findById(user.getId()) != null) {
            log.info("User {} {} {} was found, updating...", user.getId(), user.getLogin(), user.getName());
            createUser(user);
            log.info("User {} {} {} updated!", user.getId(), user.getLogin(), user.getName());
        } else {
            log.info("User with id {} was not found!", user.getId());
        }
    }

    @Override
    public void deleteUser(Integer id) {

        if (storage.deleteById(id)) {
            log.info("User with login {} removed", id);
        } else {
            log.info("User with login {} was not found", id);
        }
    }

    @Override
    public void deleteUser(String login) {
        if (storage.deleteByLogin(login)) {
            log.info("User with login {}  removed", login);
        } else {
            log.info("User with login {} was not found", login);
        }
    }

    @Override
    public User getById(Integer id) {
        log.info("Trying to get user by id {}", id);
        User user = storage.findById(id);
        if (user != null) {
            log.info("User with id {} was found");
            log.info("User {} {} {}", user.getId(), user.getLogin(), user.getName());
            return user;
        } else {
            log.info("User with id {} was not found!", id);
            return null;
        }
    }

    @Override
    public ArrayList<User> getAll() {
        return new ArrayList<>(storage.getAll());
    }

    public UserDaoInMemory(Storage storage) {
        this.storage = storage;
    }
}
