import java.util.Collection;
import java.util.HashMap;


public class Storage {
    public static class StorageHolder {
        public static final Storage storageInstance = new Storage();
    }

    private Storage() {
    }

    public static Storage getInstance() {
        return StorageHolder.storageInstance;
    }


    private  HashMap<Integer, User> storage = new HashMap<>();
    private int cnt = 0;


    public User save(User user) {
        if (user.getId() == null) {
            if(findById(cnt) == null) {
                user.setId(cnt);
                storage.put(cnt, user);
                cnt++;
            } else {
                cnt++;
                save(user);
            }
        } else {
            storage.put(user.getId(), user);
        }
        return user;
    }

    public  User findById(Integer id) {
        return storage.get(id);
    }

    public  Integer findIDByLogin(String login) {
        for (User user : storage.values()) {
            if (user.getLogin().equals(login)) return user.getId();
        }
        return null;
    }
    public  boolean deleteById (Integer id) {
        return storage.remove(id) != null;
        //returns true if removed or false if was not found

    }
    public  boolean deleteByLogin (String login) {
        return storage.remove(findIDByLogin(login)) != null;
        //returns true if removed or false if was not found
    }

    public Collection<User> getAll() {
        return storage.values();
    }
}
