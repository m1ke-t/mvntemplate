import org.junit.Test;
import static org.junit.Assert.*;

public class StorageTest {
    Storage storage = Storage.getInstance();
    int size;


    @Test
    public void testSave() {
        size = storage.getAll().size();
        User user = new User("login", "pass", "name");
        storage.save(user);
        User dbUser = storage.findById(storage.findIDByLogin(user.getLogin()));
        user.setId(dbUser.getId());
        assertEquals(user, dbUser);

        user = new User("login1", "pass1", "name1");
        user.setId(10);
        storage.save(user);
        dbUser = storage.findById(10);
        assertEquals(user, dbUser);

        user = new User("login2", "pass2", "name2");
        user.setId(10);
        storage.save(user);
        dbUser = storage.findById(10);
        assertEquals(user, dbUser);

        assertEquals(size + 2, storage.getAll().size());
    }

    @Test
    public void testFindById() {
        User user = new User("login1337", "pass", "name");
        user.setId(1337);
        storage.save(user);
        User dbUser = storage.findById(1337);
        assertEquals(user, dbUser);

        assertEquals(null, storage.findById(1000)); //find nonexistent user
    }

    @Test
    public void testFindIDByLogin() {
        User user = new User("login122", "pass", "name");
        user.setId(122);
        storage.save(user);
        assertEquals((Integer)122, storage.findIDByLogin("login122"));
        assertEquals(null, storage.findIDByLogin("nonexistentLogin"));
    }

    @Test
    public void testDeleteByID() {
        User user = new User("login3", "pass3", "name3");
        user.setId(3);
        storage.save(user);
        assertEquals(true, storage.deleteById(3));
        assertEquals(false, storage.deleteById(1000));
    }
    @Test
    public void testDeleteByLogin() {
        User user = new User("login4", "pass4", "name4");
        user.setId(4);
        storage.save(user);
        assertEquals(true, storage.deleteByLogin("login4"));
        assertEquals(false, storage.deleteByLogin("nonexistentLogin"));
    }
}
